package id.ist.bowiprabono.employeemanagementservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dept_emp")
public class DeptEmp implements Serializable {

    private static final long serialVersionUID = -2112724559472756318L;

    @EmbeddedId
    private DeptEmpKey deptEmpKey;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @JsonBackReference(value="employee-deptEmp")
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("empNo")
    @JoinColumn(name = "emp_no", updatable = false, insertable = false)
    @EqualsAndHashCode.Exclude private Employees employees;

    @JsonBackReference(value="departments-deptEmp")
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("deptNo")
    @JoinColumn(name = "dept_no", updatable = false, insertable = false)
    @EqualsAndHashCode.Exclude private Departments departments;

}
