package com.mu.muses.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "dataset")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })

public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    public String name;
    public String description;
    String topicList;
    public int post;
    @Column(name = "create_member")
    public String owner;
    public String createDate;

    public List<String> getTopicList() {
        return Arrays.asList(this.topicList.split(","));
    }

    public void setTopicList(List<String> role) {
        this.topicList = role.toString();
    }
}
