# New Baseline API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-7.0+-green)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.12+-orange)
![License](https://img.shields.io/badge/License-MIT-green)

## 📋 Sobre o Projeto

O **New Baseline** é uma API REST robusta desenvolvida para sistemas de MRP (Manufacturing Resource Planning), permitindo que equipes de engenharia antecipem o planejamento de materiais, otimizem o PCP (Planejamento e Controle da Produção) e melhorem a gestão de compras e fabricação.

A aplicação fornece uma base sólida para o gerenciamento antecipado de:
- Matéria-prima
- Material de consumo
- Peças já projetadas
- Planejamento de compras
- Controle de fabricação

## 🚀 Tecnologias Utilizadas

### Core Framework
- **Java 21** - Linguagem de programação moderna
- **Spring Boot 3.4.3** - Framework principal
- **Spring Data JPA** - Persistência relacional
- **Spring Data MongoDB** - Persistência NoSQL
- **Spring Security** - Segurança e autenticação
- **Spring Validation** - Validação de dados
- **Spring HATEOAS** - APIs REST com hypermedia
- **Maven** - Gerenciamento de dependências

### Banco de Dados
- **PostgreSQL** - Banco de dados principal relacional
- **MongoDB** - Banco de dados NoSQL para dados não estruturados
- **H2 Database** - Banco em memória para testes
- **SQL Server** - Suporte adicional para ambientes corporativos
- **Flyway** - Migração e versionamento do banco relacional

### Autenticação e Autorização
- **OAuth2 Authorization Server** - Servidor de autorização
- **OAuth2 Client** - Cliente OAuth2
- **OAuth2 Resource Server** - Servidor de recursos
- **Spring Security** - Framework de segurança completo

### Mensageria e Comunicação
- **RabbitMQ** - Message broker para comunicação assíncrona
- **Spring Mail** - Envio de emails

### Documentação e Mapeamento
- **SpringDoc OpenAPI 3** (v2.8.5) - Documentação automática da API
- **MapStruct** (v1.6.3) - Mapeamento de objetos type-safe
- **Lombok** - Redução de código boilerplate

### Utilitários e Performance
- **Hibernate Types** (v2.21.1) - Tipos customizados para Hibernate
- **Spring Boot DevTools** - Ferramentas de desenvolvimento

## 🏗️ Arquitetura

O projeto segue os princípios de **Clean Architecture** e **Domain-Driven Design (DDD)**, com arquitetura híbrida utilizando bancos relacionais e NoSQL:

```
src/main/java/com/eliezer/newbaseline/
├── config/          # Configurações (Security, OAuth2, RabbitMQ, etc.)
├── controller/      # Controladores REST
├── dto/            # Data Transfer Objects
├── entity/         # Entidades JPA e MongoDB
├── repository/     # Repositórios JPA e MongoDB
├── service/        # Lógica de negócio
├── security/       # Configurações OAuth2 e JWT
├── messaging/      # Configurações RabbitMQ
├── mapper/         # MapStruct mappers
└── exception/      # Tratamento de exceções
```

### Arquitetura de Dados
- **PostgreSQL**: Dados transacionais, usuários, pedidos
- **MongoDB**: Logs, auditoria, dados de telemetria
- **RabbitMQ**: Processamento assíncrono e integração entre serviços

## 📦 Principais Entidades

### Core Entities
- **Baseline** - Entidade principal do sistema
- **Material** - Gestão de materiais e matéria-prima
- **Component** - Componentes e peças do sistema
- **BomItem** - Itens da Bill of Materials
- **ProductionOrder** - Ordens de produção

### Support Entities
- **User** - Usuários do sistema
- **Department** - Departamentos organizacionais
- **Supplier** - Fornecedores
- **Category** - Categorização de materiais

## 🛠️ Configuração do Ambiente

### Pré-requisitos
- Java 21 ou superior
- Maven 3.8+
- PostgreSQL 15+
- MongoDB 7.0+
- RabbitMQ 3.12+
- Docker (opcional)

### Instalação

1. **Clone o repositório**
   ```bash
   git clone https://github.com/eliezermoraesss/new-baseline.git
   cd new-baseline
   ```

2. **Configure os bancos de dados**
   
   **PostgreSQL:**
   ```sql
   CREATE DATABASE new_baseline;
   CREATE USER baseline_user WITH PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE new_baseline TO baseline_user;
   ```
   
   **MongoDB:**
   ```bash
   # Inicie o MongoDB e crie o banco
   use new_baseline_logs
   db.createUser({
     user: "baseline_user",
     pwd: "your_password",
     roles: ["readWrite"]
   })
   ```
   
   **RabbitMQ:**
   ```bash
   # Instalar e iniciar RabbitMQ
   sudo systemctl start rabbitmq-server
   
   # Criar usuário (opcional)
   sudo rabbitmqctl add_user baseline_user your_password
   sudo rabbitmqctl set_permissions -p / baseline_user ".*" ".*" ".*"
   ```

3. **Configure as variáveis de ambiente**
   ```bash
   # PostgreSQL
   export POSTGRES_HOST=localhost
   export POSTGRES_PORT=5432
   export POSTGRES_DB=new_baseline
   export POSTGRES_USER=baseline_user
   export POSTGRES_PASSWORD=your_password
   
   # MongoDB
   export MONGODB_HOST=localhost
   export MONGODB_PORT=27017
   export MONGODB_DATABASE=new_baseline_logs
   export MONGODB_USERNAME=baseline_user
   export MONGODB_PASSWORD=your_password
   
   # RabbitMQ
   export RABBITMQ_HOST=localhost
   export RABBITMQ_PORT=5672
   export RABBITMQ_USERNAME=baseline_user
   export RABBITMQ_PASSWORD=your_password
   
   # OAuth2 & Security
   export JWT_SECRET=your-super-secret-jwt-key
   export OAUTH2_CLIENT_ID=your-client-id
   export OAUTH2_CLIENT_SECRET=your-client-secret
   
   # Email Configuration
   export MAIL_HOST=smtp.gmail.com
   export MAIL_PORT=587
   export MAIL_USERNAME=your-email@gmail.com
   export MAIL_PASSWORD=your-app-password
   ```

4. **Execute a aplicação**
   ```bash
   # Desenvolvimento
   mvn spring-boot:run
   
   # Produção
   mvn clean package
   java -jar target/newbaseline-0.0.1-SNAPSHOT.jar
   ```

### Docker (Recomendado)

```yaml
# docker-compose.yml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - postgres
      - mongodb
      - rabbitmq
    environment:
      - SPRING_PROFILES_ACTIVE=docker
  
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: new_baseline
      POSTGRES_USER: baseline_user
      POSTGRES_PASSWORD: password123
    ports:
      - "5432:5432"
  
  mongodb:
    image: mongo:7.0
    environment:
      MONGO_INITDB_ROOT_USERNAME: baseline_user
      MONGO_INITDB_ROOT_PASSWORD: password123
    ports:
      - "27017:27017"
  
  rabbitmq:
    image: rabbitmq:3.12-management
    environment:
      RABBITMQ_DEFAULT_USER: baseline_user
      RABBITMQ_DEFAULT_PASS: password123
    ports:
      - "5672:5672"
      - "15672:15672"
```

```bash
# Executar com Docker Compose
docker-compose up -d
```

## 📚 Documentação da API

A documentação completa da API está disponível via Swagger UI:

- **Desenvolvimento**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

### Principais Endpoints

#### Baseline Management
```
GET    /api/v1/baselines          # Listar baselines
POST   /api/v1/baselines          # Criar baseline
GET    /api/v1/baselines/{id}     # Buscar baseline por ID
PUT    /api/v1/baselines/{id}     # Atualizar baseline
DELETE /api/v1/baselines/{id}     # Deletar baseline
```

#### Material Management
```
GET    /api/v1/materials          # Listar materiais
POST   /api/v1/materials          # Criar material
GET    /api/v1/materials/{id}     # Buscar material por ID
PUT    /api/v1/materials/{id}     # Atualizar material
```

#### Production Orders
```
GET    /api/v1/production-orders  # Listar ordens de produção
POST   /api/v1/production-orders  # Criar ordem de produção
PUT    /api/v1/production-orders/{id}/status  # Atualizar status
```

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar apenas testes unitários
mvn test -Dtest="*UnitTest"

# Executar apenas testes de integração
mvn test -Dtest="*IntegrationTest"

# Gerar relatório de cobertura
mvn jacoco:report
```

## 🔒 Segurança

A aplicação implementa segurança robusta através de múltiplas camadas:

- **OAuth2 Authorization Server** - Servidor de autorização completo
- **OAuth2 Resource Server** - Proteção de recursos com JWT
- **OAuth2 Client** - Integração com provedores externos
- **Role-based Authorization** - Autorização baseada em perfis
- **CORS Configuration** - Configuração para frontend Angular
- **SQL Injection Protection** - Proteção via JPA/Hibernate
- **Email Verification** - Verificação de email para novos usuários

### Perfis de Usuário
- **ADMIN** - Acesso completo ao sistema
- **ENGINEER** - Gestão de baselines e materiais
- **PLANNER** - Visualização e planejamento
- **VIEWER** - Apenas visualização

## 🚀 Deploy

### Variáveis de Ambiente - Produção

```bash
# PostgreSQL
POSTGRES_HOST=your-postgres-host
POSTGRES_PORT=5432
POSTGRES_DB=new_baseline_prod
POSTGRES_USER=prod_user
POSTGRES_PASSWORD=secure_password

# MongoDB
MONGODB_HOST=your-mongodb-host
MONGODB_PORT=27017
MONGODB_DATABASE=new_baseline_logs_prod
MONGODB_USERNAME=prod_user
MONGODB_PASSWORD=secure_password

# RabbitMQ
RABBITMQ_HOST=your-rabbitmq-host
RABBITMQ_PORT=5672
RABBITMQ_USERNAME=prod_user
RABBITMQ_PASSWORD=secure_password

# Security & OAuth2
JWT_SECRET=your-super-secure-jwt-secret-key
JWT_EXPIRATION=86400000
OAUTH2_CLIENT_ID=prod-client-id
OAUTH2_CLIENT_SECRET=prod-client-secret

# Email
MAIL_HOST=smtp.company.com
MAIL_PORT=587
MAIL_USERNAME=noreply@company.com
MAIL_PASSWORD=secure_mail_password

# Application
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
```

### Deploy com Docker

```yaml
# docker-compose.prod.yml
version: '3.8'
services:
  app:
    image: new-baseline-api:latest
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - POSTGRES_HOST=postgres
      - MONGODB_HOST=mongodb
      - RABBITMQ_HOST=rabbitmq
    depends_on:
      - postgres
      - mongodb
      - rabbitmq
  
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DATABASE: new_baseline_prod
      POSTGRES_USER: prod_user
      POSTGRES_PASSWORD: secure_password
    volumes:
      - postgres_data:/var/lib/postgresql/data
  
  mongodb:
    image: mongo:7.0
    environment:
      MONGO_INITDB_ROOT_USERNAME: prod_user
      MONGO_INITDB_ROOT_PASSWORD: secure_password
    volumes:
      - mongodb_data:/data/db
  
  rabbitmq:
    image: rabbitmq:3.12-management
    environment:
      RABBITMQ_DEFAULT_USER: prod_user
      RABBITMQ_DEFAULT_PASS: secure_password
    volumes:
      - rabbitmq_data:/var/lib/rabbitmq

volumes:
  postgres_data:
  mongodb_data:
  rabbitmq_data:
```

## 🔄 Integração com Frontend

Esta API foi projetada para integrar perfeitamente com aplicações Angular modernas, fornecendo:

- **RESTful endpoints** padronizados com HATEOAS
- **CORS configurado** para desenvolvimento local
- **OpenAPI 3 documentation** completa via SpringDoc
- **DTOs otimizados** com MapStruct para transferência eficiente
- **Paginação e filtros** para grandes volumes de dados
- **OAuth2 integration** pronto para SSO corporativo
- **Real-time notifications** via RabbitMQ e WebSockets
- **Email notifications** para eventos importantes do sistema

## 📈 Roadmap

### Próximas Versões
- [ ] **v1.1.0** - Cache distribuído com Redis
- [ ] **v1.2.0** - WebSockets para atualizações em tempo real
- [ ] **v1.3.0** - Relatórios avançados com JasperReports
- [ ] **v1.4.0** - Integração com sistemas ERP externos
- [ ] **v2.0.0** - Microserviços com Spring Cloud

### Futuras Melhorias
- [ ] API de métricas e observabilidade (Micrometer + Prometheus)
- [ ] Testes de carga automatizados (JMeter)
- [ ] CI/CD com GitHub Actions
- [ ] Deploy automático no Kubernetes
- [ ] Monitoramento avançado com ELK Stack
- [ ] API Rate Limiting e Throttling
- [ ] Backup automático e disaster recovery

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👤 Autor

**Eliezer Moraes**
- GitHub: [@eliezermoraesss](https://github.com/eliezermoraesss)
- LinkedIn: [Seu LinkedIn](https://linkedin.com/in/seu-perfil)

## 🙏 Agradecimentos

- Spring Framework Team
- Comunidade Java
- Contribuidores do projeto

---

**⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!**