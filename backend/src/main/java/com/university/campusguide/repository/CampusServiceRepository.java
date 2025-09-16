package com.university.campusguide.repository;

import com.university.campusguide.entity.CampusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampusServiceRepository extends JpaRepository<CampusService, Long> {
    
    @Query("SELECT s FROM CampusService s WHERE s.deleted = false AND s.status = 'ACTIVE' ORDER BY s.priority DESC, s.rating DESC")
    Page<CampusService> findAllActive(Pageable pageable);
    
    @Query("SELECT s FROM CampusService s WHERE s.deleted = false AND s.status = 'ACTIVE' AND s.category = :category ORDER BY s.priority DESC, s.rating DESC")
    Page<CampusService> findByCategory(@Param("category") CampusService.ServiceCategory category, Pageable pageable);
    
    @Query("SELECT s FROM CampusService s WHERE s.deleted = false AND s.status = 'ACTIVE' AND " +
           "(s.name LIKE %:keyword% OR s.description LIKE %:keyword% OR s.location LIKE %:keyword%)")
    Page<CampusService> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT s FROM CampusService s WHERE s.deleted = false AND s.status = 'ACTIVE' AND s.isOnline = true ORDER BY s.priority DESC")
    List<CampusService> findOnlineServices();
    
    @Query("SELECT s FROM CampusService s WHERE s.deleted = false AND s.status = 'ACTIVE' ORDER BY s.rating DESC")
    List<CampusService> findTopRatedServices(Pageable pageable);
    
    @Query("SELECT DISTINCT s.category FROM CampusService s WHERE s.deleted = false AND s.status = 'ACTIVE'")
    List<CampusService.ServiceCategory> findAllActiveCategories();
}