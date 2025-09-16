package com.university.campusguide.dto;

import com.university.campusguide.entity.Guide;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GuideDTO {
    private Long id;
    private String title;
    private String content;
    private String summary;
    private Guide.GuideCategory category;
    private List<String> tags;
    private String coverImage;
    private List<String> images;
    private Integer priority;
    private Integer viewCount;
    private Integer likeCount;
    private Guide.GuideStatus status;
    private UserDTO author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}