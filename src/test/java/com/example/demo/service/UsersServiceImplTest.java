package com.example.demo.service;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersServiceImplTest {

    private UsersRepository usersRepository;
    private UsersService usersService;

    @BeforeEach
    public void setUp() {
        usersRepository = mock(UsersRepository.class);
        usersService = new UsersServiceImpl(usersRepository);
    }

    @Test
    void shouldCreateUser() throws SQLException {
        Users user = new Users();
        user.setUsername("user123");
        user.setEmail("test@o2.pl");
        user.setPassword("pass");

        usersService.createUser(user);

        verify(usersRepository).createUser(user);
    }

    @Test
    void shouldAuthenticateUser() throws SQLException {
        UserLoginDTO userLoginDTO = new UserLoginDTO("user123", "password123");
        UserProjectRelation expectedUserRelation = new UserProjectRelation();
        expectedUserRelation.setUserId(1);
        expectedUserRelation.setProjectId(2);
        expectedUserRelation.setRoleId(3);

        when(usersRepository.authenticateUsers(userLoginDTO)).thenReturn(Optional.of(expectedUserRelation));

        Optional<UserProjectRelation> authenticatedUser = usersService.authenticateUser(userLoginDTO);

        assertTrue(authenticatedUser.isPresent());
        assertEquals(expectedUserRelation, authenticatedUser.get());
    }
}