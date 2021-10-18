package id.ist.bowiprabono.employeemanagementservice.repository;

import id.ist.bowiprabono.employeemanagementservice.model.Titles;
import id.ist.bowiprabono.employeemanagementservice.model.TitlesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitlesRepository extends JpaRepository<Titles, TitlesKey> {
}
