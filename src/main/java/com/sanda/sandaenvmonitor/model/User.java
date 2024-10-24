// src/main/java/com/sanda/sandaenvmonitor/model/User.java

package com.sanda.sandaenvmonitor.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "default_city")
    private Long defaultCity;

    // Getter 和 Setter
    public Long getDefaultCity() {
        return defaultCity;
    }

    public void setDefaultCity(Long defaultCity) {
        this.defaultCity = defaultCity;
    }

    // Constructors
    public User() {}

    public User(String username, String password, String email, Boolean enabled, String phoneNumber, String avatar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
    }
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}