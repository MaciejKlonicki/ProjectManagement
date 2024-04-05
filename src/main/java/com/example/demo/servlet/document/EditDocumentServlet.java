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

@WebServlet(name = "editDocumentServlet", value = "/edit-document")
public class EditDocumentServlet extends HttpServlet {
    private DocumentService documentService;

    public void init() {
        documentService = new DocumentServiceImpl(new SqlDocumentRepository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");

        try {
            Document document = documentService.getDocumentByTitle(title);
            if (document != null) {
                request.setAttribute("document", document);
                request.getRequestDispatcher("/editDocument.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/chooseDocument.jsp?error=Document not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/chooseDocument.jsp?error=Database error");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String creator = request.getParameter("creator");
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        int projectId = Integer.parseInt(request.getParameter("project_id"));

        Document updatedDocument = new Document(title, description, creator, topic, content, projectId);
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.MANAGER || userRole == Role.ENGINEER) {
            try {
                documentService.editDocument(updatedDocument);
                request.setAttribute("successMessage", "Document edited successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error to edit document.");
            }
        } else {
            request.setAttribute("errorMessage", "You do not have permission to perform this action.");
            request.getRequestDispatcher("/noPermission.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/editDocument.jsp").forward(request, response);
    }
}




