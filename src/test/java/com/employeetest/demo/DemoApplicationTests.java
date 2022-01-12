package com.employeetest.demo;

import com.employeetest.model.Employee;
import com.employeetest.repository.Repository;
import com.employeetest.service.Service;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
class DemoApplicationTests {

    @Mock
    private Repository repository;

    @InjectMocks
    private Service service;

    private static Employee emp1;
    private static Employee emp2;
    private static List<Employee> e1 =null;

    @BeforeAll
    public static void init() {

        // create an Employee Entity object
        emp1 = new Employee(1, "Vinod", 40000, "male", LocalDateTime.parse("2018-12-30T19:34:50.63"),"Hr","abcd");
        emp2 = new Employee(2, "Shubham", 20000, "male", LocalDateTime.parse("2018-12-30T19:34:50.63"),"account","abcd");

        e1=new ArrayList<>();
        e1.add(emp1);
        e1.add(emp2);

    }
    //get all list employee
    @Test
    public void getAllTest() {

        Mockito.when(repository.findAll()).thenReturn(e1);
        List allEmployees = service.getAllEmp();

        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
    }

    //add employee
    @Test
    public void addEmployeeTest() {
        Mockito.when(repository.save(emp1)).thenReturn(emp1);
        Employee emp = service.save(emp1);
        assertNotNull(emp);
        assertEquals(emp1,emp);
    }


}
