package com.eliezer.newbaseline.repository;

import com.eliezer.newbaseline.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
