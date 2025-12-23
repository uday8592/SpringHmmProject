package com.hms.hospitalManagment.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    private String empFname;
    private String empLname;
    private String specialization;
    private String qualification;
    private String hospitalName;
    private String mobileNumber;
    private String status;
    private Integer consultingFee = 0;
    private String gender;
    private String address;
    private String email;
    private String roleType;
    private String issueDesc;
    private String comments;
    private String password;
}
