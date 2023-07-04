package pl.gm.employeesrest.employee.service;

import org.springframework.http.ResponseEntity;
import pl.gm.employeesrest.employee.model.Employee;
import pl.gm.employeesrest.employee.response.EmployeeResponse;

import java.util.List;

public interface IEmployeeQueryService {

    ResponseEntity<List<EmployeeResponse>> getAllEmployees();
    ResponseEntity<EmployeeResponse> getEmployeeById(Long id);
    ResponseEntity<List<EmployeeResponse>> findEmployeesBySearchRequest(String searchRequest);

}
