package com.techtracker.technology_service.model.event;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyEvent {
    private Long id;
    private String name;
    private Integer confidenceLevel;
    private Integer importanceScore;
    private String category;
    private List<String> projects;
}
