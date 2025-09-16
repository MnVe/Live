package com.university.campusguide.repository;

import com.university.campusguide.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByStudentId(String studentId);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    boolean existsByStudentId(String studentId);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false AND u.status = 'ACTIVE'")
    Page<User> findAllActiveUsers(Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false AND " +
           "(u.username LIKE %:keyword% OR u.nickname LIKE %:keyword% OR u.email LIKE %:keyword%)")
    Page<User> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false AND u.role = :role")
    Page<User> findByRole(@Param("role") User.Role role, Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.deleted = false AND u.college = :college")
    Page<User> findByCollege(@Param("college") String college, Pageable pageable);
}