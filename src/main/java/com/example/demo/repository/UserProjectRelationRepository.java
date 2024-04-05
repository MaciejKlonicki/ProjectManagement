package com.example.demo.repository;

import com.example.demo.model.Project;
import com.example.demo.model.UserProjectRelation;

import java.sql.SQLException;
import java.util.List;

public interface UserProjectRelationRepository {
    void assignUserRole(UserProjectRelation userProjectRelation) throws SQLException;
    List<Project> getProjectsForUserByUsername(String username) throws SQLException;
    List<UserProjectRelation> getProjectDetails(String projectName) throws SQLException;
    UserProjectRelation getDocumentDetails(String documentName) throws SQLException;
}
