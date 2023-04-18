package com.mu.muses.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Repository
public class Enums {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    public String value;
    public String label;
}
