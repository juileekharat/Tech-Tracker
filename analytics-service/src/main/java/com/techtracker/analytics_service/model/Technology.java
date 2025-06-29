package com.techtracker.analytics_service.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technology {
    private Long id;
    private String name;
    private String category;
    private int confidence;
    private int importance;
}
