package pl.gm.employeesrest.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gm.employeesrest.employee.request.EmployeeEditRequest;
import pl.gm.employeesrest.employee.request.EmployeeFormRequest;
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
        List<EmployeeResponse> employees = employeeServiceImpl.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeResponse employee = employeeServiceImpl.getEmployeeById(id);

        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeFormRequest employeeFormRequest) {
        ResponseEntity<EmployeeResponse> response = employeeServiceImpl.saveEmployee(employeeFormRequest);
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
}
