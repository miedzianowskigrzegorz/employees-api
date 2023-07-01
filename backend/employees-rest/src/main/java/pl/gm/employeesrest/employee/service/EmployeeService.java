package pl.gm.employeesrest.employee.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pl.gm.employeesrest.employee.request.EmployeeFormRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();
    ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody @Valid EmployeeFormRequest employeeFormRequest);
    ResponseEntity<Void> deleteEmployee(Long id);

}
