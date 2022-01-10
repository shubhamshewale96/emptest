package com.employeetest.service;

import com.employeetest.exception.EmpException;
import com.employeetest.model.Employee;
import com.employeetest.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
public class Service {


    @Autowired
    private Repository empRepository;

    public List<Employee> getAllEmp() {
        List<Employee> empList = empRepository.findAll();
        if (empList.isEmpty()) {
            throw new EmpException("Employee Payroll Data is Empty");
        }
        return empList;
    }

    public Employee getEmpById(int id) {
        Employee employee = empRepository.findById(id).orElseThrow(() -> new EmpException(
                "Employee Data is not present with ID : " + id + ", Please Check the ID."));
        return employee;
    }


    public Employee save(Employee emp) {
        Employee employee = empRepository.save(emp);
        return employee;
    }


    public Employee update(int id, Employee emp) {
        Optional<Employee> empOptional = empRepository.findById(id);
        if (empOptional.isPresent()) {
            Employee employee = empOptional.get();
            employee.setName(emp.getName());
            employee.setSalary(emp.getSalary());
            employee.setGender(emp.getGender());
            employee.setStartDate(emp.getStartDate());
            return empRepository.save(employee);
        } else {
            throw new EmpException(
                    "Employee Data is not present with ID : " + id + ", Please Check the ID.");
        }
    }

    public void deleteById(int id) {
        empRepository.deleteById(id);
    }
}
