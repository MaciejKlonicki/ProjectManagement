package com.example.demo.service;

import com.example.demo.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectService {
    void createProject(Project project) throws SQLException;
    void deleteProject(String projectName) throws SQLException;
    List<Project> getAllProjects() throws SQLException;
}
