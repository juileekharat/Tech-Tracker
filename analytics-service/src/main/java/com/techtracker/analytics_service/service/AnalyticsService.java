package com.techtracker.analytics_service.service;

import com.techtracker.analytics_service.model.event.ProjectEvent;
import com.techtracker.analytics_service.model.event.TechnologyEvent;
import com.techtracker.analytics_service.repository.ProjectEventRepository;
import com.techtracker.analytics_service.repository.TechnologyEventRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyticsService {

    private final TechnologyEventRepository technologyEventRepository;
    private final ProjectEventRepository projectEventRepository;

    public AnalyticsService(ProjectEventRepository projectEventRepository, TechnologyEventRepository technologyEventRepository) {
        this.projectEventRepository = projectEventRepository;
        this.technologyEventRepository = technologyEventRepository;
    }

    public Map<String, Object> getSummary() {
        Map<String, Object> result = new HashMap<>();

        List<TechnologyEvent> technologies = technologyEventRepository.findAll();

        double avgConfidence = technologies.stream()
                .mapToInt(TechnologyEvent::getConfidenceLevel)
                .average()
                .orElse(0.0);

        List<ProjectEvent> projects = projectEventRepository.findAll();

        Map<Long, Long> projectCountMap = new HashMap<>();
        for (ProjectEvent p : projects) {
            projectCountMap.put(p.getId(),
                    projectCountMap.getOrDefault(p.getId(), 0L) + 1);
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

    public List<TechnologyEvent> getAllTechnologies() {
        return technologyEventRepository.findAll();
    }

    public List<ProjectEvent> getAllProjects() {
        return projectEventRepository.findAll();
    }
}
