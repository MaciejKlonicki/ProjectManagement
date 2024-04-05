package com.example.demo.model;

public class Document {
    private String title;
    private String description;
    private String creator;
    private String topic;
    private String content;
    private int projectId;

    public Document() {
    }

    public Document(String title, String description, String creator, String topic, String content, int projectId) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.topic = topic;
        this.content = content;
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creator='" + creator + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

