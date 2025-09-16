package com.university.campusguide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    
    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;
    
    @NotBlank
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private PostCategory category;
    
    @Column(name = "tags")
    private String tags; // JSON array as string
    
    @Column(name = "images")
    private String images; // JSON array as string
    
    @Column(name = "view_count")
    private Integer viewCount = 0;
    
    @Column(name = "like_count")
    private Integer likeCount = 0;
    
    @Column(name = "comment_count")
    private Integer commentCount = 0;
    
    @Column(name = "is_pinned")
    private Boolean isPinned = false;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PostStatus status = PostStatus.PUBLISHED;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostComment> comments;
    
    public enum PostCategory {
        DISCUSSION, // 讨论交流
        QUESTION, // 问题求助
        SHARING, // 经验分享
        ACTIVITY, // 活动发布
        LOST_FOUND, // 失物招领
        MARKET, // 二手市场
        ANNOUNCEMENT, // 公告通知
        OTHER // 其他
    }
    
    public enum PostStatus {
        DRAFT, PUBLISHED, ARCHIVED, DELETED
    }
}