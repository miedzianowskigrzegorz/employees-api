package pl.gm.employeesrest.employee.saga.employee;

import lombok.Data;
import pl.gm.employeesrest.employee.request.EmployeeCreateRequest;
import pl.gm.employeesrest.employee.response.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeData {

    private EmployeeCreateRequest employeeRequest;
    private List<EmployeeResponse> employees;

    public EmployeeData(EmployeeCreateRequest employeeRequest) {
        this.employeeRequest = employeeRequest;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(EmployeeResponse employee) {
        this.employees.add(employee);
    }

}
