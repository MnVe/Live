package com.university.campusguide.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.campusguide.dto.CampusServiceDTO;
import com.university.campusguide.entity.CampusService;
import com.university.campusguide.repository.CampusServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CampusServiceService {
    
    private final CampusServiceRepository campusServiceRepository;
    private final ObjectMapper objectMapper;
    
    public CampusServiceDTO createService(CampusServiceDTO serviceDTO) {
        CampusService service = new CampusService();
        BeanUtils.copyProperties(serviceDTO, service, "id", "createdAt", "updatedAt");
        
        // 处理图片列表
        if (serviceDTO.getImages() != null) {
            try {
                service.setImages(objectMapper.writeValueAsString(serviceDTO.getImages()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("图片处理失败", e);
            }
        }
        
        service = campusServiceRepository.save(service);
        return convertToDTO(service);
    }
    
    public CampusServiceDTO updateService(Long id, CampusServiceDTO serviceDTO) {
        CampusService service = campusServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("服务不存在"));
        
        BeanUtils.copyProperties(serviceDTO, service, "id", "createdAt", "updatedAt");
        
        // 处理图片列表
        if (serviceDTO.getImages() != null) {
            try {
                service.setImages(objectMapper.writeValueAsString(serviceDTO.getImages()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("图片处理失败", e);
            }
        }
        
        service = campusServiceRepository.save(service);
        return convertToDTO(service);
    }
    
    public CampusServiceDTO getServiceById(Long id) {
        CampusService service = campusServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("服务不存在"));
        return convertToDTO(service);
    }
    
    public Page<CampusServiceDTO> getAllServices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return campusServiceRepository.findAllActive(pageable)
                .map(this::convertToDTO);
    }
    
    public Page<CampusServiceDTO> getServicesByCategory(CampusService.ServiceCategory category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return campusServiceRepository.findByCategory(category, pageable)
                .map(this::convertToDTO);
    }
    
    public Page<CampusServiceDTO> searchServices(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return campusServiceRepository.findByKeyword(keyword, pageable)
                .map(this::convertToDTO);
    }
    
    public List<CampusServiceDTO> getOnlineServices() {
        return campusServiceRepository.findOnlineServices().stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    public List<CampusServiceDTO> getTopRatedServices(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return campusServiceRepository.findTopRatedServices(pageable).stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    public List<CampusService.ServiceCategory> getAllCategories() {
        return campusServiceRepository.findAllActiveCategories();
    }
    
    public void deleteService(Long id) {
        CampusService service = campusServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("服务不存在"));
        service.setDeleted(true);
        campusServiceRepository.save(service);
    }
    
    public CampusServiceDTO convertToDTO(CampusService service) {
        CampusServiceDTO dto = new CampusServiceDTO();
        BeanUtils.copyProperties(service, dto);
        
        // 处理图片
        if (service.getImages() != null) {
            try {
                dto.setImages(objectMapper.readValue(service.getImages(), new TypeReference<List<String>>() {}));
            } catch (JsonProcessingException e) {
                dto.setImages(new ArrayList<>());
            }
        }
        
        return dto;
    }
}