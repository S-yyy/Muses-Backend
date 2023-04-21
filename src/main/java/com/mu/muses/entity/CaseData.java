package com.mu.muses.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "casedata")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })

public class CaseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    public Integer patientId;
    public String patientName;
    public Integer visits;
    public String treatmentType;
    public String medicalImages;
    public String bodyParts;
    public String illness;
    public String illnessSubtype;
    public String medicalSection;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    public Date visitDate;

}
