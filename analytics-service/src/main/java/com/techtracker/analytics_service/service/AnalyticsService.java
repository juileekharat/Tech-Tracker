package com.techtracker.analytics_service.service;

import com.techtracker.analytics_service.model.Project;
import com.techtracker.analytics_service.model.Technology;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AnalyticsService {

    private final List<Technology> technologies = new ArrayList<>();
    private final List<Project> projects = new ArrayList<>();

    public AnalyticsService() {
        // Seed dummy tech data
        technologies.add(new Technology(1L, "Spring Boot", "Backend", 8, 10));
        technologies.add(new Technology(2L, "React", "Frontend", 6, 9));
        technologies.add(new Technology(3L, "PostgreSQL", "Database", 7, 8));

        // Seed dummy project data
        projects.add(new Project(1L, "Skill Tracker", "Track your tech skills", "https://github.com/skill", "COMPLETED", 1L));
        projects.add(new Project(2L, "Portfolio", "Personal React site", "https://github.com/portfolio", "IN_PROGRESS", 2L));
        projects.add(new Project(3L, "API Gateway", "Microservice routing", "https://github.com/gateway", "COMPLETED", 1L));
    }

    public Map<String, Object> getSummary() {
        Map<String, Object> result = new HashMap<>();

        double avgConfidence = technologies.stream()
                .mapToInt(Technology::getConfidence)
                .average()
                .orElse(0.0);

        Map<Long, Long> projectCountMap = new HashMap<>();
        for (Project p : projects) {
            projectCountMap.put(p.getTechnologyId(),
                    projectCountMap.getOrDefault(p.getTechnologyId(), 0L) + 1);
        }

        Optional<Long> mostUsedTechId = projectCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        result.put("averageConfidence", avgConfidence);
        result.put("totalTechnologies", technologies.size());
        result.put("totalProjects", projects.size());
        result.put("mostUsedTechnologyId", mostUsedTechId.orElse(null));

        return result;
    }

    public List<Technology> getAllTechnologies() {
        return technologies;
    }

    public List<Project> getAllProjects() {
        return projects;
    }
}

