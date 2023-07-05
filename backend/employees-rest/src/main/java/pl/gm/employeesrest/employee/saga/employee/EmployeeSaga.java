package pl.gm.employeesrest.employee.saga.employee;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.gm.employeesrest.employee.saga.employee.step.Step;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class EmployeeSaga {
    private List<Step> steps;

    public EmployeeSaga(List<Step> steps) {
        this.steps = steps;
    }

    public void execute(EmployeeData employeeData) {
        for (Step step : steps) {
            try {
                step.execute(employeeData);
            } catch (Exception e) {
                rollback(employeeData);
                throw new SagaExecutionException("Saga execution failed.", e);
            }
        }
    }

    private void rollback(EmployeeData employeeData) {
        for (int i = steps.size() - 1; i >= 0; i--) {
            try {
                steps.get(i).rollback(employeeData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
