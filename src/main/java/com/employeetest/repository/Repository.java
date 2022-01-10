package com.employeetest.repository;

import com.employeetest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Employee,Integer> {
}
