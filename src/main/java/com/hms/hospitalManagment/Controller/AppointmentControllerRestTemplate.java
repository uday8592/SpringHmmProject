package com.hms.hospitalManagment.Controller;

import com.hms.hospitalManagment.Entity.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AppointmentControllerRestTemplate {
    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentControllerRestTemplate.class);

    @GetMapping("/getAppointment_one")
    public List<Appointment> getAppointment(){
        String url = "http://localhost:8081/getAppointment";

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        logger.info("Data get successfully : {}",response.getStatusCode());
        return response.getBody();
    }
    @GetMapping("/getAppointmentById_one/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable int appointmentId){
        String url = "http://localhost:8081/getAppointmentById/{appointmentId}";
       Appointment appointment= restTemplate.getForObject(url, Appointment.class,appointmentId);
       // url , class , uri pathvarable
       return appointment;
    }

    @DeleteMapping("/deleteAppointmentById_one/{appointmentId}")
    public void deleteAppointmentById(@PathVariable int appointmentId){
        String url = "http://localhost:8081/deleteAppointment/{appointmentId}";
         restTemplate.delete(url,appointmentId);

    }
    @PutMapping("/updateAppointment_one/{appointmentId}")
    public Appointment updateAppointment(@PathVariable int appointmentId , @RequestBody Appointment appointment){
        appointment.setAppointmentId(appointmentId);
        String url = "http://localhost:8081/updateAppointment";
        HttpEntity<Appointment> request = new HttpEntity<>(appointment);
        ResponseEntity<Appointment> response = restTemplate.exchange(
                        url,
                        HttpMethod.PUT,
                        request,
                        Appointment.class);
        return response.getBody();
    }



}
