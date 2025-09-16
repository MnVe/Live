package com.university.campusguide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "campus_services")
public class CampusService extends BaseEntity {
    
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ServiceCategory category;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "contact_info")
    private String contactInfo;
    
    @Column(name = "working_hours")
    private String workingHours;
    
    @Column(name = "icon")
    private String icon;
    
    @Column(name = "images")
    private String images; // JSON array as string
    
    @Column(name = "rating")
    private Double rating = 0.0;
    
    @Column(name = "rating_count")
    private Integer ratingCount = 0;
    
    @Column(name = "is_online")
    private Boolean isOnline = false;
    
    @Column(name = "url")
    private String url;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ServiceStatus status = ServiceStatus.ACTIVE;
    
    @Column(name = "priority")
    private Integer priority = 0;
    
    public enum ServiceCategory {
        DINING, // 餐饮服务
        ACCOMMODATION, // 住宿服务
        TRANSPORTATION, // 交通服务
        MEDICAL, // 医疗服务
        LIBRARY, // 图书馆服务
        SPORTS, // 体育服务
        SHOPPING, // 购物服务
        ENTERTAINMENT, // 娱乐服务
        ACADEMIC, // 学术服务
        ADMINISTRATIVE, // 行政服务
        FINANCIAL, // 金融服务
        TECHNOLOGY, // 技术服务
        OTHER // 其他服务
    }
    
    public enum ServiceStatus {
        ACTIVE, INACTIVE, MAINTENANCE
    }
}