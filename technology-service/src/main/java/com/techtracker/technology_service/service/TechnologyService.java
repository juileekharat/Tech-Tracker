package com.techtracker.technology_service.service;

import com.techtracker.technology_service.model.Technology;
import java.util.List;

public interface TechnologyService {
    Technology addTechnology(Technology technology);

    List<Technology> getAllTechnologies();

    Technology getTechnologyById(Long id);

    Technology updateTechnology(Long id, Technology technology);

    void deleteTechnology(Long id);

    List<Technology> getTechnologiesByCategory(String category);

    List<Technology> getTechnologiesSortedByImportance();
}
