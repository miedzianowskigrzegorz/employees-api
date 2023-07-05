package pl.gm.employeesrest.employee.saga.employee.step;

import pl.gm.employeesrest.employee.saga.employee.EmployeeData;

public interface Step {

    void execute(EmployeeData data);
    void rollback(EmployeeData data);

}
