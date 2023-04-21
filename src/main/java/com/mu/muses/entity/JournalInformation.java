package com.mu.muses.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "researchsubject")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })


public class JournalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    public String journalName;
    public String issn;
    public String eissn;
    public String category;
    public Boolean if_2022;
    public String jci;
    public int percentage;
    public Date createDate;

}
