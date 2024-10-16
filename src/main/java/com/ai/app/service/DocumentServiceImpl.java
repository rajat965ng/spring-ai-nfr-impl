package com.ai.app.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DocumentServiceImpl implements DocumentService{

    @Override
    public List<Document> read(List<String> urls) {
        var docs = urls.stream()
                .map(TikaDocumentReader::new)
                .map(TikaDocumentReader::get)
                .flatMap(List::stream)
                .collect(toList());
        return docs;
    }
}
