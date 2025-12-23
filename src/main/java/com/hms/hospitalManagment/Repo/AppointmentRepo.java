package com.hms.hospitalManagment.Repo;

import com.hms.hospitalManagment.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
}

