package com.example.demo.servlet.document;

import com.example.demo.model.Document;
import com.example.demo.model.Role;
import com.example.demo.repository.SqlDocumentRepository;
import com.example.demo.service.DocumentService;
import com.example.demo.service.DocumentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "getDocumentsForUserServlet", value = "/get-documents-for-user")
public class GetDocumentsForUserServlet extends HttpServlet {
    private DocumentService documentService;

    public void init() {
        documentService = new DocumentServiceImpl(new SqlDocumentRepository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/getDocumentsForUser.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.MANAGER ||
                userRole == Role.ENGINEER || userRole == Role.HR) {
            try {
                List<Document> documents = documentService.showDocumentsForUser(username);
                request.setAttribute("documents", documents);
                request.getRequestDispatcher("/showDocumentsForUser.jsp").forward(request, response);
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

