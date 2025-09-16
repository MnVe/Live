package com.university.campusguide.dto;

import com.university.campusguide.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String nickname;
    private String avatarUrl;
    private String phone;
    private User.Gender gender;
    private LocalDate birthDate;
    private String studentId;
    private String major;
    private String grade;
    private String college;
    private User.Role role;
    private User.UserStatus status;
    private String bio;
    private Integer points;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}