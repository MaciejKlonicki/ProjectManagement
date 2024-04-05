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

@WebServlet(name = "getDocumentDetailsServlet", value = "/get-document-details")
public class GetDocumentDetailsServlet extends HttpServlet {
    private UserProjectRelationService userProjectRelationService;

    public void init() {
        userProjectRelationService = new UserProjectRelationServiceImpl(new SqlUserProjectRelationRepository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/getDocumentDetails.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String documentName = request.getParameter("documentName");
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.MANAGER ||
                userRole == Role.ENGINEER || userRole == Role.HR) {
            try {
                UserProjectRelation documentDetails = userProjectRelationService.getDocumentDetails(documentName);
                request.setAttribute("documentDetails", documentDetails);
                request.getRequestDispatcher("/showDocumentDetails.jsp").forward(request, response);
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
