package pl.gm.employeesrest.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gm.employeesrest.employee.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE %:searchRequest% OR LOWER(e.lastName) LIKE %:searchRequest%")
    List<Employee> findEmployeesBySearchRequest(@Param("searchRequest") String searchRequest);

}
