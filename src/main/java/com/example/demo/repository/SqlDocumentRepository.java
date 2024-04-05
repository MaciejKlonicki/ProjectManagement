package com.example.demo.repository;

import com.example.demo.model.Document;
import com.example.demo.persistence.sql.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlDocumentRepository implements DocumentRepository {
    @Override
    public void createDocument(Document document) throws SQLException {
        String query = "INSERT INTO Document (title, description, creator, topic, content, project_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, document.getTitle());
            preparedStatement.setString(2, document.getDescription());
            preparedStatement.setString(3, document.getCreator());
            preparedStatement.setString(4, document.getTopic());
            preparedStatement.setString(5, document.getContent());
            preparedStatement.setInt(6, document.getProjectId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
        public void editDocument(Document document) throws SQLException {
            String query = "UPDATE Document SET description = ?, creator = ?, topic = ?, content = ?, project_id = ? WHERE title = ?";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, document.getDescription());
                preparedStatement.setString(2, document.getCreator());
                preparedStatement.setString(3, document.getTopic());
                preparedStatement.setString(4, document.getContent());
                preparedStatement.setInt(5, document.getProjectId());
                preparedStatement.setString(6, document.getTitle());

                preparedStatement.executeUpdate();
            }
    }

    @Override
    public Document getDocumentByTitle(String title) throws SQLException {
        String query = "SELECT * FROM Document WHERE title = ?";
        Document document = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String description = resultSet.getString("description");
                String creator = resultSet.getString("creator");
                String topic = resultSet.getString("topic");
                String content = resultSet.getString("content");
                int projectId = resultSet.getInt("project_id");

                document = new Document(title, description, creator, topic, content, projectId);
            }
        }
        return document;
    }

    @Override
    public void deleteDocument(String title) throws SQLException {
        String query = "DELETE FROM Document WHERE title = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Document> showDocumentsForUser(String username) throws SQLException {
        List<Document> documents = new ArrayList<>();
        String query = "SELECT DISTINCT d.title, d.description, d.creator, d.topic, " +
                "d.content, d.project_id FROM Document d " +
                "JOIN Project p ON d.project_id = p.id " +
                "JOIN UserProjectRelation upr ON p.id = upr.project_id " +
                "JOIN Users u ON upr.user_id = u.id " +
                "WHERE u.username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String creator = resultSet.getString("creator");
                String topic = resultSet.getString("topic");
                String content = resultSet.getString("content");
                int projectID = resultSet.getInt("project_id");

                Document document = new Document(title, description, creator, topic, content, projectID);
                documents.add(document);
            }
        }

        return documents;
    }
}
