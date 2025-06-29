package com.techtracker.technology_service.service.kafka;

import com.techtracker.technology_service.model.event.TechnologyEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TechnologyKafkaProducer {

    private final KafkaTemplate<String, TechnologyEvent> kafkaTemplate;

    public TechnologyKafkaProducer(KafkaTemplate<String, TechnologyEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTechnologyEvent(TechnologyEvent event) {
        kafkaTemplate.send("technology-events", event);
    }
}
