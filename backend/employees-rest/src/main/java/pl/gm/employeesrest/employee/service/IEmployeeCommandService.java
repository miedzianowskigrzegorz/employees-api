package pl.gm.employeesrest.employee.service;

import org.springframework.http.ResponseEntity;
import pl.gm.employeesrest.employee.request.EmployeeEditRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;
import pl.gm.employeesrest.employee.saga.employee.EmployeeData;

public interface IEmployeeCommandService {

    ResponseEntity<EmployeeResponse> createEmployee(EmployeeData data);
    ResponseEntity<EmployeeResponse> updateEmployee(EmployeeEditRequest employeeEditRequest);
    ResponseEntity<Void> deleteEmployee(Long id);

}
