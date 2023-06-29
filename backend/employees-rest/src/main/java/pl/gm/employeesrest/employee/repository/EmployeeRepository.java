package pl.gm.employeesrest.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gm.employeesrest.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
