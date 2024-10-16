package com.ai.app.service;

import org.springframework.ai.document.Document;

import java.util.List;

public interface AssistService {
    public void ingest(List<String> urls);
    public List<Document> retrieve(String keyword);
    public String getAnswer(String question);
}
