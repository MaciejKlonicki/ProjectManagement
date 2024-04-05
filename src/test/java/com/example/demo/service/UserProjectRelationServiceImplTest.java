package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.repository.UserProjectRelationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserProjectRelationServiceImplTest {

    private UserProjectRelationRepository userProjectRelationRepository;
    private UserProjectRelationService userProjectRelationService;

    @BeforeEach
    public void setUp() {
        userProjectRelationRepository = mock(UserProjectRelationRepository.class);
        userProjectRelationService = new UserProjectRelationServiceImpl(userProjectRelationRepository);
    }

    @Test
    void shouldAssignUserRole() throws SQLException {
        UserProjectRelation userProjectRelation = new UserProjectRelation();
        userProjectRelation.setUserId(1);
        userProjectRelation.setProjectId(2);
        userProjectRelation.setRoleId(3);

        userProjectRelationService.assignUserRole(userProjectRelation);

        verify(userProjectRelationRepository).assignUserRole(userProjectRelation);
    }

    @Test
    void shouldGetProjectsForUserByUsername() throws SQLException {
        String username = "user123";
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

        when(userProjectRelationRepository.getProjectsForUserByUsername(username)).thenReturn(expectedProjects);

        List<Project> retrievedProjects = userProjectRelationService.getProjectsForUserByUsername(username);

        assertEquals(expectedProjects, retrievedProjects);
    }

    @Test
    void shouldGetProjectDetails() throws SQLException {
        String projectName = "Test Project";
        List<UserProjectRelation> expectedDetails = new ArrayList<>();
        UserProjectRelation relation1 = new UserProjectRelation();
        relation1.setUserId(1);
        relation1.setProjectId(1);
        relation1.setRoleId(2);

        UserProjectRelation relation2 = new UserProjectRelation();
        relation2.setUserId(2);
        relation2.setProjectId(1);
        relation2.setRoleId(3);

        expectedDetails.add(relation1);
        expectedDetails.add(relation2);

        when(userProjectRelationRepository.getProjectDetails(projectName)).thenReturn(expectedDetails);

        List<UserProjectRelation> retrievedDetails = userProjectRelationService.getProjectDetails(projectName);

        assertEquals(expectedDetails, retrievedDetails);
    }

    @Test
    void shouldGetDocumentDetails() throws SQLException {
        String documentName = "Test Document";
        UserProjectRelation expectedDetails = new UserProjectRelation();
        expectedDetails.setUserId(1);
        expectedDetails.setProjectId(1);
        expectedDetails.setRoleId(2);

        when(userProjectRelationRepository.getDocumentDetails(documentName)).thenReturn(expectedDetails);

        UserProjectRelation retrievedDetails = userProjectRelationService.getDocumentDetails(documentName);

        assertEquals(expectedDetails, retrievedDetails);
    }
}