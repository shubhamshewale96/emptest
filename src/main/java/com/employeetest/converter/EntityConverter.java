package com.employeetest.converter;
import org.modelmapper.ModelMapper;
import com.employeetest.DTO.EmployeeDTO;
import com.employeetest.model.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class EntityConverter {
    public Employee dtoToEntity(EmployeeDTO employeeDTO) {
        ModelMapper mapper = new ModelMapper();

        Employee employee = mapper.map(employeeDTO, Employee.class);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parse = LocalDate.parse(employeeDTO.getStartDate(), dtf);
        LocalDateTime startDateTime = parse.atStartOfDay();
        employee.setStartDate((startDateTime));
        return employee;
    }

    public EmployeeDTO entityToDTO(Employee employee) {
        ModelMapper mapper = new ModelMapper();
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }

    public List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTOList) {
        return employeeDTOList.stream().map(dto -> dtoToEntity(dto)).collect(Collectors.toList());
    }

    public List<EmployeeDTO> entityToDTO(List<Employee> employeeList) {
        return employeeList.stream().map(employee -> entityToDTO(employee)).collect(Collectors.toList());
    }
}
