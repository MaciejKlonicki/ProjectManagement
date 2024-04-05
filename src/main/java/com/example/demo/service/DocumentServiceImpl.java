package com.example.demo.service;

import com.example.demo.model.Document;
import com.example.demo.repository.DocumentRepository;

import java.sql.SQLException;
import java.util.List;

public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void createDocument(Document document) throws SQLException {
        documentRepository.createDocument(document);
    }

    @Override
    public void editDocument(Document document) throws SQLException {
        documentRepository.editDocument(document);
    }

    @Override
    public Document getDocumentByTitle(String title) throws SQLException {
        return documentRepository.getDocumentByTitle(title);
    }

    @Override
    public void deleteDocument(String title) throws SQLException {
        documentRepository.deleteDocument(title);
    }

    @Override
    public List<Document> showDocumentsForUser(String username) throws SQLException {
        return documentRepository.showDocumentsForUser(username);
    }
}
