package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.model.UserProjectRelation;
import com.example.demo.repository.UserProjectRelationRepository;

import java.sql.SQLException;
import java.util.List;

public class UserProjectRelationServiceImpl implements UserProjectRelationService {
    private final UserProjectRelationRepository userProjectRelationRepository;

    public UserProjectRelationServiceImpl(UserProjectRelationRepository userProjectRelationRepository) {
        this.userProjectRelationRepository = userProjectRelationRepository;
    }

    @Override
    public void assignUserRole(UserProjectRelation userProjectRelation) throws SQLException {
        userProjectRelationRepository.assignUserRole(userProjectRelation);
    }

    @Override
    public List<Project> getProjectsForUserByUsername(String username) throws SQLException {
        return userProjectRelationRepository.getProjectsForUserByUsername(username);
    }

    @Override
    public List<UserProjectRelation> getProjectDetails(String projectName) throws SQLException {
        return userProjectRelationRepository.getProjectDetails(projectName);
    }

    @Override
    public UserProjectRelation getDocumentDetails(String documentName) throws SQLException {
        return userProjectRelationRepository.getDocumentDetails(documentName);
    }
}
