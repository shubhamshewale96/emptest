package com.employeetest.DTO;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class EmployeeDTO {
    int id;
    @Size(min = 3, message = "Name is not valid At list 3 character.")
    @NotEmpty
    private String name;

    @Min(value = 500, message = "Salary must be greater than 500.")
    private double salary;

    @Pattern(regexp = "male|female")
    @NotEmpty
    public String gender;

    private String startDate;


}
