package com.eHotel.eHotel.repo;

import com.eHotel.eHotel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

