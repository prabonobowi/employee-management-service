package id.ist.bowiprabono.employeemanagementservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dept_manager")
public class DeptManager implements Serializable {

    private static final long serialVersionUID = -3006526701481733036L;

    @EmbeddedId
    private DeptManagerKey deptManagerKey;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @JsonBackReference(value="employee-deptManager")
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("empNo")
    @JoinColumn(name = "emp_no", updatable = false, insertable = false)
    Employees employees;

    @JsonBackReference(value="departments-deptManager")
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("deptNo")
    @JoinColumn(name = "dept_no", updatable = false, insertable = false)
    Departments departments;

}
