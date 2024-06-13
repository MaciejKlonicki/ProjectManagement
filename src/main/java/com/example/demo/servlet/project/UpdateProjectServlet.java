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
            //request.setAttribute("newDescription", newDescription); // TODO: Przesyłanie raw data dalej "<script>alert('XSS');</script>"
            request.setAttribute("newDescription", escapeHtml(newDescription)); // TODO: Sanityzacja danych przed wysłaniem
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating project description.");
        }
        request.getRequestDispatcher("/updateProject.jsp").forward(request, response);
    }


    private String escapeHtml(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder escaped = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '&' -> escaped.append("&amp;");
                case '<' -> escaped.append("&lt;");
                case '>' -> escaped.append("&gt;");
                case '"' -> escaped.append("&quot;");
                case '\'' -> escaped.append("&#x27;");
                case '/' -> escaped.append("&#x2F;");
                default -> escaped.append(c);
            }
        }
        return escaped.toString();
    }
}
