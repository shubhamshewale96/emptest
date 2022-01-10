package com.employeetest.controller;

import com.employeetest.DTO.EmployeeDTO;
import com.employeetest.DTO.ResponseDTO;
import com.employeetest.converter.EntityConverter;
import com.employeetest.model.Employee;
import com.employeetest.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class EmployeeController {

    @Autowired
    private Service empService;

    @Autowired
    private EntityConverter convertor;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Employee app";
    }
    @GetMapping("/get")
    public ResponseEntity<List<EmployeeDTO>> getAllEmp() {
        List<Employee> allPerson = empService.getAllEmp();
        List<EmployeeDTO> dtoList = convertor.entityToDTO(allPerson);
        return new ResponseEntity<List<EmployeeDTO>>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/get/{eId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("eId") int eId) {
        Employee employee = empService.getEmpById(eId);
        EmployeeDTO employeeDTO = convertor.entityToDTO(employee);
        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> addEmployee( @Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertor.dtoToEntity(employeeDTO);
        Employee savedEmp = empService.save(employee);
        EmployeeDTO employeedto = convertor.entityToDTO(savedEmp);
        return new ResponseEntity<EmployeeDTO>(employeedto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") int id,  @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertor.dtoToEntity(employeeDTO);
        Employee updatedEmp = empService.update(id,employee);
        EmployeeDTO employeedto = convertor.entityToDTO(updatedEmp);
        return new ResponseEntity<EmployeeDTO>(employeedto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
        empService.deleteById(id);
        return new ResponseEntity<String>("Data deleted for id : " + id, HttpStatus.OK);
    }

}
