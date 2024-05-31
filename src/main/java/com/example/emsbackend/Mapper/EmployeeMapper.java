package com.example.emsbackend.Mapper;

import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.entity.Employee;

public class EmployeeMapper {
// transfer data from server to client and vice  versa

    public  static EmployeeDto mapToEmployeeDto(Employee employee){
      return new EmployeeDto(
              employee.getId(),
              employee.getFirstName(),
              employee.getLastName(),
              employee.getEmail()
      );
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new  Employee(
           employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
