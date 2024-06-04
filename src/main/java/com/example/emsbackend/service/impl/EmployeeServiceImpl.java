package com.example.emsbackend.service.impl;

import com.example.emsbackend.Mapper.EmployeeMapper;
import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.entity.Employee;
import com.example.emsbackend.exception.ResourceNotFoundException;
import com.example.emsbackend.repository.EmployeeRepository;
import com.example.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


   private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee with given Id does not exist:"+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees= employeeRepository.findAll();
        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {

Employee employee= employeeRepository.findById(employeeId).orElseThrow(
        ()-> new ResourceNotFoundException("Employee with given id does not Exist" + employeeId)

);
employee.setFirstName(updateEmployee.getFirstName());
employee.setLastName(updateEmployee.getLastName());
employee.setEmail(updateEmployee.getEmail());

Employee updatedEmployeeOBj=employeeRepository.save(employee);


        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeOBj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee with given id does not Exist" + employeeId)

        );
 employeeRepository.deleteById(employeeId);
    }
}
