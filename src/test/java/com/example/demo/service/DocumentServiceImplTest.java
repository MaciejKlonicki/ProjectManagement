package com.example.demo.service;

import com.example.demo.model.Document;
import com.example.demo.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocumentServiceImplTest {

    private DocumentRepository documentRepository;
    private DocumentService documentService;

    @BeforeEach
    public void setUp() {
        documentRepository = mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    @Test
    void shouldCreateNewDocument() throws SQLException {
        Document document = new Document();
        document.setTitle("Test Title");
        document.setDescription("Test Description");
        document.setCreator("Test Creator");
        document.setTopic("Test Topic");
        document.setContent("Test Content");
        document.setProjectId(1);

        documentService.createDocument(document);

        verify(documentRepository).createDocument(document);
    }

    @Test
    void shouldEditDocument() throws SQLException {
        Document document = new Document();
        document.setTitle("Test Title");
        document.setDescription("Test Description");
        document.setCreator("Test Creator");
        document.setTopic("Test Topic");
        document.setContent("Test Content");
        document.setProjectId(1);

        documentService.editDocument(document);

        verify(documentRepository).editDocument(document);
    }

    @Test
    void shouldGetDocumentByTitle() throws SQLException {
        String titleToFind = "Test Title";
        Document expectedDocument = new Document();
        expectedDocument.setTitle(titleToFind);
        expectedDocument.setDescription("Test Description");
        expectedDocument.setCreator("Test Creator");
        expectedDocument.setTopic("Test Topic");
        expectedDocument.setContent("Test Content");
        expectedDocument.setProjectId(1);

        when(documentRepository.getDocumentByTitle(titleToFind)).thenReturn(expectedDocument);

        Document retrievedDocument = documentService.getDocumentByTitle(titleToFind);

        assertEquals(expectedDocument, retrievedDocument);
    }

    @Test
    void shouldDeleteDocument() throws SQLException {
        String titleToDelete = "Test Title";

        documentService.deleteDocument(titleToDelete);

        verify(documentRepository).deleteDocument(titleToDelete);
    }

    @Test
    void shouldShowDocumentsForUser() throws SQLException {
        String username = "user123";
        List<Document> expectedDocuments = new ArrayList<>();
        Document document1 = new Document("Title 1", "Description 1", "Creator 1", "Topic 1", "Content 1", 1);
        Document document2 = new Document("Title 2", "Description 2", "Creator 2", "Topic 2", "Content 2", 2);
        expectedDocuments.add(document1);
        expectedDocuments.add(document2);

        when(documentRepository.showDocumentsForUser(username)).thenReturn(expectedDocuments);

        List<Document> retrievedDocuments = documentService.showDocumentsForUser(username);

        assertEquals(expectedDocuments, retrievedDocuments);
    }
}