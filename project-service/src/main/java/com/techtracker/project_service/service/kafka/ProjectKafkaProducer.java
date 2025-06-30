package com.techtracker.project_service.service.kafka;

import com.techtracker.project_service.model.event.ProjectEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProjectKafkaProducer {

    private final KafkaTemplate<String, ProjectEvent> kafkaTemplate;

    public ProjectKafkaProducer(KafkaTemplate<String, ProjectEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void  sendProjectEvent(ProjectEvent event) {
        kafkaTemplate.send("project-events", event);
    }
}
