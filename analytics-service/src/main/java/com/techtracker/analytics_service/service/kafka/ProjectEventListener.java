package com.techtracker.analytics_service.service.kafka;

import com.techtracker.analytics_service.model.event.ProjectEvent;
import com.techtracker.analytics_service.repository.ProjectEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProjectEventListener {

    private final ProjectEventRepository projectEventRepository;

    public ProjectEventListener(ProjectEventRepository projectEventRepository) {
        this.projectEventRepository = projectEventRepository;
    }

    @KafkaListener(topics = "project-events", groupId = "analytics-group")
    public void handleProjectEvent(ProjectEvent event) {
        System.out.println("Analytics received: " + event.getName());
        projectEventRepository.save(event);
    }
}
