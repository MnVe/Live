package com.university.campusguide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    
    @NotBlank
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;
    
    @Email
    @NotBlank
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "nickname")
    private String nickname;
    
    @Column(name = "avatar_url")
    private String avatarUrl;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @Column(name = "student_id")
    private String studentId;
    
    @Column(name = "major")
    private String major;
    
    @Column(name = "grade")
    private String grade;
    
    @Column(name = "college")
    private String college;
    
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;
    
    @Column(name = "bio", length = 500)
    private String bio;
    
    @Column(name = "points")
    private Integer points = 0;
    
    public enum Gender {
        MALE, FEMALE, OTHER
    }
    
    public enum Role {
        ADMIN, TEACHER, STUDENT
    }
    
    public enum UserStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
}