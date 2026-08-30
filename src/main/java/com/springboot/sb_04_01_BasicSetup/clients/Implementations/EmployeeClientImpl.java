package com.springboot.sb_04_01_BasicSetup.clients.Implementations;

import com.springboot.sb_04_01_BasicSetup.advice.ApiResponse;
import com.springboot.sb_04_01_BasicSetup.clients.EmployeeClient;
import com.springboot.sb_04_01_BasicSetup.dto.EmployeeDTO;
import com.springboot.sb_04_01_BasicSetup.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class EmployeeClientImpl implements EmployeeClient {

    @Autowired
    private RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.error("error log");
        log.warn("warning log");
        log.info("information log");    //default logging level(error to info enabled)
        log.debug("debugging log");
        log.trace("trace log");

        try {
            ApiResponse<List<EmployeeDTO>> empDtoList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            return empDtoList.getData();
        }
        catch (RestClientException ex) {
            log.error("Exception Occured while Fetching the Employee List", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        try{
            ApiResponse<EmployeeDTO> employeeDtoRespone = restClient.get()
                    .uri("employees/{empId}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            log.info("Successfully fetched all employees");

            return employeeDtoRespone.getData();
        }
        catch (RestClientException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO newEmployee) {
        try{
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDtoRespone = restClient.post()
                    .uri("employees")
                    .body(newEmployee)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                        System.out.println(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Couldnn't create employee");
                    }))
                    // These errors can be handled by default for all APIs in the RestClientConfig
                    .onStatus(HttpStatusCode::is5xxServerError, ((request, response) -> {
                        System.out.println(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Server Error Occured");
                    }))
                    .toEntity(new ParameterizedTypeReference<>() {});
                    //.body(new ParameterizedTypeReference<>() {});

            return employeeDtoRespone.getBody().getData();
        }
        catch (RestClientException ex) {
            throw new RuntimeException(ex);
        }
    }
}
