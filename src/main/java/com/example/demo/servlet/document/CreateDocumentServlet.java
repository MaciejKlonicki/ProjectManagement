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

@WebServlet(name = "createDocumentServlet", value = "/create-document-servlet")
public class CreateDocumentServlet extends HttpServlet {
    private DocumentService documentService;

    public void init() {
        documentService = new DocumentServiceImpl(new SqlDocumentRepository());
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String creator = request.getParameter("creator");
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        String projectIDParam = request.getParameter("project_id");
        int projectID = Integer.parseInt(projectIDParam);

        Document newDocument = new Document(title, description, creator, topic, content, projectID);
        Role userRole = (Role) request.getSession().getAttribute("userRole");

        if (userRole != null && userRole == Role.MANAGER || userRole == Role.ENGINEER) {
            try {
                documentService.createDocument(newDocument);
                request.setAttribute("successMessage", "Document added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error adding document.");
            }
        } else {
            request.setAttribute("errorMessage", "You do not have permission to perform this action.");
            request.getRequestDispatcher("/noPermission.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/addDocument.jsp").forward(request, response);
    }
}
