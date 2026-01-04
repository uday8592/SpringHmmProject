package com.hms.hospitalManagment.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
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


