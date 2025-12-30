package com.hms.hospitalManagment.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.hospitalManagment.Entity.Employee;
import com.hms.hospitalManagment.Service.EmployeeService;
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
public class EmployeeControllerTest2{
    @Autowired
  private MockMvc mockMvc;

  @Mock
  private EmployeeService employeeService;

  @InjectMocks
  private EmployeeController employeeController;

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private Employee employee;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    objectMapper = new ObjectMapper();

    employee = new Employee();
    employee.setEmpId(1);
    employee.setEmpFname("Uday");
    employee.setEmpLname("Kiran");
    employee.setEmail("uday@gmail.com");
    employee.setRoleType("ADMIN");
    employee.setStatus("ACTIVE");
  }

  @Test
  void testSaveEmployee() throws Exception {
    when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

    mockMvc.perform(post("/saveEmployee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employee)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.empId").value(1))
        .andExpect(jsonPath("$.empFname").value("Uday"))
        .andExpect(jsonPath("$.email").value("uday@gmail.com"))
        .andExpect(jsonPath("$.roleType").value("ADMIN"));
  }

  @Test
  void testGetEmployees() throws Exception {
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(employee);

    Employee employee2 = new Employee();
    employee2.setEmpId(2);
    employee2.setEmpFname("Rajesh");
    employee2.setEmpLname("Kumar");
    employee2.setEmail("rajesh@gmail.com");
    employee2.setRoleType("USER");
    employee2.setStatus("ACTIVE");
    employeeList.add(employee2);

    when(employeeService.getAllEmployes()).thenReturn(employeeList);

    mockMvc.perform(get("/getEmployee"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].empId").value(1))
        .andExpect(jsonPath("$[1].empId").value(2))
        .andExpect(jsonPath("$[0].empFname").value("Uday"))
        .andExpect(jsonPath("$[1].empFname").value("Rajesh"));
  }

  @Test
  void testUpdateEmployee() throws Exception {
    employee.setEmpFname("Rajesh");
    when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

    mockMvc.perform(put("/updateEmployee/1")
        .param("EmpId", "1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employee)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.empId").value(1))
        .andExpect(jsonPath("$.empFname").value("Rajesh"));
  }

  @Test
  void testDeleteEmployee() throws Exception {
    ResponseEntity<String> mockResponse = ResponseEntity.ok("Employee deleted successfully");
    Mockito.doReturn(mockResponse).when(employeeService).deleteEmploye(anyInt());

    mockMvc.perform(delete("/deleteEmployee/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("Employee deleted successfully"));
  }
}
