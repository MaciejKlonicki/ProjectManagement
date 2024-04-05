package com.example.demo.repository;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.Role;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.model.Users;
import com.example.demo.persistence.sql.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

public class SqlUsersRepository implements UsersRepository{

    @Override
    public void createUser(Users users) throws SQLException {
        String query = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getEmail());
            preparedStatement.setString(3, users.getPassword());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Optional<UserProjectRelation> authenticateUsers(UserLoginDTO userLoginDTO) throws SQLException {
        String query = "SELECT upr.user_id, upr.project_id, upr.role_id " +
                "FROM userprojectrelation upr " +
                "JOIN users u ON u.id = upr.user_id " +
                "WHERE u.username = ? AND u.password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userLoginDTO.username());
            preparedStatement.setString(2, userLoginDTO.password());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    UserProjectRelation userProjectRelation = new UserProjectRelation();
                    userProjectRelation.setUserId(resultSet.getInt("user_id"));
                    userProjectRelation.setProjectId(resultSet.getInt("project_id"));
                    userProjectRelation.setRoles(Collections.singletonList(Role.values()[resultSet.getInt("role_id") - 1]));

                    return Optional.of(userProjectRelation);
                }
            }
        }
        return Optional.empty();
    }
}
