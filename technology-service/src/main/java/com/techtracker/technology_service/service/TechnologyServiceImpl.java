package com.techtracker.technology_service.service;

import com.techtracker.technology_service.model.Technology;
import com.techtracker.technology_service.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {
    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public Technology addTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public List<Technology> getAllTechnologies() {
        return technologyRepository.findAll();
    }

    @Override
    public Technology getTechnologyById(Long id) {
        return technologyRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Technology not found with id: " + id));
    }

    @Override
    public Technology updateTechnology(Long id, Technology technology) {
        if (technologyRepository.existsById(id)) {
            technology.setId(id);
            return technologyRepository.save(technology);
        }
        throw new RuntimeException("Technology not found with id: " + id);
    }

    @Override
    public void deleteTechnology(Long id) {
        technologyRepository.deleteById(id);
    }

    @Override
    public List<Technology> getTechnologiesByCategory(String category) {
        return technologyRepository.findByCategory(category);
    }

    @Override
    public List<Technology> getTechnologiesSortedByImportance() {
        return technologyRepository.findAllByOrderByImportanceScoreDesc();
    }
}
