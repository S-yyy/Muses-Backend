package com.mu.muses.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mu.muses.enums.Status;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "research")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })

public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    public String name;
    public String description;
    public String tags;
    public Status status;
    @Column(name = "create_member")
    public String owner;
    public String members;
    public String createTime;
    public String openingTime;
    public String endingTime;

}
