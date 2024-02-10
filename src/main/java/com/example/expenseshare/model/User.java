package com.example.expenseshare.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserGroup> userGroups = new HashSet<>();

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return userId;
    }
}


