package com.university.campusguide.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_behaviors")
public class UserBehavior extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    
    @Column(name = "target_type")
    @Enumerated(EnumType.STRING)
    private TargetType targetType;
    
    @Column(name = "target_id")
    private Long targetId;
    
    @Column(name = "duration") // 浏览时长（秒）
    private Integer duration;
    
    @Column(name = "score") // 行为得分
    private Double score = 1.0;
    
    public enum ActionType {
        VIEW,       // 浏览
        LIKE,       // 点赞
        COMMENT,    // 评论
        SHARE,      // 分享
        FAVORITE,   // 收藏
        SEARCH,     // 搜索
        DOWNLOAD    // 下载
    }
    
    public enum TargetType {
        GUIDE,      // 指南
        SERVICE,    // 服务
        POST,       // 帖子
        USER        // 用户
    }
}