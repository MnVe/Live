package com.university.campusguide.controller;

import com.university.campusguide.dto.ApiResponse;
import com.university.campusguide.dto.CampusServiceDTO;
import com.university.campusguide.entity.CampusService;
import com.university.campusguide.service.CampusServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CampusServiceController {
    
    private final CampusServiceService campusServiceService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CampusServiceDTO>>> getAllServices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CampusServiceDTO> services = campusServiceService.getAllServices(page, size);
        return ResponseEntity.ok(ApiResponse.success(services));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CampusServiceDTO>> getServiceById(@PathVariable Long id) {
        CampusServiceDTO service = campusServiceService.getServiceById(id);
        return ResponseEntity.ok(ApiResponse.success(service));
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<Page<CampusServiceDTO>>> getServicesByCategory(
            @PathVariable CampusService.ServiceCategory category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CampusServiceDTO> services = campusServiceService.getServicesByCategory(category, page, size);
        return ResponseEntity.ok(ApiResponse.success(services));
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<CampusServiceDTO>>> searchServices(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CampusServiceDTO> services = campusServiceService.searchServices(keyword, page, size);
        return ResponseEntity.ok(ApiResponse.success(services));
    }
    
    @GetMapping("/online")
    public ResponseEntity<ApiResponse<List<CampusServiceDTO>>> getOnlineServices() {
        List<CampusServiceDTO> services = campusServiceService.getOnlineServices();
        return ResponseEntity.ok(ApiResponse.success(services));
    }
    
    @GetMapping("/top-rated")
    public ResponseEntity<ApiResponse<List<CampusServiceDTO>>> getTopRatedServices(
            @RequestParam(defaultValue = "10") int limit) {
        List<CampusServiceDTO> services = campusServiceService.getTopRatedServices(limit);
        return ResponseEntity.ok(ApiResponse.success(services));
    }
    
    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CampusService.ServiceCategory>>> getAllCategories() {
        List<CampusService.ServiceCategory> categories = campusServiceService.getAllCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<CampusServiceDTO>> createService(@RequestBody CampusServiceDTO serviceDTO) {
        CampusServiceDTO createdService = campusServiceService.createService(serviceDTO);
        return ResponseEntity.ok(ApiResponse.success("服务创建成功", createdService));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CampusServiceDTO>> updateService(
            @PathVariable Long id,
            @RequestBody CampusServiceDTO serviceDTO) {
        CampusServiceDTO updatedService = campusServiceService.updateService(id, serviceDTO);
        return ResponseEntity.ok(ApiResponse.success("服务更新成功", updatedService));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteService(@PathVariable Long id) {
        campusServiceService.deleteService(id);
        return ResponseEntity.ok(ApiResponse.success("服务删除成功", null));
    }
}