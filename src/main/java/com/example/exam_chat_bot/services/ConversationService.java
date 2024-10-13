package com.example.exam_chat_bot.services;
import com.example.exam_chat_bot.ConversationRepository;
import com.example.exam_chat_bot.entities.Conversation;
import com.example.exam_chat_bot.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public List<Conversation> getConversationsByPerson(Person person) {
        return conversationRepository.findByPerson(person);
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }
}
