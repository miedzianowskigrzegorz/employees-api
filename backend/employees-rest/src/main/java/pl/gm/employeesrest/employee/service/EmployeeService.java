package pl.gm.employeesrest.employee.service;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import pl.gm.employeesrest.employee.model.Employee;
import pl.gm.employeesrest.employee.repository.EmployeeRepository;
import pl.gm.employeesrest.employee.request.EmployeeFormRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, Validator validator) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(e -> modelMapper.map(e,EmployeeResponse.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody @Valid EmployeeFormRequest employeeFormRequest) {
        Employee employeeToSave = modelMapper.map(employeeFormRequest, Employee.class);
        Employee savedEmployee = employeeRepository.save(employeeToSave);
        EmployeeResponse employeeResponse = modelMapper.map(savedEmployee, EmployeeResponse.class);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeResponse);
    }

    public ResponseEntity<Void> deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
