package com.techtracker.project_service.controller;

import com.techtracker.project_service.model.Project;
import com.techtracker.project_service.model.event.ProjectEvent;
import com.techtracker.project_service.service.ProjectService;

import com.techtracker.project_service.service.kafka.ProjectKafkaProducer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectKafkaProducer producer;

    public ProjectController(ProjectService projectService, ProjectKafkaProducer producer) {
        this.projectService = projectService;
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
        Project created = projectService.createProject(project);

        // Send project event to Kafka
        ProjectEvent event = new ProjectEvent(
            created.getId(),
            created.getName(),
            created.getDescription(),
            created.getGithubUrl(),
            created.getStatus(),
            created.getTechnologyId()
        );
        producer.sendProjectEvent(event);

        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @Valid @RequestBody Project project) {
        try {
            Project updated = projectService.updateProject(id, project);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
