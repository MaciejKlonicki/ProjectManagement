package com.example.demo.servlet.project;

import com.example.demo.model.Project;
import com.example.demo.model.Role;
import com.example.demo.repository.SqlProjectRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.ProjectServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "getAllProjectsServlet", value = "/get-all-projects-servlet")
public class GetAllProjectsServlet extends HttpServlet {
    private ProjectService projectService;

    public void init() {
        projectService = new ProjectServiceImpl(new SqlProjectRepository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.ADMINISTRATOR) {
            try {
                List<Project> projects = projectService.getAllProjects();
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("getAllProjects.jsp").forward(request, response);
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
