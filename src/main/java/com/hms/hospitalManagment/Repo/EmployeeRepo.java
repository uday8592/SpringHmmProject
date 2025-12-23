package com.hms.hospitalManagment.Repo;

import com.hms.hospitalManagment.Entity1.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
