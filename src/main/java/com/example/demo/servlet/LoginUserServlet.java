package com.example.demo.servlet;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.UserProjectRelation;
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
import java.util.Optional;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginUserServlet extends HttpServlet {
    private UsersService usersService;
    public void init() {
        usersService = new UsersServiceImpl(new SqlUsersRepository());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        try {
            Optional<UserProjectRelation> user = usersService.authenticateUser(userLoginDTO);

            if (user.isPresent()) {
                request.getSession().setAttribute("userRole", user.get().getRoles().get(0));
                response.sendRedirect(request.getContextPath() + "/welcomePage.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
    public void destroy() {

    }
}
