package com.university.campusguide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "guides")
public class Guide extends BaseEntity {
    
    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;
    
    @NotBlank
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    
    @Column(name = "summary", length = 500)
    private String summary;
    
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private GuideCategory category;
    
    @Column(name = "tags")
    private String tags; // JSON array as string
    
    @Column(name = "cover_image")
    private String coverImage;
    
    @Column(name = "images")
    private String images; // JSON array as string
    
    @Column(name = "priority")
    private Integer priority = 0;
    
    @Column(name = "view_count")
    private Integer viewCount = 0;
    
    @Column(name = "like_count")
    private Integer likeCount = 0;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GuideStatus status = GuideStatus.DRAFT;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuideComment> comments;
    
    public enum GuideCategory {
        ADMISSION, // 入学指南
        ACADEMIC, // 学习指导
        LIFE, // 生活服务
        ACTIVITY, // 活动信息
        CAREER, // 就业指导
        FINANCE, // 财务相关
        HEALTH, // 健康服务
        TECHNOLOGY, // 技术支持
        OTHER // 其他
    }
    
    public enum GuideStatus {
        DRAFT, PUBLISHED, ARCHIVED
    }
}