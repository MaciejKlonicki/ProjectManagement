package com.example.demo.servlet.document;

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

@WebServlet(name = "deleteDocumentServlet", value = "/delete-document-servlet")
public class DeleteDocumentServlet extends HttpServlet {
    private DocumentService documentService;

    public void init() {
        documentService = new DocumentServiceImpl(new SqlDocumentRepository());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.MANAGER) {
            try {
                documentService.deleteDocument(title);
                request.setAttribute("successMessage", "Document deleted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error to delete document.");
            }
        } else {
            request.setAttribute("errorMessage", "You do not have permission to perform this action.");
            request.getRequestDispatcher("/noPermission.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/deleteDocument.jsp").forward(request, response);
    }
}
