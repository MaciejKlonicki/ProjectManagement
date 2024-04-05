package com.example.demo.model;

import java.util.List;

public class UserProjectRelation {
    private int userId;
    private int projectId;
    private int roleId;
    private List<Role> roles;

    public UserProjectRelation(int userId, int projectId, int roleId) {
        this.userId = userId;
        this.projectId = projectId;
        this.roleId = roleId;
    }

    public UserProjectRelation() {
    }

    public UserProjectRelation(int userId, int projectId, int roleId, List<Role> roles) {
        this.userId = userId;
        this.projectId = projectId;
        this.roleId = roleId;
        this.roles = roles;
    }

    public UserProjectRelation(int userId, int projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserProjectRelation{" +
                "userId=" + userId +
                ", projectId=" + projectId +
                ", roleId=" + roleId +
                ", roles=" + roles +
                '}';
    }
}

