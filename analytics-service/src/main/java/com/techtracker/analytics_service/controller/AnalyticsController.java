package com.techtracker.analytics_service.controller;

import com.techtracker.analytics_service.model.Project;
import com.techtracker.analytics_service.model.event.ProjectEvent;
import com.techtracker.analytics_service.model.event.TechnologyEvent;
import com.techtracker.analytics_service.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        return analyticsService.getSummary();
    }

    @GetMapping("/technologies")
    public List<TechnologyEvent> getTechnologies() {
        return analyticsService.getAllTechnologies();
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        // Map ProjectEvent to Project for API compatibility
        return analyticsService.getAllProjects().stream()
                .map(e -> new Project(
                        e.getId(),
                        e.getName(),
                        e.getDescription(),
                        e.getGithubUrl(),
                        e.getStatus(),
                        e.getTechnologyId()
                ))
                .collect(Collectors.toList());
    }
}
