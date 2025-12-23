package com.hms.hospitalManagment.Controller;

import com.hms.hospitalManagment.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hms.hospitalManagment.Entity.Employee;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/getEmployee")
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployes();
    }
    @PutMapping("/updateEmployee/{Empid}")
    public Employee updateEmployee(@RequestParam int EmpId,@RequestBody Employee employee){
        employee.setEmpId(EmpId);
        return employeeService.saveEmployee(employee);
    }
    @DeleteMapping("/deleteEmployee/{Empid}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int Empid){
        return employeeService.deleteEmploye(Empid);
    }
}
