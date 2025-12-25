package com.hms.hospitalManagment.Controller;

import com.hms.hospitalManagment.Entity.Appointment;
import com.hms.hospitalManagment.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/saveAppointment")
    public Appointment saveAppointment(@RequestBody Appointment appointment){
        return appointmentService.saveAppointment(appointment);
    }
    @GetMapping("/getAppointment")
    public List<Appointment> getAppointments(){
        return appointmentService.getAppointments();
    }
    @PutMapping("/updateAppontment/{AppointmentId}")
    public Appointment updateAppointment(@RequestBody Appointment appointment , @PathVariable int AppointmentId){
        appointment.setAppointmentId(AppointmentId);
        return appointmentService.saveAppointment(appointment);
    }
   @DeleteMapping("/deleteAppointment/{AppointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable int AppointmentId){
      return   appointmentService.deleteAppointment(AppointmentId);

   }

}
