package pl.gm.employeesrest.employee.service;

import org.springframework.http.ResponseEntity;
import pl.gm.employeesrest.employee.request.EmployeeEditRequest;
import pl.gm.employeesrest.employee.request.EmployeeCreateRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;

public interface IEmployeeCommandService {

    ResponseEntity<EmployeeResponse> createEmployee(EmployeeCreateRequest employeeFormRequest);
    ResponseEntity<EmployeeResponse> updateEmployee(EmployeeEditRequest employeeEditRequest);
    ResponseEntity<Void> deleteEmployee(Long id);

}
