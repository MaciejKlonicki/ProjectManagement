package com.example.demo.servlet.project;

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

@WebServlet(name = "updateProjectServlet", value = "/update-project-servlet")
public class UpdateProjectServlet extends HttpServlet {
    private ProjectService projectService;

    public void init() {
        projectService = new ProjectServiceImpl(new SqlProjectRepository());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectName = request.getParameter("name");
        String newDescription = request.getParameter("description");

        try {
            projectService.updateProjectDescription(projectName, newDescription);
            request.setAttribute("successMessage", "Project description updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating project description.");
        }
        request.getRequestDispatcher("/updateProject.jsp").forward(request, response);
    }
}