package com.leandroProject.apideinvestimentos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     private UUID userId;

    private String userName;

    private String email;

    private String password;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant UpdatedAt;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    public User(UUID userId, String userName, String email, String password, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        UpdatedAt = updatedAt;
    }

}