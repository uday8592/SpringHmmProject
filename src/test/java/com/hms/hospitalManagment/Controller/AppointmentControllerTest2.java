package com.hms.hospitalManagment.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.hospitalManagment.Entity.Appointment;
import com.hms.hospitalManagment.Service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest2 {

    @Autowired
  private MockMvc mockMvc;

  @Mock
  private AppointmentService appointmentService;

  @InjectMocks
  private AppointmentController appointmentController;

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private Appointment appointment;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    objectMapper = new ObjectMapper();

    appointment = new Appointment();
    appointment.setAppointmentId(1);
    appointment.setPatientId(23);
    appointment.setPatientFName("uday");
    appointment.setEmail("uday@gmail.com");
    appointment.setStatus("Active");
  }

  @Test
  void testSaveAppointment() throws Exception {
    when(appointmentService.saveAppointment(any(Appointment.class))).thenReturn(appointment);

    mockMvc.perform(post("/saveAppointment")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(appointment)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.appointmentId").value(1))
        .andExpect(jsonPath("$.patientFName").value("uday"))
        .andExpect(jsonPath("$.email").value("uday@gmail.com"));
  }

  @Test
  void testGetAppointments() throws Exception {
    List<Appointment> appointmentList = new ArrayList<>();
    appointmentList.add(appointment);

    Appointment appointment2 = new Appointment();
    appointment2.setAppointmentId(2);
    appointment2.setPatientId(24);
    appointment2.setPatientFName("rajesh");
    appointment2.setEmail("rajesh@gmail.com");
    appointment2.setStatus("Active");
    appointmentList.add(appointment2);

    when(appointmentService.getAppointments()).thenReturn(appointmentList);

    mockMvc.perform(get("/getAppointment"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].appointmentId").value(1))
        .andExpect(jsonPath("$[1].appointmentId").value(2));
  }

  @Test
  void testUpdateAppointment() throws Exception {
    appointment.setPatientFName("rajesh");
    when(appointmentService.saveAppointment(any(Appointment.class))).thenReturn(appointment);

    mockMvc.perform(put("/updateAppontment/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(appointment)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.appointmentId").value(1))
        .andExpect(jsonPath("$.patientFName").value("rajesh"));
  }

  @Test
  void testDeleteAppointment() throws Exception {
    ResponseEntity<String> mockResponse = ResponseEntity.ok("Appointment deleted successfully");
    Mockito.doReturn(mockResponse).when(appointmentService).deleteAppointment(anyInt());

    mockMvc.perform(delete("/deleteAppointment/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("Appointment deleted successfully"));
  }
}
