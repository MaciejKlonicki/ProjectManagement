package com.example.demo.servlet.user;

import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repository.SqlUsersRepository;
import com.example.demo.service.UsersService;
import com.example.demo.service.UsersServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "createUserServlet", value = "/create-user-servlet")
public class CreateUserServlet extends HttpServlet {
    private UsersService usersService;

    public void init() {
        usersService = new UsersServiceImpl(new SqlUsersRepository());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Users newUsers = new Users(username, email, password);
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.ADMINISTRATOR) {
            try {
                usersService.createUser(newUsers);
                request.setAttribute("successMessage", "User added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error adding user.");
            }
        } else {
            request.setAttribute("errorMessage", "You do not have permission to perform this action.");
            request.getRequestDispatcher("/noPermission.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/addUser.jsp").forward(request, response);
    }
}

