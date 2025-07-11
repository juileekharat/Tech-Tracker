package com.techtracker.technology_service.controller;

import com.techtracker.technology_service.model.Technology;
import com.techtracker.technology_service.model.event.TechnologyEvent;
import com.techtracker.technology_service.service.TechnologyService;
import com.techtracker.technology_service.service.kafka.TechnologyKafkaProducer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;
    private final TechnologyKafkaProducer producer;

    public TechnologyController(TechnologyService technologyService, TechnologyKafkaProducer producer) {
        this.technologyService = technologyService;
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Technology> addTechnology(@Valid @RequestBody Technology technology) {
        Technology created = technologyService.addTechnology(technology);

        TechnologyEvent event = new TechnologyEvent(
            created.getId(),
            created.getName(),
            created.getConfidenceLevel(),
            created.getImportanceScore(),
            created.getCategory(),
            created.getProjects()
        );
        producer.sendTechnologyEvent(event);

        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Technology>> getAllTechnologies() {
        List<Technology> list = technologyService.getAllTechnologies();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Long id) {
        Technology tech = technologyService.getTechnologyById(id);
        if (tech == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tech);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable Long id, @RequestBody Technology technology) {
        Technology updated = technologyService.updateTechnology(id, technology);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        technologyService.deleteTechnology(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Technology>> getTechnologiesByCategory(@PathVariable String category) {
        List<Technology> list = technologyService.getTechnologiesByCategory(category);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/important")
    public ResponseEntity<List<Technology>> getTechnologiesSortedByImportance() {
        List<Technology> list = technologyService.getTechnologiesSortedByImportance();
        return ResponseEntity.ok(list);
    }
}