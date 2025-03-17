package com.eliezer.newbaseline.repository.mssql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ComponentRepository {

    private final JdbcTemplate jdbcTemplate;

    public ComponentRepository(@Qualifier("mssqlDataSource") javax.sql.DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getItemDescription(String codigo) {
        String query = """
            SELECT B1_DESC as 'Descrição'
            FROM PROTHEUS12_R27.dbo.SB1010
            WHERE B1_COD = ?
            AND D_E_L_E_T_ <> '*'
        """;

        try {
            return jdbcTemplate.queryForObject(query, String.class, codigo);
        } catch (Exception e) {
            return "";
        }
    }

    public List<Map<String, Object>> getComponents(String parentCode) {
        String query = """
            SELECT 
                struct.G1_COD AS 'Código Pai',
                struct.G1_COMP AS 'Código',
                prod.B1_DESC AS 'Descrição',
                struct.G1_XUM AS 'Unidade',
                struct.G1_QUANT AS 'Quantidade'
            FROM 
                PROTHEUS12_R27.dbo.SG1010 struct
            INNER JOIN
                PROTHEUS12_R27.dbo.SB1010 prod
            ON 
                G1_COMP = B1_COD AND prod.D_E_L_E_T_ <> '*'
            WHERE 
                G1_COD = ? 
                AND G1_REVFIM <> 'ZZZ' 
                AND struct.D_E_L_E_T_ <> '*' 
                AND G1_REVFIM = (
                    SELECT MAX(G1_REVFIM) 
                    FROM PROTHEUS12_R27.dbo.SG1010 
                    WHERE G1_COD = ?
                    AND G1_REVFIM <> 'ZZZ' 
                    AND struct.D_E_L_E_T_ <> '*'
                )
        """;

        List<Map<String, Object>> results = jdbcTemplate.queryForList(query, parentCode, parentCode);

        // Convert quantidade to BigDecimal
        return results.stream()
                .map(row -> {
                    Map<String, Object> modifiedRow = new HashMap<>(row);
                    Object quantidadeObj = row.get("Quantidade");
                    if (quantidadeObj instanceof Number) {
                        modifiedRow.put("Quantidade", BigDecimal.valueOf(((Number) quantidadeObj).doubleValue()));
                    }
                    return modifiedRow;
                })
                .collect(Collectors.toList());
    }
}
