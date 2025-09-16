package com.university.campusguide.controller;

import com.university.campusguide.dto.ApiResponse;
import com.university.campusguide.dto.GuideDTO;
import com.university.campusguide.entity.Guide;
import com.university.campusguide.entity.UserBehavior;
import com.university.campusguide.security.UserPrincipal;
import com.university.campusguide.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecommendationController {
    
    private final RecommendationService recommendationService;
    
    @GetMapping("/guides")
    public ResponseEntity<ApiResponse<List<GuideDTO>>> getRecommendedGuides(
            @AuthenticationPrincipal UserPrincipal currentUser,
            @RequestParam(defaultValue = "10") int limit) {
        
        List<GuideDTO> recommendedGuides = recommendationService.getRecommendedGuides(
                currentUser.getId(), limit);
        return ResponseEntity.ok(ApiResponse.success(recommendedGuides));
    }
    
    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<Guide.GuideCategory>>> getRecommendedCategories(
            @AuthenticationPrincipal UserPrincipal currentUser) {
        
        List<Guide.GuideCategory> categories = recommendationService.getRecommendedCategories(
                currentUser.getId());
        return ResponseEntity.ok(ApiResponse.success(categories));
    }
    
    @PostMapping("/behavior")
    public ResponseEntity<ApiResponse<String>> recordBehavior(
            @AuthenticationPrincipal UserPrincipal currentUser,
            @RequestBody BehaviorRequest request) {
        
        recommendationService.recordUserBehavior(
                currentUser.getId(),
                request.getActionType(),
                request.getTargetType(),
                request.getTargetId(),
                request.getDuration()
        );
        
        return ResponseEntity.ok(ApiResponse.success("行为记录成功", null));
    }
    
    @GetMapping("/check-interaction")
    public ResponseEntity<ApiResponse<Boolean>> checkUserInteraction(
            @AuthenticationPrincipal UserPrincipal currentUser,
            @RequestParam UserBehavior.TargetType targetType,
            @RequestParam Long targetId,
            @RequestParam UserBehavior.ActionType actionType) {
        
        boolean hasInteracted = recommendationService.hasUserInteracted(
                currentUser.getId(), targetType, targetId, actionType);
        
        return ResponseEntity.ok(ApiResponse.success(hasInteracted));
    }
    
    // 内部类：行为记录请求
    public static class BehaviorRequest {
        private UserBehavior.ActionType actionType;
        private UserBehavior.TargetType targetType;
        private Long targetId;
        private Integer duration;
        
        // Getters and Setters
        public UserBehavior.ActionType getActionType() {
            return actionType;
        }
        
        public void setActionType(UserBehavior.ActionType actionType) {
            this.actionType = actionType;
        }
        
        public UserBehavior.TargetType getTargetType() {
            return targetType;
        }
        
        public void setTargetType(UserBehavior.TargetType targetType) {
            this.targetType = targetType;
        }
        
        public Long getTargetId() {
            return targetId;
        }
        
        public void setTargetId(Long targetId) {
            this.targetId = targetId;
        }
        
        public Integer getDuration() {
            return duration;
        }
        
        public void setDuration(Integer duration) {
            this.duration = duration;
        }
    }
}