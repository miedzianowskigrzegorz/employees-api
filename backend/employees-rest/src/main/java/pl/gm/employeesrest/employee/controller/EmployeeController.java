package pl.gm.employeesrest.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gm.employeesrest.employee.request.EmployeeEditRequest;
import pl.gm.employeesrest.employee.request.EmployeeCreateRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;
import pl.gm.employeesrest.employee.service.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
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
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeCreateRequest employeeFormRequest) {
        ResponseEntity<EmployeeResponse> response = employeeServiceImpl.createEmployee(employeeFormRequest);
        return response;
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
