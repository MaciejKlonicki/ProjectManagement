package com.example.demo.servlet.userprojectrelation;

import com.example.demo.model.Role;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.repository.SqlUserProjectRelationRepository;
import com.example.demo.service.UserProjectRelationService;
import com.example.demo.service.UserProjectRelationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AssignUserNewRoleServlet", value = "/new-role-servlet")
public class AssignUserNewRoleServlet extends HttpServlet {
    private UserProjectRelationService userProjectRelationService;

    public void init() {
        userProjectRelationService =
                new UserProjectRelationServiceImpl(new SqlUserProjectRelationRepository());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int projectId = Integer.parseInt(request.getParameter("project_id"));
        int roleId = Integer.parseInt(request.getParameter("role_id"));

        UserProjectRelation newUserProjectRelation = new UserProjectRelation(userId, projectId, roleId);
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.ADMINISTRATOR || userRole == Role.MANAGER) {
            try {
                userProjectRelationService.assignUserRole(newUserProjectRelation);
                request.setAttribute("successMessage", "Role added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error adding role.");
            }
        } else {
            request.setAttribute("errorMessage", "You do not have permission to perform this action.");
            request.getRequestDispatcher("/noPermission.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/assignNewRole.jsp").forward(request, response);
    }
}
