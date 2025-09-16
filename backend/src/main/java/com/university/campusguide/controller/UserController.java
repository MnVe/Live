package com.university.campusguide.controller;

import com.university.campusguide.dto.ApiResponse;
import com.university.campusguide.dto.UserDTO;
import com.university.campusguide.security.UserPrincipal;
import com.university.campusguide.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDTO>> getCurrentUser(
            @AuthenticationPrincipal UserPrincipal currentUser) {
        UserDTO user = userService.getUserById(currentUser.getId());
        return ResponseEntity.ok(ApiResponse.success(user));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserDTO>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        Page<UserDTO> users = userService.getAllUsers(page, size, sortBy, sortDir);
        return ResponseEntity.ok(ApiResponse.success(users));
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<UserDTO>>> searchUsers(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<UserDTO> users = userService.searchUsers(keyword, page, size);
        return ResponseEntity.ok(ApiResponse.success(users));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        
        // 只能更新自己的信息或管理员可以更新任何用户
        if (!currentUser.getId().equals(id) && 
            !currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403)
                .body(ApiResponse.error(403, "无权限修改此用户信息"));
        }
        
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(ApiResponse.success("用户信息更新成功", updatedUser));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        
        // 只有管理员可以删除用户
        if (!currentUser.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403)
                .body(ApiResponse.error(403, "无权限删除用户"));
        }
        
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("用户删除成功", null));
    }
}