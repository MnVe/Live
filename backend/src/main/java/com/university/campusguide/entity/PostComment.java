package com.university.campusguide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "post_comments")
public class PostComment extends BaseEntity {
    
    @NotBlank
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PostComment parent;
    
    @Column(name = "like_count")
    private Integer likeCount = 0;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CommentStatus status = CommentStatus.ACTIVE;
    
    public enum CommentStatus {
        ACTIVE, HIDDEN, DELETED
    }
}