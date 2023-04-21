package com.mu.muses.entity;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    @CreationTimestamp
    public Date createDate;
    String cases;

    public List<String> getTopicList() {
        if(topicList==null){
            return new ArrayList<>();
        }
        return JSONArray.parseArray(this.topicList,String.class);
    }

    public void setTopicList(List<String> role) {
        this.topicList = role.toString();
    }

    public List<Integer> getCases() {
        if (cases==null){
            return new ArrayList<>();
        }
            return JSONArray.parseArray(this.cases,Integer.class);
    }

    public void setCases(List<Integer> cases) {
        this.cases = cases.toString();
    }
}
