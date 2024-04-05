package com.example.demo.service;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.model.Users;

import java.sql.SQLException;
import java.util.Optional;

public interface UsersService {
    void createUser(Users users) throws SQLException;
    Optional<UserProjectRelation> authenticateUser(UserLoginDTO userLoginDTO) throws SQLException;
}
