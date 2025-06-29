package com.techtracker.analytics_service.service.kafka;

import com.techtracker.analytics_service.model.event.TechnologyEvent;
import com.techtracker.analytics_service.repository.TechnologyEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TechnologyEventListener {

    private final TechnologyEventRepository technologyEventRepository;

    public TechnologyEventListener(TechnologyEventRepository technologyEventRepository) {
        this.technologyEventRepository = technologyEventRepository;
    }

    @KafkaListener(topics = "technology-events", groupId = "analytics-group", containerFactory = "technologyKafkaListenerContainerFactory")
    public void handleTechnologyEvent(TechnologyEvent event) {
        System.out.println("Analytics received technology: " + event.getName());
        technologyEventRepository.save(event);
    }
}
