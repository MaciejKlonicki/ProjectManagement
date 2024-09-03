package com.example.demo.service;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.model.Users;
import com.example.demo.repository.SqlUsersRepository;
import com.example.demo.repository.UsersRepository;

import java.sql.SQLException;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_TIME_MINS = 5;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void createUser(Users users) throws SQLException {
        if (!isPasswordValid(users.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 6 characters long, contain at least one letter and one special character.");
        }
        usersRepository.createUser(users);
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (!Character.isDigit(c) && !Character.isLetter(c)) {
                hasSpecialChar = true;
            }
        }
        return hasLetter && hasSpecialChar;
    }

    @Override
    public Optional<UserProjectRelation> authenticateUser(UserLoginDTO userLoginDTO) throws SQLException {
        return usersRepository.authenticateUsers(userLoginDTO);
    }
}
