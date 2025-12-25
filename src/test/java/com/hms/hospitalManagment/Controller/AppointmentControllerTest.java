package com.hms.hospitalManagment.Controller;


import com.hms.hospitalManagment.Entity.Appointment;
import com.hms.hospitalManagment.Service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {
    @InjectMocks
    private AppointmentController appointmentController;
    @Mock
    private AppointmentService appointmentService;


    @Test
    public void myFirstTest(){
        System.out.println("This is my first test ");
    }

    private Appointment appointment;

    @BeforeEach
    void setUp(){
        appointment=new Appointment();
        appointment.setAppointmentId(1);
        appointment.setPatientId(23);
        appointment.setPatientFName("uday");
        appointment.setEmail("uday@gmail.com");
        appointment.setStatus("Active");

    }


    @Test
    void saveAppointmentIsSavedSuccessfully(){
        Mockito.when(appointmentController.saveAppointment(any(Appointment.class))).thenReturn(appointment);
        Appointment returnResult = appointmentController.saveAppointment(appointment);
        assertNotNull(returnResult);
        assertEquals(appointment.getAppointmentId() ,returnResult.getAppointmentId());

    }

    @Test
    void getAppointmentListIsSuccessFully(){
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        Appointment appointment2 = new Appointment();
        appointment.setAppointmentId(2);
        appointment.setPatientId(24);
        appointment.setPatientFName("rajesh");
        appointment.setEmail("rajesh@gmail.com");
        appointment.setStatus("Active");
        appointmentList.add(appointment2);
        Mockito.when(appointmentController.getAppointments()).thenReturn(appointmentList);
        List<Appointment>resultList = appointmentController.getAppointments();
        assertNotNull(resultList);
        assertEquals(appointmentList.size(),2);
    }

    @Test
    void updateAppointmentIsSuccessfull(){
        int appointmentId =1;
        appointment.setPatientFName("rajesh");
        Mockito.when(appointmentService.saveAppointment(any(Appointment.class))).thenReturn(appointment);
        Appointment resultAppointment = appointmentController.updateAppointment(appointment,appointmentId);
        assertNotNull(resultAppointment);
        assertEquals(resultAppointment.getAppointmentId(),1);
        assertEquals(resultAppointment.getPatientFName(),"rajesh");
    }


//    @Test
//    void testDeleteAppointment() {
//        int appointmentId = 1;
//        ResponseEntity<?> mockResponse =
//                ResponseEntity.ok("Appointment deleted successfully");
//        Mockito.when(appointmentService.deleteAppointment(appointmentId))
//                .thenReturn(mockResponse);
//
//        ResponseEntity<?> result = appointmentController.deleteAppointment(appointmentId);
//        assertNull(result);
//        assertEquals(200, result.getStatusCode().value());
//        assertEquals("Appointment deleted successfully", result.getBody());
//
//    }



}
