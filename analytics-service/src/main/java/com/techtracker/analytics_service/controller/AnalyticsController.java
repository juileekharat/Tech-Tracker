package com.techtracker.analytics_service.controller;

import com.techtracker.analytics_service.model.Project;
import com.techtracker.analytics_service.model.Technology;
import com.techtracker.analytics_service.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<Technology> getTechnologies() {
        return analyticsService.getAllTechnologies();
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return analyticsService.getAllProjects();
    }
}
