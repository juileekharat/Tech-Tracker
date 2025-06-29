package com.techtracker.analytics_service.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long id;
    private String name;
    private String description;
    private String githubUrl;
    private String status;      // e.g., IN_PROGRESS, COMPLETED
    private Long technologyId;
}
