package com.hms.hospitalManagment.Controller;
import com.hms.hospitalManagment.Entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
@RestController
public class AppointmentControllerWebClient {

    @Autowired
    private WebClient webClient;
    @PostMapping("/saveAppointment_one")
    public Appointment saveAppointment(@RequestBody Appointment appointment){
        return
                webClient
                        .post()
                        .uri("http://localhost:8081/saveAppointment")
                        .bodyValue(appointment)
                        .retrieve()
                        .bodyToMono(Appointment.class)
                        .block();

    }
    @GetMapping("/getAppointments")
    public List<Appointment> getAppointments(){
        return webClient.get()
                .uri("http://localhost:8081/getAppointment")
                .retrieve()
                .bodyToFlux(Appointment.class)
                .collectList()
                .block();
    }

    @GetMapping("/getAppointmentById/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable int appointmentId ){
        return webClient.get()
                .uri("http://localhost:8081/getAppointmentById/{appointmentId}",appointmentId)
                .retrieve()
                .bodyToMono(Appointment.class)
                .block();
    }
    @PutMapping("/updateAppointmentById/{appointmentId}")
    public Appointment updateAppointment(@PathVariable int appointmentId,@RequestBody Appointment appointment){
        appointment.setAppointmentId(appointmentId);
        return webClient
                .put()
                .uri("http://localhost:8081/updateAppointment")
                .bodyValue(appointment)
                .retrieve()
                .bodyToMono(Appointment.class)
                .block();
    }
    @DeleteMapping("/deleteAppointmentById/{appointmentId}")
    public void deleteAppointmentById(@PathVariable int appointmentId){
      webClient.delete()
             .uri("http://localhost:8081/deleteAppointment/{appointmentId}",appointmentId)
             .retrieve()
              .bodyToMono(void.class)
             .block();
    }
}
