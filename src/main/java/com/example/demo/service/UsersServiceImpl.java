package com.example.demo.service;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.model.Users;
import com.example.demo.repository.SqlUsersRepository;
import com.example.demo.repository.UsersRepository;

import java.sql.SQLException;
import java.util.Optional;

public class UsersServiceImpl implements UsersService{
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void createUser(Users users) throws SQLException {
        usersRepository.createUser(users);
    }

    @Override
    public Optional<UserProjectRelation> authenticateUser(UserLoginDTO userLoginDTO) throws SQLException {
        return usersRepository.authenticateUsers(userLoginDTO);
    }
}
