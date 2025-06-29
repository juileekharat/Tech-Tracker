package com.techtracker.project_service.service;

import com.techtracker.project_service.model.Project;
import com.techtracker.project_service.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Long id, Project project) {
        return projectRepository.findById(id)
            .map(existingProject -> {
                existingProject.setName(project.getName());
                existingProject.setDescription(project.getDescription());
                // ...update other fields as needed...
                return projectRepository.save(existingProject);
            })
            .orElseThrow(() -> new RuntimeException("Project not found with id " + id));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
