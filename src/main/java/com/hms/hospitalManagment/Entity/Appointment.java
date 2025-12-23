package com.hms.hospitalManagment.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Appointment")
public class Appointment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int appointmentId;
    private Integer patientId;
    private String patientFName;
    private String patientLName;
    private String mobileNumber;
    private String specialization;
    private String status;
    private String email;
    private String issueDesc;
    private String comments;
}
