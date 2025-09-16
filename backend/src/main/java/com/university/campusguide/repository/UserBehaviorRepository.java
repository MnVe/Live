package com.university.campusguide.repository;

import com.university.campusguide.entity.UserBehavior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserBehaviorRepository extends JpaRepository<UserBehavior, Long> {
    
    @Query("SELECT ub FROM UserBehavior ub WHERE ub.user.id = :userId AND ub.deleted = false " +
           "ORDER BY ub.createdAt DESC")
    List<UserBehavior> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT ub FROM UserBehavior ub WHERE ub.user.id = :userId AND ub.targetType = :targetType " +
           "AND ub.deleted = false ORDER BY ub.createdAt DESC")
    List<UserBehavior> findByUserIdAndTargetType(@Param("userId") Long userId, 
                                                  @Param("targetType") UserBehavior.TargetType targetType);
    
    @Query("SELECT ub.targetId, COUNT(ub) as count FROM UserBehavior ub " +
           "WHERE ub.targetType = :targetType AND ub.actionType = :actionType " +
           "AND ub.createdAt >= :startTime AND ub.deleted = false " +
           "GROUP BY ub.targetId ORDER BY count DESC")
    List<Object[]> findPopularTargets(@Param("targetType") UserBehavior.TargetType targetType,
                                      @Param("actionType") UserBehavior.ActionType actionType,
                                      @Param("startTime") LocalDateTime startTime);
    
    @Query("SELECT ub FROM UserBehavior ub WHERE ub.user.id = :userId AND ub.targetType = :targetType " +
           "AND ub.targetId = :targetId AND ub.actionType = :actionType AND ub.deleted = false")
    UserBehavior findByUserAndTarget(@Param("userId") Long userId,
                                     @Param("targetType") UserBehavior.TargetType targetType,
                                     @Param("targetId") Long targetId,
                                     @Param("actionType") UserBehavior.ActionType actionType);
}