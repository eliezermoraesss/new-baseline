package com.eliezer.newbaseline.repository;

import com.eliezer.newbaseline.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
