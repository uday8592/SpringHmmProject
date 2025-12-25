package com.hms.hospitalManagment.Service;

import com.hms.hospitalManagment.Entity.Appointment;
import com.hms.hospitalManagment.Repo.AppointmentRepo;
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
public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepo appointmentRepo;

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment();
        appointment.setAppointmentId(1);
        appointment.setPatientId(23);
        appointment.setPatientFName("uday");
        appointment.setEmail("uday@gmail.com");
        appointment.setStatus("Active");
    }

    @Test
    void saveAppointmentIsSavedSuccessfully() {
        Mockito.when(appointmentRepo.save(any(Appointment.class))).thenReturn(appointment);
        Appointment result = appointmentService.saveAppointment(appointment);

        assertNotNull(result);
        assertEquals(1, result.getAppointmentId());
        assertEquals("uday", result.getPatientFName());
    }

    @Test
    void getAppointmentListIsSuccessFully() {
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentId(2);
        appointment2.setPatientId(24);
        appointment2.setPatientFName("rajesh");
        appointment2.setEmail("rajesh@gmail.com");
        appointment2.setStatus("Active");

        appointmentList.add(appointment2);

        Mockito.when(appointmentRepo.findAll()).thenReturn(appointmentList);
        List<Appointment> resultList = appointmentService.getAppointments();

        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    void deleteAppointmentIsSuccessFull() {
        int appointmentId = 1;

        Mockito.doNothing().when(appointmentRepo).deleteById(anyInt());
        ResponseEntity<?> response = appointmentService.deleteAppointment(appointmentId);
        Mockito.verify(appointmentRepo, Mockito.times(1)).deleteById(appointmentId);
        assertNull(response);
    }
}
