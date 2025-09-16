package com.university.campusguide.repository;

import com.university.campusguide.entity.Post;
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
public interface PostRepository extends JpaRepository<Post, Long> {
    
    @Query("SELECT p FROM Post p WHERE p.deleted = false AND p.status = 'PUBLISHED' ORDER BY p.isPinned DESC, p.createdAt DESC")
    Page<Post> findAllPublished(Pageable pageable);
    
    @Query("SELECT p FROM Post p WHERE p.deleted = false AND p.status = 'PUBLISHED' AND p.category = :category ORDER BY p.isPinned DESC, p.createdAt DESC")
    Page<Post> findByCategory(@Param("category") Post.PostCategory category, Pageable pageable);
    
    @Query("SELECT p FROM Post p WHERE p.deleted = false AND p.status = 'PUBLISHED' AND " +
           "(p.title LIKE %:keyword% OR p.content LIKE %:keyword% OR p.tags LIKE %:keyword%)")
    Page<Post> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT p FROM Post p WHERE p.deleted = false AND p.author.id = :authorId ORDER BY p.createdAt DESC")
    Page<Post> findByAuthorId(@Param("authorId") Long authorId, Pageable pageable);
    
    @Query("SELECT p FROM Post p WHERE p.deleted = false AND p.status = 'PUBLISHED' ORDER BY p.viewCount DESC")
    List<Post> findTopByViewCount(Pageable pageable);
    
    @Query("SELECT p FROM Post p WHERE p.deleted = false AND p.status = 'PUBLISHED' ORDER BY p.likeCount DESC")
    List<Post> findTopByLikeCount(Pageable pageable);
    
    @Query("SELECT p FROM Post p WHERE p.deleted = false AND p.status = 'PUBLISHED' ORDER BY p.commentCount DESC")
    List<Post> findTopByCommentCount(Pageable pageable);
    
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.viewCount = p.viewCount + 1 WHERE p.id = :id")
    void incrementViewCount(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.likeCount = p.likeCount + 1 WHERE p.id = :id")
    void incrementLikeCount(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.likeCount = p.likeCount - 1 WHERE p.id = :id AND p.likeCount > 0")
    void decrementLikeCount(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.commentCount = p.commentCount + 1 WHERE p.id = :id")
    void incrementCommentCount(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.commentCount = p.commentCount - 1 WHERE p.id = :id AND p.commentCount > 0")
    void decrementCommentCount(@Param("id") Long id);
}