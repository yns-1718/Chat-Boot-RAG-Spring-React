package com.example.exam_chat_bot.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Component
@Configuration
public class RagDataLoader {
    @Value("classpath:/files/cv.pdf")
    private Resource pdfResource;
    @Value("store-data-v2.json")
    private String storeFile;
    
    private EmbeddingModel embeddingModel;

    private JdbcClient jdbcClient;

    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel){
        SimpleVectorStore vectorStore = new SimpleVectorStore(embeddingModel);
        String fileStore = Path.of("src","main","resources","store")
                .toAbsolutePath()+"/"+storeFile;
        File file = new File(fileStore);
        if(!file.exists()){
            PagePdfDocumentReader pdfDocumentReader =
                    new PagePdfDocumentReader(pdfResource);
            List<Document> documents = pdfDocumentReader.get();
            TextSplitter textSplitter = new TokenTextSplitter();
            List<Document> chunks = textSplitter.split(documents);
            vectorStore.accept(chunks);
            vectorStore.save(file);
        }else {
            vectorStore.load(file);
        }
        return vectorStore;
    }
    
}
