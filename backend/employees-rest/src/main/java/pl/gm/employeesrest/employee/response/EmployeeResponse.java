package pl.gm.employeesrest.employee.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private BigDecimal salary;
    private LocalDate startDate;

}
