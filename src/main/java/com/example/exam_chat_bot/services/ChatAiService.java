package com.example.exam_chat_bot.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

@BrowserCallable
@AnonymousAllowed
@Service
public class ChatAiService {
    private ChatClient chatClient;
    private VectorStore vectorStore;
    @Value("classpath:/prompts/prompt-template.ts")
    private Resource promptRresource;

    public ChatAiService ( ChatClient.Builder builder ,VectorStore vectorStore){

        this.chatClient = builder.build();
        this.vectorStore = vectorStore ;
    }
    private static final Logger logger = LoggerFactory.getLogger(ChatAiService.class);

    public String ragChat( String qst){
        logger.info("Starting ragChat with question: {}", qst);

        List<Document> documents = vectorStore.similaritySearch(qst);
        List<String> context = documents.stream().map(Document::getContent).toList();
        PromptTemplate promptTemplate = new PromptTemplate(promptRresource);
        Prompt prompt = promptTemplate.create(Map.of("context", context, "question", qst));
        return chatClient.prompt(prompt).
                call().
                content();
    }
}
