package com.gitangular.graph.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Table(name = "users")
@Entity
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Email cannot be left empty")
    @NotNull(message = "Email cannot be set to null")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Username cannot be left empty")
    @NotNull(message = "Username cannot be set to null")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty(message = "Github username is required")
    @NotNull(message = "Github username cannot be set to null")
    @Column(name = "github", nullable = false, unique = true)
    private String github;

    @Column(name = "token")
    private String token;

    @JoinColumn(name = "location_id")
    @OneToOne
    private Location location;

    @JoinColumn(name = "picture_id", unique = true)
    @OneToOne
    private Picture picture;

    public User(String email, String username, String password, String github, String token) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.github = github;
        this.token = token;
    }
}