package com.example.demo.repository;

import com.example.demo.model.Project;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.persistence.sql.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlUserProjectRelationRepository implements UserProjectRelationRepository {
    @Override
    public void assignUserRole(UserProjectRelation userProjectRelation) throws SQLException {
        String query = "INSERT INTO UserProjectRelation (user_id, project_id, role_id) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, userProjectRelation.getUserId());
            preparedStatement.setLong(2, userProjectRelation.getProjectId());
            preparedStatement.setLong(3, userProjectRelation.getRoleId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Project> getProjectsForUserByUsername(String username) throws SQLException {
        List<Project> projects = new ArrayList<>();

        String query = "SELECT DISTINCT p.name, p.description, p.creator " +
                "FROM Project p " +
                "JOIN UserProjectRelation upr ON p.id = upr.project_id " +
                "JOIN Users u ON upr.user_id = u.id " +
                "WHERE u.username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

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

    @Override
    public List<UserProjectRelation> getProjectDetails(String projectName) throws SQLException {
        List<UserProjectRelation> projectDetails = new ArrayList<>();
        String query = "SELECT * FROM UserProjectRelation UPR " +
                "INNER JOIN Project P ON UPR.project_id = P.id " +
                "WHERE P.name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, projectName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int projectId = resultSet.getInt("project_id");
                int roleId = resultSet.getInt("role_id");

                projectDetails.add(new UserProjectRelation(userId, projectId, roleId));
            }
        }
        return projectDetails;
    }

    @Override
    public UserProjectRelation getDocumentDetails(String documentName) throws SQLException {
        String query = "SELECT upr.user_id, upr.role_id " +
                "FROM UserProjectRelation upr " +
                "JOIN Project p ON upr.project_id = p.id " +
                "JOIN Document d ON p.id = d.project_id " +
                "WHERE d.title = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, documentName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int roleId = resultSet.getInt("role_id");

                return new UserProjectRelation(userId, roleId);
            }
        }

        return null;
    }
}
