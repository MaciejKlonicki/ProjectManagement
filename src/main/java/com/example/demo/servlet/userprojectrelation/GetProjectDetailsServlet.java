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
import java.util.List;

@WebServlet(name = "getProjectDetailsServlet", value = "/get-project-details")
public class GetProjectDetailsServlet extends HttpServlet {
    private UserProjectRelationService userProjectRelationService;

    public void init() {
        userProjectRelationService = new UserProjectRelationServiceImpl(new SqlUserProjectRelationRepository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/getProjectDetails.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectName = request.getParameter("projectName");
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.ADMINISTRATOR || userRole == Role.MANAGER) {
            try {
                List<UserProjectRelation> projectDetails = userProjectRelationService.getProjectDetails(projectName);
                request.setAttribute("projectDetails", projectDetails);
                request.getRequestDispatcher("/showProjectDetails.jsp").forward(request, response);
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

