package com.university.campusguide.dto;

import com.university.campusguide.entity.CampusService;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CampusServiceDTO {
    private Long id;
    private String name;
    private String description;
    private CampusService.ServiceCategory category;
    private String location;
    private String contactInfo;
    private String workingHours;
    private String icon;
    private List<String> images;
    private Double rating;
    private Integer ratingCount;
    private Boolean isOnline;
    private String url;
    private CampusService.ServiceStatus status;
    private Integer priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}