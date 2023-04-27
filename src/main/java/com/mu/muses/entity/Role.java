package com.mu.muses.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mu.muses.enums.Roles;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "role")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    public String name;
    public String description;
    String roleList;

    public List<String> getRole() {
        if(roleList==null){
            return new ArrayList<>();
        }
        return Arrays.asList(this.roleList.split(","));
    }

    public void setRole(List<String> role) {
        this.roleList = role.toString();
    }
}
