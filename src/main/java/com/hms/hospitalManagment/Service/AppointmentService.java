package com.hms.hospitalManagment.Service;

import com.hms.hospitalManagment.Entity.Appointment;
import com.hms.hospitalManagment.Repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;


    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointmentRepo.findAll();
    }


    public ResponseEntity<?> deleteAppointment(int AppointmentId) {
        appointmentRepo.deleteById(AppointmentId);
        return null;
    }
}
