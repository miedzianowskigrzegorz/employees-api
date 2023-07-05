package pl.gm.employeesrest.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gm.employeesrest.employee.request.EmployeeEditRequest;
import pl.gm.employeesrest.employee.request.EmployeeCreateRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;
import pl.gm.employeesrest.employee.saga.employee.EmployeeData;
import pl.gm.employeesrest.employee.saga.employee.EmployeeSaga;
import pl.gm.employeesrest.employee.saga.employee.step.CreateEmployeeStep;
import pl.gm.employeesrest.employee.service.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;
    private final EmployeeSaga employeeSaga;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl, EmployeeSaga employeeSaga) {
        this.employeeServiceImpl = employeeServiceImpl;
        this.employeeSaga = employeeSaga;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        ResponseEntity<List<EmployeeResponse>> response = employeeServiceImpl.getAllEmployees();
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") Long id) {
        ResponseEntity<EmployeeResponse> response = employeeServiceImpl.getEmployeeById(id);
        return response;
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeCreateRequest request) {
        EmployeeData data = new EmployeeData(request);

        CreateEmployeeStep createEmployeeStep = new CreateEmployeeStep(employeeServiceImpl);
        try {
            createEmployeeStep.execute(data);
            return ResponseEntity.ok("Employee created successfully.");
        } catch (Exception e) {
            createEmployeeStep.rollback(data);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create employee.");
        }
    }

    @PutMapping
    public ResponseEntity<EmployeeResponse> editEmployee(@RequestBody EmployeeEditRequest employeeEditRequest) {
        ResponseEntity<EmployeeResponse> response = employeeServiceImpl.updateEmployee(employeeEditRequest);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        ResponseEntity<Void> response = employeeServiceImpl.deleteEmployee(id);
        return response;
    }

    @GetMapping("/search/{searchRequest}")
    public ResponseEntity<List<EmployeeResponse>> searchEmployees(@PathVariable String searchRequest) {
        ResponseEntity<List<EmployeeResponse>> response = employeeServiceImpl.findEmployeesBySearchRequest(searchRequest);
        return response;
    }
}
