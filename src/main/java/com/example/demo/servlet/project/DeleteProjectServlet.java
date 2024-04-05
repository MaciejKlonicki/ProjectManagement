package com.example.demo.servlet.project;

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

@WebServlet(name = "deleteProjectServlet", value = "/delete-project-servlet")
public class DeleteProjectServlet extends HttpServlet {
    private ProjectService projectService;

    public void init() {
        projectService = new ProjectServiceImpl(new SqlProjectRepository());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectName = request.getParameter("name");
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.ADMINISTRATOR || userRole == Role.MANAGER) {
            try {
                projectService.deleteProject(projectName);
                request.setAttribute("successMessage", "Project deleted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error to delete project.");
            }
        } else {
            request.setAttribute("errorMessage", "You do not have permission to perform this action.");
            request.getRequestDispatcher("/noPermission.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/deleteProject.jsp").forward(request, response);
    }
}
