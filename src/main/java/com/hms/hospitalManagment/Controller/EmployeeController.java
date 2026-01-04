package com.hms.hospitalManagment.Controller;

import com.hms.hospitalManagment.Entity.Appointment;
import com.hms.hospitalManagment.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.hms.hospitalManagment.Entity.Employee;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RestTemplate restTemplate;


    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    private final String url_Pattern =
            "http://localhost:8081/saveAppointment";

    @PostMapping("/saveEmployee")
    @Transactional
    public Employee saveEmployee(@RequestBody Employee employee) {
        if ("Patient".equalsIgnoreCase(employee.getRoleType())) {
            Appointment appointment = SetAppointmentData(employee);
            HttpEntity<Appointment> requestEntity = new HttpEntity<>(appointment);
            ResponseEntity<Appointment> responseEntity = restTemplate.exchange(url_Pattern, HttpMethod.POST, requestEntity,Appointment.class);
            logger.info("Appointment saved successfully {}", responseEntity.getBody());
        }
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/getEmployee")
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployes();
    }
    @PutMapping("/updateEmployee/{Empid}")
    public Employee updateEmployee(@RequestParam int Empid,@RequestBody Employee employee){
        employee.setEmpId(Empid);
        return employeeService.saveEmployee(employee);
    }
    @DeleteMapping("/deleteEmployee/{Empid}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int Empid){
        return employeeService.deleteEmploye(Empid);
    }

    private Appointment SetAppointmentData(Employee employee){
       Appointment appointment=new Appointment();
        appointment.setPatientId(employee.getEmpId());
        appointment.setEmail(employee.getEmail());
        appointment.setPatientFName(employee.getEmpFname());
        appointment.setPatientLName(employee.getEmpLname());
        appointment.setStatus(employee.getStatus());
        appointment.setComments(employee.getComments());
        appointment.setIssueDesc(employee.getIssueDesc());
        appointment.setMobileNumber(employee.getMobileNumber());
        appointment.setSpecialization(employee.getSpecialization());
        return appointment;
    }
}
