package id.ist.bowiprabono.employeemanagementservice.repository;

import id.ist.bowiprabono.employeemanagementservice.model.DeptEmp;
import id.ist.bowiprabono.employeemanagementservice.model.DeptEmpKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpKey> {
}
