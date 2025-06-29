package com.techtracker.analytics_service.model.event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class TechnologyEvent {
    @Id
    private Long id;
    private String name;
    private Integer confidenceLevel;
    private Integer importanceScore;
    private String category;

    @ElementCollection
    @CollectionTable(name = "analytics_technology_projects", joinColumns = @JoinColumn(name = "technology_id"))
    @Column(name = "project")
    private List<String> projects;

    public TechnologyEvent() {}

    public TechnologyEvent(Long id, String name, Integer confidenceLevel, Integer importanceScore, String category, List<String> projects) {
        this.id = id;
        this.name = name;
        this.confidenceLevel = confidenceLevel;
        this.importanceScore = importanceScore;
        this.category = category;
        this.projects = projects;
    }
}
