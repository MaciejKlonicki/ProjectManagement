package com.example.demo.servlet.userprojectrelation;

import com.example.demo.model.Project;
import com.example.demo.model.Role;
import com.example.demo.repository.SqlProjectRepository;
import com.example.demo.repository.SqlUserProjectRelationRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.ProjectServiceImpl;
import com.example.demo.service.UserProjectRelationService;
import com.example.demo.service.UserProjectRelationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "getProjectsForUserServlet", value = "/get-projects-for-user")
public class GetProjectsForUserServlet extends HttpServlet {
    private UserProjectRelationService userProjectRelationService;

    public void init() {
        userProjectRelationService = new UserProjectRelationServiceImpl(new SqlUserProjectRelationRepository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/getProjectsForUser.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.ADMINISTRATOR || userRole == Role.MANAGER ||
                userRole == Role.ENGINEER || userRole == Role.HR) {
            try {
                List<Project> projects = userProjectRelationService.getProjectsForUserByUsername(username);
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("/showProjectsForUser.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {
            request.setAttribute("errorMessage", "You do not have permission to perform this action.");
            request.getRequestDispatcher("/noPermission.jsp").forward(request, response);
        }
    }
}
