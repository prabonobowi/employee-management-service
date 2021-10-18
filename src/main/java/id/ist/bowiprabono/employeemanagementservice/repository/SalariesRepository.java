package id.ist.bowiprabono.employeemanagementservice.repository;

import id.ist.bowiprabono.employeemanagementservice.model.Salaries;
import id.ist.bowiprabono.employeemanagementservice.model.SalariesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface SalariesRepository extends JpaRepository<Salaries, SalariesKey> {

    @Query(value = "SELECT * FROM salaries s WHERE s.emp_no = ?1 AND s.from_date = ?2",
            nativeQuery = true)
    public Optional<Salaries> findBySalariesKey(Integer empNo, Date fromDate);

}
