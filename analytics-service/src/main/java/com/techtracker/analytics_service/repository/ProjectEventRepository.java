package com.techtracker.analytics_service.repository;

import com.techtracker.analytics_service.model.event.ProjectEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEventRepository extends JpaRepository<ProjectEvent, Long> {
}
