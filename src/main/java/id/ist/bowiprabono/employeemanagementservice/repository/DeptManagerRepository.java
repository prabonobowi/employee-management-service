package id.ist.bowiprabono.employeemanagementservice.repository;

import id.ist.bowiprabono.employeemanagementservice.model.DeptManager;
import id.ist.bowiprabono.employeemanagementservice.model.DeptManagerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptManagerKey> {
}
