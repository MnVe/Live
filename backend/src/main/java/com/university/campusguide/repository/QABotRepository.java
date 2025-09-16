package com.university.campusguide.repository;

import com.university.campusguide.entity.QABot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QABotRepository extends JpaRepository<QABot, Long> {
    
    @Query("SELECT q FROM QABot q WHERE q.deleted = false AND q.isActive = true AND " +
           "(q.question LIKE %:keyword% OR q.keywords LIKE %:keyword% OR q.answer LIKE %:keyword%) " +
           "ORDER BY q.priority DESC, q.hitCount DESC")
    List<QABot> findByKeyword(@Param("keyword") String keyword);
    
    @Query("SELECT q FROM QABot q WHERE q.deleted = false AND q.isActive = true AND q.category = :category " +
           "ORDER BY q.priority DESC, q.hitCount DESC")
    List<QABot> findByCategory(@Param("category") QABot.QACategory category);
    
    @Query("SELECT q FROM QABot q WHERE q.deleted = false AND q.isActive = true " +
           "ORDER BY q.hitCount DESC")
    List<QABot> findTopQuestions(@Param("limit") int limit);
    
    @Modifying
    @Transactional
    @Query("UPDATE QABot q SET q.hitCount = q.hitCount + 1 WHERE q.id = :id")
    void incrementHitCount(@Param("id") Long id);
}