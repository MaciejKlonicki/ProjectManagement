package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;

import java.sql.SQLException;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void createProject(Project project) throws SQLException {
        projectRepository.createProject(project);
    }

    @Override
    public void deleteProject(String projectName) throws SQLException {
        projectRepository.deleteProject(projectName);
    }

    @Override
    public List<Project> getAllProjects() throws SQLException {
        return projectRepository.getAllProjects();
    }
}
