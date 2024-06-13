package com.example.demo.repository;

import com.example.demo.model.Project;
import com.example.demo.persistence.sql.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlProjectRepository implements ProjectRepository {
    @Override
    public void createProject(Project project) throws SQLException {
        String query = "INSERT INTO Project (name, description, creator) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setString(3, project.getCreator());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteProject(String projectName) throws SQLException {
        String query = "DELETE FROM Project WHERE name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, projectName);

            preparedStatement.executeUpdate();
        }
    }
    public void updateProjectDescription(String projectName, String newDescription) throws SQLException {
        String query = "UPDATE Project SET description = ? WHERE name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newDescription);
            preparedStatement.setString(2, projectName);
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT name, description, creator FROM Project";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String creator = resultSet.getString("creator");

                Project project = new Project(name, description, creator);
                projects.add(project);
            }
        }
        return projects;
    }
}
