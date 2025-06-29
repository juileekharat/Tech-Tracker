package com.techtracker.project_service.model.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectEvent {
    private Long id;
    private String name;
    private String description;
    private String githubUrl;
    private String status;
    private Long technologyId;

    public ProjectEvent() {
    }

    public ProjectEvent(Long id, String name, String description, String githubUrl, String status, Long technologyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.githubUrl = githubUrl;
        this.status = status;
        this.technologyId = technologyId;
    }
}
