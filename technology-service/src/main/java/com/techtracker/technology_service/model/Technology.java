package com.techtracker.technology_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Technology Name is required")
    private String name;

    private Integer confidenceLevel;

    private Integer importanceScore;

    private String category;

    @ElementCollection
    @CollectionTable(name = "technology_projects", joinColumns = @JoinColumn(name = "technology_id"))
    @Column(name = "project")
    private List<String> projects;
}
