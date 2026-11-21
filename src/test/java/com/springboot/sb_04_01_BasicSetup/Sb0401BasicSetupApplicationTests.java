package com.springboot.sb_04_01_BasicSetup;

import com.springboot.sb_04_01_BasicSetup.clients.EmployeeClient;
import com.springboot.sb_04_01_BasicSetup.dto.EmployeeDTO;
import com.springboot.sb_04_01_BasicSetup.entities.UserEntity;
import com.springboot.sb_04_01_BasicSetup.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class Sb0401BasicSetupApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		UserEntity user = new UserEntity(4L, "krishna@gmail.com", "1234", "krishna");
		String token = jwtService.generateAccessToken(user);
		System.out.println(token);
		Long id = jwtService.getUserIdFromToken(token);
		System.out.println("ID: " +  id);
	}

	@Test
	void getAllEmployeesTest() {
        List<EmployeeDTO> employeeDtoList = employeeClient.getAllEmployees();
		for (EmployeeDTO employeeDTO : employeeDtoList) {
			System.out.println(employeeDTO);
		}
	}

	@Test
	void getEmployeeByIdTest() {
		EmployeeDTO employeeDto = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDto);
	}

	@Test
	void postEmployeeTest() {
		EmployeeDTO employeeDto = new EmployeeDTO();
			employeeDto.setName("RestClient");
			employeeDto.setEmail("RestClien@gmail.com");
			employeeDto.setAge(24);
			employeeDto.setDoj(LocalDate.of(2026,8,30));
			employeeDto.setISActive(true);

		EmployeeDTO savedEmployeeDto = employeeClient.createNewEmployee(employeeDto);
		System.out.println(savedEmployeeDto);
	}
}
