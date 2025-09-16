package com.university.campusguide.controller;

import com.university.campusguide.dto.*;
import com.university.campusguide.security.JwtTokenProvider;
import com.university.campusguide.security.UserPrincipal;
import com.university.campusguide.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserDTO user = userService.getUserById(userPrincipal.getId());
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("type", "Bearer");
        response.put("user", user);
        
        return ResponseEntity.ok(ApiResponse.success("登录成功", response));
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserDTO user = userService.register(registerRequest);
        return ResponseEntity.ok(ApiResponse.success("注册成功", user));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(ApiResponse.success("退出成功", null));
    }
}