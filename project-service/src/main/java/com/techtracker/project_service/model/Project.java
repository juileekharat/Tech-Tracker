package com.techtracker.project_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name cannot be blank")
    private String name;
    private String description;
    private String githubUrl;

    @NotBlank(message = "Project status cannot be blank")
    private String status; // e.g., IN_PROGRESS, COMPLETED

    @NotNull(message = "Technology Id cannot be blank")
    private Long technologyId; // or @ManyToOne with Technology if needed
}
