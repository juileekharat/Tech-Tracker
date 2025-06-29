package com.techtracker.analytics_service.repository;

import com.techtracker.analytics_service.model.event.TechnologyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyEventRepository extends JpaRepository<TechnologyEvent, Long> {
}
