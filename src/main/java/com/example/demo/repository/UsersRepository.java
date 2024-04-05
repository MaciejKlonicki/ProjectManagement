package com.example.demo.repository;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.model.Users;

import java.sql.SQLException;
import java.util.Optional;

public interface UsersRepository {
    void createUser(Users users) throws SQLException;
    Optional<UserProjectRelation> authenticateUsers(UserLoginDTO userLoginDTO) throws SQLException;

}
