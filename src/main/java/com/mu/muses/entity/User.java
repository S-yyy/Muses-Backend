package com.mu.muses.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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
    public String password;

    @Column(name = "role")
    public String role;

}
