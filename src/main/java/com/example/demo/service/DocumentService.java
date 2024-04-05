package com.example.demo.service;

import com.example.demo.model.Document;

import java.sql.SQLException;
import java.util.List;

public interface DocumentService {
    void createDocument(Document document) throws SQLException;
    void editDocument(Document document) throws SQLException;
    Document getDocumentByTitle(String title) throws SQLException;
    void deleteDocument(String title) throws SQLException;
    List<Document> showDocumentsForUser(String username) throws SQLException;
}
