package com.techtracker.project_service.repository;

import com.techtracker.project_service.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
