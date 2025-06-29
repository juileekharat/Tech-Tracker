package com.techtracker.project_service.service;

import com.techtracker.project_service.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project);
    Optional<Project> getProjectById(Long id);
    List<Project> getAllProjects();
    Project updateProject(Long id, Project project);
    void deleteProject(Long id);
}
