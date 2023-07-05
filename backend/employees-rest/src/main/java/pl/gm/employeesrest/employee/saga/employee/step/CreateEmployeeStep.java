package pl.gm.employeesrest.employee.saga.employee.step;
import org.springframework.beans.factory.annotation.Autowired;
import pl.gm.employeesrest.employee.saga.employee.EmployeeData;
import pl.gm.employeesrest.employee.service.EmployeeServiceImpl;

public class CreateEmployeeStep implements Step {
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public CreateEmployeeStep(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(EmployeeData employeeData) {
        employeeService.createEmployee(employeeData);
    }

    @Override
    public void rollback(EmployeeData employeeData) {
        employeeService.deleteEmployee(employeeData.getEmployeeRequest().getId());
    }

}
