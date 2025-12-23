package com.hms.hospitalManagment.Service;

import com.hms.hospitalManagment.Entity1.Employee;
import com.hms.hospitalManagment.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
}
