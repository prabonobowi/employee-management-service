package id.ist.bowiprabono.employeemanagementservice.repository;

import id.ist.bowiprabono.employeemanagementservice.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
}
