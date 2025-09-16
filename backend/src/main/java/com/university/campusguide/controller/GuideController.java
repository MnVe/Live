package com.university.campusguide.controller;

import com.university.campusguide.dto.ApiResponse;
import com.university.campusguide.dto.GuideDTO;
import com.university.campusguide.entity.Guide;
import com.university.campusguide.security.UserPrincipal;
import com.university.campusguide.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guides")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GuideController {
    
    private final GuideService guideService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<GuideDTO>>> getAllGuides(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        Page<GuideDTO> guides = guideService.getAllGuides(page, size, sortBy, sortDir);
        return ResponseEntity.ok(ApiResponse.success(guides));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GuideDTO>> getGuideById(@PathVariable Long id) {
        GuideDTO guide = guideService.getGuideById(id);
        return ResponseEntity.ok(ApiResponse.success(guide));
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<Page<GuideDTO>>> getGuidesByCategory(
            @PathVariable Guide.GuideCategory category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<GuideDTO> guides = guideService.getGuidesByCategory(category, page, size);
        return ResponseEntity.ok(ApiResponse.success(guides));
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<GuideDTO>>> searchGuides(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<GuideDTO> guides = guideService.searchGuides(keyword, page, size);
        return ResponseEntity.ok(ApiResponse.success(guides));
    }
    
    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<List<GuideDTO>>> getPopularGuides(
            @RequestParam(defaultValue = "10") int limit) {
        List<GuideDTO> guides = guideService.getPopularGuides(limit);
        return ResponseEntity.ok(ApiResponse.success(guides));
    }
    
    @GetMapping("/recommended")
    public ResponseEntity<ApiResponse<List<GuideDTO>>> getRecommendedGuides(
            @RequestParam(defaultValue = "10") int limit) {
        List<GuideDTO> guides = guideService.getRecommendedGuides(limit);
        return ResponseEntity.ok(ApiResponse.success(guides));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<GuideDTO>> createGuide(
            @RequestBody GuideDTO guideDTO,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        GuideDTO createdGuide = guideService.createGuide(guideDTO, currentUser.getId());
        return ResponseEntity.ok(ApiResponse.success("指南创建成功", createdGuide));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GuideDTO>> updateGuide(
            @PathVariable Long id,
            @RequestBody GuideDTO guideDTO,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        GuideDTO updatedGuide = guideService.updateGuide(id, guideDTO, currentUser.getId());
        return ResponseEntity.ok(ApiResponse.success("指南更新成功", updatedGuide));
    }
    
    @PutMapping("/{id}/publish")
    public ResponseEntity<ApiResponse<String>> publishGuide(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        guideService.publishGuide(id, currentUser.getId());
        return ResponseEntity.ok(ApiResponse.success("指南发布成功", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteGuide(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        guideService.deleteGuide(id, currentUser.getId());
        return ResponseEntity.ok(ApiResponse.success("指南删除成功", null));
    }
}