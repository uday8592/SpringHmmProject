package com.hms.hospitalManagment.Controller;

import com.hms.hospitalManagment.Entity.Employee;
import com.hms.hospitalManagment.Service.EmployeeService;
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
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setEmpId(1);
        employee.setEmpFname("Uday");
        employee.setEmail("uday@gmail.com");
        employee.setRoleType("ADMIN");
        employee.setStatus("ACTIVE");
    }

    @Test
    void saveEmployeeIsSavedSuccessfully() {

        Mockito.when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);
        Employee result = employeeController.saveEmployee(employee);
        assertNotNull(result);
        assertEquals(1, result.getEmpId());
        assertEquals("Uday", result.getEmpFname());
    }

    @Test
    void getEmployeeListIsSuccessfully() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        Employee employee2 = new Employee();
        employee2.setEmpId(2);
        employee2.setEmpFname("Rajesh");
        employee2.setEmail("rajesh@gmail.com");
        employee2.setRoleType("USER");
        employee2.setStatus("ACTIVE");

        employeeList.add(employee2);

        Mockito.when(employeeService.getAllEmployes()).thenReturn(employeeList);
        List<Employee> resultList = employeeController.getEmployees();

        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    void updateEmployeeIsSuccessful() {

        int empId = 1;
        employee.setEmpFname("Rajesh");

        Mockito.when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);
        Employee result = employeeController.updateEmployee(empId, employee);

        assertNotNull(result);
        assertEquals(1, result.getEmpId());
        assertEquals("Rajesh", result.getEmpFname());
    }

    @Test
    void deleteEmployeeIsSuccessful() {
        int empId = 1;
        ResponseEntity<String> mockResponse = ResponseEntity.ok("Employee deleted successfully");

        Mockito.doReturn(mockResponse).when(employeeService).deleteEmploye(empId);

        ResponseEntity<?> response = employeeController.deleteEmployee(empId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Employee deleted successfully", response.getBody());
    }
}
