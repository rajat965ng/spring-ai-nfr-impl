package com.ai.app.service;

import org.springframework.ai.document.Document;

import java.util.List;

public interface DocumentService {
    public List<Document> read(List<String> urls);
}
