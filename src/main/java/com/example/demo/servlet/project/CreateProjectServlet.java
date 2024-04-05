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

@WebServlet(name = "createProjectServlet", value = "/create-project-servlet")
public class CreateProjectServlet extends HttpServlet {
    private ProjectService projectService;

    public void init() {
        projectService = new ProjectServiceImpl(new SqlProjectRepository());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String creator = request.getParameter("creator");

        Project newProject = new Project(name, description, creator);
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.ADMINISTRATOR) {
            try {
                projectService.createProject(newProject);
                request.setAttribute("successMessage", "Project added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error adding project.");
            }
        } else {
            request.setAttribute("errorMessage", "You do not have permission to perform this action.");
            request.getRequestDispatcher("/noPermission.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/addProject.jsp").forward(request, response);
    }
}
