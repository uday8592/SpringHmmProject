package com.hms.hospitalManagment.Service;

import com.hms.hospitalManagment.Entity.Employee;
import com.hms.hospitalManagment.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployes() {
        return employeeRepo.findAll();
    }

    public ResponseEntity<?> deleteEmploye(int empid) {
        employeeRepo.deleteById(empid);
        return null;
    }
}
