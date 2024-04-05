package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    private ProjectRepository projectRepository;
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        projectRepository = mock(ProjectRepository.class);
        projectService = new ProjectServiceImpl(projectRepository);
    }

    @Test
    void shouldCreateProject() throws SQLException {
        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setCreator("Test Creator");

        projectService.createProject(project);

        verify(projectRepository).createProject(project);
    }

    @Test
    void shouldDeleteProject() throws SQLException {
        String projectNameToDelete = "Test Project";

        projectService.deleteProject(projectNameToDelete);

        verify(projectRepository).deleteProject(projectNameToDelete);
    }

    @Test
    void shouldGetAllProjects() throws SQLException {
        List<Project> expectedProjects = new ArrayList<>();
        Project project1 = new Project();
        project1.setName("Project 1");
        project1.setDescription("Description 1");
        project1.setCreator("Creator 1");

        Project project2 = new Project();
        project2.setName("Project 2");
        project2.setDescription("Description 2");
        project2.setCreator("Creator 2");

        expectedProjects.add(project1);
        expectedProjects.add(project2);

        when(projectRepository.getAllProjects()).thenReturn(expectedProjects);

        List<Project> retrievedProjects = projectService.getAllProjects();

        assertEquals(expectedProjects, retrievedProjects);
    }
}