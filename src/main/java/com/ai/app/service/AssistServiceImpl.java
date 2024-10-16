package com.ai.app.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistServiceImpl implements AssistService {

    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private TokenTextSplitter splitter;
    @Autowired
    private ChatClient chatClient;
    @Override
    public void ingest(List<String> urls) {
        var docs = splitter.apply(documentService.read(urls));
        vectorStore.add(docs);
    }

    @Override
    public List<Document> retrieve(String keyword) {
        return vectorStore.similaritySearch(keyword);
    }

    @Override
    public String getAnswer(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
