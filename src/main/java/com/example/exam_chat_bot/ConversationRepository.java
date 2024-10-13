package com.example.exam_chat_bot;

import com.example.exam_chat_bot.entities.Conversation;
import com.example.exam_chat_bot.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ConversationRepository  extends JpaRepository<Conversation, Long>, JpaSpecificationExecutor<Conversation> {
    List<Conversation> findByPerson(Person person);
}

