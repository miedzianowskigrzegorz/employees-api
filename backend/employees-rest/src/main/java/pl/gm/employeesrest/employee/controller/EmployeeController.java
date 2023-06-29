package pl.gm.employeesrest.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gm.employeesrest.employee.request.EmployeeFormRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;
import pl.gm.employeesrest.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeFormRequest employeeFormRequest) {
        ResponseEntity<EmployeeResponse> response = employeeService.saveEmployee(employeeFormRequest);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        ResponseEntity<Void> response = employeeService.deleteEmployee(id);
        return response;
    }
}
