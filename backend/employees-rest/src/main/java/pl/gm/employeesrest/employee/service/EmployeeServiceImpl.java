package pl.gm.employeesrest.employee.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import pl.gm.employeesrest.employee.model.Employee;
import pl.gm.employeesrest.employee.repository.EmployeeRepository;
import pl.gm.employeesrest.employee.request.EmployeeEditRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;
import pl.gm.employeesrest.employee.saga.employee.EmployeeData;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class EmployeeServiceImpl implements IEmployeeQueryService,IEmployeeCommandService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, Validator validator) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeResponses);
    }

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
            return ResponseEntity.ok(employeeResponse);
        } else {
            throw new EntityNotFoundException("Employee with the given ID not found.");
        }
    }

    @Override
    public ResponseEntity<EmployeeResponse> createEmployee(EmployeeData data) {
        Employee employeeToSave = modelMapper.map(data.getEmployeeRequest(), Employee.class);
        Employee savedEmployee = employeeRepository.save(employeeToSave);
        EmployeeResponse employeeResponse = modelMapper.map(savedEmployee, EmployeeResponse.class);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeResponse);
    }

    @Override
    public ResponseEntity<EmployeeResponse> updateEmployee(EmployeeEditRequest employeeEditRequest) {
        if (employeeRepository.existsById(employeeEditRequest.getId())) {

            Employee updatedEmployee = modelMapper.map(employeeEditRequest, Employee.class);

            employeeRepository.save(updatedEmployee);

            EmployeeResponse employeeResponse = modelMapper.map(updatedEmployee, EmployeeResponse.class);

            return ResponseEntity.ok(employeeResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<EmployeeResponse>> findEmployeesBySearchRequest(String searchRequest) {
        List<Employee> employees = employeeRepository.findEmployeesBySearchRequest(searchRequest);

        List<EmployeeResponse> employeeResponses = employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(employeeResponses);
    }
}
