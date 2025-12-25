package com.hms.hospitalManagment.Service;

import com.hms.hospitalManagment.Entity.Employee;
import com.hms.hospitalManagment.Repo.EmployeeRepo;
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
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepo employeeRepo;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setEmpId(1);
        employee.setEmpFname("uday");
        employee.setEmpLname("kiran");
        employee.setEmail("uday@gmail.com");
        employee.setMobileNumber("9999999999");
        employee.setRoleType("Doctor");
        employee.setStatus("Active");
    }

    @Test
    void saveEmployeeIsSavedSuccessfully() {
        Mockito.when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
        Employee result = employeeService.saveEmployee(employee);

        assertNotNull(result);
        assertEquals(1, result.getEmpId());
        assertEquals("uday", result.getEmpFname());
    }

    @Test
    void getEmployeeListIsSuccessfullyReturned() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        Employee employee2 = new Employee();
        employee2.setEmpId(2);
        employee2.setEmpFname("rajesh");
        employee2.setEmpLname("kumar");
        employee2.setEmail("rajesh@gmail.com");
        employee2.setMobileNumber("8888888888");
        employee2.setRoleType("Nurse");
        employee2.setStatus("Active");
        employeeList.add(employee2);

        Mockito.when(employeeRepo.findAll()).thenReturn(employeeList);
        List<Employee> resultList = employeeService.getAllEmployes();

        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    void deleteEmployeeIsSuccessful() {
        int empId = 1;

        Mockito.doNothing().when(employeeRepo).deleteById(anyInt());
        ResponseEntity<?> response = employeeService.deleteEmploye(empId);

        Mockito.verify(employeeRepo, Mockito.times(1)).deleteById(empId);
        assertNull(response);
    }
}
