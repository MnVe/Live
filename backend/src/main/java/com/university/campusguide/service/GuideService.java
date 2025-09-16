package com.university.campusguide.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.campusguide.dto.GuideDTO;
import com.university.campusguide.entity.Guide;
import com.university.campusguide.entity.User;
import com.university.campusguide.repository.GuideRepository;
import com.university.campusguide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GuideService {
    
    private final GuideRepository guideRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    
    public GuideDTO createGuide(GuideDTO guideDTO, Long authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("作者不存在"));
        
        Guide guide = new Guide();
        BeanUtils.copyProperties(guideDTO, guide, "id", "author", "createdAt", "updatedAt");
        guide.setAuthor(author);
        guide.setViewCount(0);
        guide.setLikeCount(0);
        guide.setStatus(Guide.GuideStatus.DRAFT);
        
        // 处理标签和图片列表
        if (guideDTO.getTags() != null) {
            try {
                guide.setTags(objectMapper.writeValueAsString(guideDTO.getTags()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("标签处理失败", e);
            }
        }
        
        if (guideDTO.getImages() != null) {
            try {
                guide.setImages(objectMapper.writeValueAsString(guideDTO.getImages()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("图片处理失败", e);
            }
        }
        
        guide = guideRepository.save(guide);
        return convertToDTO(guide);
    }
    
    public GuideDTO updateGuide(Long id, GuideDTO guideDTO, Long userId) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("指南不存在"));
        
        // 检查权限
        if (!guide.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("无权限修改此指南");
        }
        
        BeanUtils.copyProperties(guideDTO, guide, "id", "author", "viewCount", "likeCount", "createdAt", "updatedAt");
        
        // 处理标签和图片列表
        if (guideDTO.getTags() != null) {
            try {
                guide.setTags(objectMapper.writeValueAsString(guideDTO.getTags()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("标签处理失败", e);
            }
        }
        
        if (guideDTO.getImages() != null) {
            try {
                guide.setImages(objectMapper.writeValueAsString(guideDTO.getImages()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("图片处理失败", e);
            }
        }
        
        guide = guideRepository.save(guide);
        return convertToDTO(guide);
    }
    
    public GuideDTO getGuideById(Long id) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("指南不存在"));
        
        // 增加浏览量
        guideRepository.incrementViewCount(id);
        guide.setViewCount(guide.getViewCount() + 1);
        
        return convertToDTO(guide);
    }
    
    public Page<GuideDTO> getAllGuides(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        return guideRepository.findAllPublished(pageable)
                .map(this::convertToDTO);
    }
    
    public Page<GuideDTO> getGuidesByCategory(Guide.GuideCategory category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return guideRepository.findByCategory(category, pageable)
                .map(this::convertToDTO);
    }
    
    public Page<GuideDTO> searchGuides(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return guideRepository.findByKeyword(keyword, pageable)
                .map(this::convertToDTO);
    }
    
    public List<GuideDTO> getPopularGuides(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return guideRepository.findTopByViewCount(pageable).stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    public List<GuideDTO> getRecommendedGuides(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return guideRepository.findTopByLikeCount(pageable).stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    public void publishGuide(Long id, Long userId) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("指南不存在"));
        
        if (!guide.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("无权限发布此指南");
        }
        
        guide.setStatus(Guide.GuideStatus.PUBLISHED);
        guideRepository.save(guide);
    }
    
    public void deleteGuide(Long id, Long userId) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("指南不存在"));
        
        if (!guide.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("无权限删除此指南");
        }
        
        guide.setDeleted(true);
        guideRepository.save(guide);
    }
    
    public GuideDTO convertToDTO(Guide guide) {
        GuideDTO dto = new GuideDTO();
        BeanUtils.copyProperties(guide, dto);
        
        // 处理标签和图片
        if (guide.getTags() != null) {
            try {
                dto.setTags(objectMapper.readValue(guide.getTags(), new TypeReference<List<String>>() {}));
            } catch (JsonProcessingException e) {
                dto.setTags(new ArrayList<>());
            }
        }
        
        if (guide.getImages() != null) {
            try {
                dto.setImages(objectMapper.readValue(guide.getImages(), new TypeReference<List<String>>() {}));
            } catch (JsonProcessingException e) {
                dto.setImages(new ArrayList<>());
            }
        }
        
        if (guide.getAuthor() != null) {
            dto.setAuthor(userService.convertToDTO(guide.getAuthor()));
        }
        
        return dto;
    }
}