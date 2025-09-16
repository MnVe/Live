package com.university.campusguide.service;

import com.university.campusguide.dto.GuideDTO;
import com.university.campusguide.entity.*;
import com.university.campusguide.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendationService {
    
    private final UserBehaviorRepository userBehaviorRepository;
    private final GuideRepository guideRepository;
    private final UserRepository userRepository;
    private final GuideService guideService;
    
    // 记录用户行为
    public void recordUserBehavior(Long userId, UserBehavior.ActionType actionType, 
                                   UserBehavior.TargetType targetType, Long targetId, 
                                   Integer duration) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return;
        
        UserBehavior behavior = new UserBehavior();
        behavior.setUser(user);
        behavior.setActionType(actionType);
        behavior.setTargetType(targetType);
        behavior.setTargetId(targetId);
        behavior.setDuration(duration);
        
        // 计算行为得分
        double score = calculateActionScore(actionType, duration);
        behavior.setScore(score);
        
        userBehaviorRepository.save(behavior);
    }
    
    // 计算行为得分
    private double calculateActionScore(UserBehavior.ActionType actionType, Integer duration) {
        double baseScore = switch (actionType) {
            case VIEW -> 1.0;
            case LIKE -> 3.0;
            case COMMENT -> 5.0;
            case SHARE -> 4.0;
            case FAVORITE -> 6.0;
            case SEARCH -> 2.0;
            case DOWNLOAD -> 7.0;
        };
        
        // 根据浏览时长调整得分
        if (actionType == UserBehavior.ActionType.VIEW && duration != null) {
            if (duration > 60) baseScore *= 1.5; // 浏览超过1分钟
            else if (duration > 30) baseScore *= 1.2; // 浏览超过30秒
        }
        
        return baseScore;
    }
    
    // 获取个性化推荐指南
    public List<GuideDTO> getRecommendedGuides(Long userId, int limit) {
        // 获取用户行为历史
        List<UserBehavior> userBehaviors = userBehaviorRepository.findByUserId(userId);
        
        if (userBehaviors.isEmpty()) {
            // 如果没有行为历史，返回热门指南
            return getPopularGuides(limit);
        }
        
        // 基于协同过滤的推荐
        Map<Long, Double> guideScores = new HashMap<>();
        
        // 1. 基于用户历史行为的内容推荐
        for (UserBehavior behavior : userBehaviors) {
            if (behavior.getTargetType() == UserBehavior.TargetType.GUIDE) {
                Guide guide = guideRepository.findById(behavior.getTargetId()).orElse(null);
                if (guide != null && guide.getStatus() == Guide.GuideStatus.PUBLISHED) {
                    // 找到相似的指南（基于分类）
                    List<Guide> similarGuides = guideRepository.findByCategory(guide.getCategory(), 
                            PageRequest.of(0, 10)).getContent();
                    
                    for (Guide similarGuide : similarGuides) {
                        if (!similarGuide.getId().equals(behavior.getTargetId())) {
                            guideScores.merge(similarGuide.getId(), 
                                    behavior.getScore() * 0.8, Double::sum);
                        }
                    }
                }
            }
        }
        
        // 2. 热门内容推荐
        List<Object[]> popularGuides = userBehaviorRepository.findPopularTargets(
                UserBehavior.TargetType.GUIDE, 
                UserBehavior.ActionType.VIEW,
                LocalDateTime.now().minusDays(7));
        
        for (Object[] result : popularGuides) {
            Long guideId = (Long) result[0];
            Long count = (Long) result[1];
            guideScores.merge(guideId, count * 0.5, Double::sum);
        }
        
        // 排序并获取推荐结果
        List<Long> recommendedGuideIds = guideScores.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(limit)
                .collect(Collectors.toList());
        
        List<GuideDTO> recommendedGuides = new ArrayList<>();
        for (Long guideId : recommendedGuideIds) {
            try {
                GuideDTO guide = guideService.getGuideById(guideId);
                recommendedGuides.add(guide);
            } catch (Exception e) {
                // 忽略不存在的指南
            }
        }
        
        // 如果推荐结果不够，用热门指南补充
        if (recommendedGuides.size() < limit) {
            List<GuideDTO> popularGuideDTOs = getPopularGuides(limit - recommendedGuides.size());
            for (GuideDTO popular : popularGuideDTOs) {
                if (recommendedGuides.stream().noneMatch(g -> g.getId().equals(popular.getId()))) {
                    recommendedGuides.add(popular);
                }
            }
        }
        
        return recommendedGuides;
    }
    
    // 获取热门指南
    private List<GuideDTO> getPopularGuides(int limit) {
        return guideService.getPopularGuides(limit);
    }
    
    // 获取用户可能感兴趣的分类
    public List<Guide.GuideCategory> getRecommendedCategories(Long userId) {
        List<UserBehavior> behaviors = userBehaviorRepository.findByUserIdAndTargetType(
                userId, UserBehavior.TargetType.GUIDE);
        
        Map<Guide.GuideCategory, Double> categoryScores = new HashMap<>();
        
        for (UserBehavior behavior : behaviors) {
            Guide guide = guideRepository.findById(behavior.getTargetId()).orElse(null);
            if (guide != null) {
                categoryScores.merge(guide.getCategory(), behavior.getScore(), Double::sum);
            }
        }
        
        return categoryScores.entrySet().stream()
                .sorted(Map.Entry.<Guide.GuideCategory, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(5)
                .collect(Collectors.toList());
    }
    
    // 检查用户是否对某个内容有过行为
    public boolean hasUserInteracted(Long userId, UserBehavior.TargetType targetType, 
                                     Long targetId, UserBehavior.ActionType actionType) {
        return userBehaviorRepository.findByUserAndTarget(userId, targetType, targetId, actionType) != null;
    }
}