package com.techtracker.technology_service.repository;

import com.techtracker.technology_service.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    List<Technology> findAllByOrderByImportanceScoreDesc();
    List<Technology> findByCategory(String category);
}
