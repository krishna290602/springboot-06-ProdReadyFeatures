package com.springboot.sb_04_01_BasicSetup.clients;

import com.springboot.sb_04_01_BasicSetup.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO createNewEmployee(EmployeeDTO newEmployee);
}
