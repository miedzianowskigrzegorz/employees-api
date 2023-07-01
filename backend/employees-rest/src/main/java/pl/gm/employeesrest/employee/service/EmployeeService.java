package pl.gm.employeesrest.employee.service;

import org.springframework.http.ResponseEntity;
import pl.gm.employeesrest.employee.request.EmployeeFormRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();
    ResponseEntity<EmployeeResponse> saveEmployee(EmployeeFormRequest employeeFormRequest);
    ResponseEntity<Void> deleteEmployee(Long id);

}
