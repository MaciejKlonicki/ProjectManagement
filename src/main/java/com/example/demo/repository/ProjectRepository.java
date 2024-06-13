package com.example.demo.repository;

import com.example.demo.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectRepository {
    void createProject(Project project) throws SQLException;
    void deleteProject(String projectName) throws SQLException;
    List<Project> getAllProjects() throws SQLException;
    void updateProjectDescription(String projectName, String newDescription) throws SQLException;
}
