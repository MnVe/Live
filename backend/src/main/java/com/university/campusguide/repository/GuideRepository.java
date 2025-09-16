package com.university.campusguide.repository;

import com.university.campusguide.entity.Guide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {
    
    @Query("SELECT g FROM Guide g WHERE g.deleted = false AND g.status = 'PUBLISHED' ORDER BY g.priority DESC, g.createdAt DESC")
    Page<Guide> findAllPublished(Pageable pageable);
    
    @Query("SELECT g FROM Guide g WHERE g.deleted = false AND g.status = 'PUBLISHED' AND g.category = :category ORDER BY g.priority DESC, g.createdAt DESC")
    Page<Guide> findByCategory(@Param("category") Guide.GuideCategory category, Pageable pageable);
    
    @Query("SELECT g FROM Guide g WHERE g.deleted = false AND g.status = 'PUBLISHED' AND " +
           "(g.title LIKE %:keyword% OR g.summary LIKE %:keyword% OR g.content LIKE %:keyword% OR g.tags LIKE %:keyword%)")
    Page<Guide> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT g FROM Guide g WHERE g.deleted = false AND g.author.id = :authorId ORDER BY g.createdAt DESC")
    Page<Guide> findByAuthorId(@Param("authorId") Long authorId, Pageable pageable);
    
    @Query("SELECT g FROM Guide g WHERE g.deleted = false AND g.status = 'PUBLISHED' ORDER BY g.viewCount DESC")
    List<Guide> findTopByViewCount(Pageable pageable);
    
    @Query("SELECT g FROM Guide g WHERE g.deleted = false AND g.status = 'PUBLISHED' ORDER BY g.likeCount DESC")
    List<Guide> findTopByLikeCount(Pageable pageable);
    
    @Modifying
    @Transactional
    @Query("UPDATE Guide g SET g.viewCount = g.viewCount + 1 WHERE g.id = :id")
    void incrementViewCount(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Guide g SET g.likeCount = g.likeCount + 1 WHERE g.id = :id")
    void incrementLikeCount(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Guide g SET g.likeCount = g.likeCount - 1 WHERE g.id = :id AND g.likeCount > 0")
    void decrementLikeCount(@Param("id") Long id);
}