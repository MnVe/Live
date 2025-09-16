package com.university.campusguide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "qa_bot")
public class QABot extends BaseEntity {
    
    @NotBlank
    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;
    
    @NotBlank
    @Column(name = "answer", nullable = false, columnDefinition = "TEXT")
    private String answer;
    
    @Column(name = "keywords")
    private String keywords; // 关键词，用于匹配，以逗号分隔
    
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private QACategory category;
    
    @Column(name = "priority")
    private Integer priority = 0; // 优先级，数字越大优先级越高
    
    @Column(name = "hit_count")
    private Integer hitCount = 0; // 命中次数
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    public enum QACategory {
        ADMISSION,    // 入学相关
        ACADEMIC,     // 学术相关
        LIFE,         // 生活相关
        SERVICE,      // 服务相关
        TECHNOLOGY,   // 技术相关
        OTHER         // 其他
    }
}