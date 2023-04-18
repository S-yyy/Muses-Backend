package com.mu.muses.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "password")
    public String password;

    @Column(name = "role")
    String role;

}
