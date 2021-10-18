package id.ist.bowiprabono.employeemanagementservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Departments implements Serializable {

    private static final long serialVersionUID = 8618010009086780176L;

    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "dept_name")
    private String deptName;

    @JsonManagedReference(value="departments-deptEmp")
    @OneToMany(mappedBy = "departments")
    @EqualsAndHashCode.Exclude private Set<DeptEmp> deptEmps;

    @JsonManagedReference(value="departments-deptManager")
    @OneToMany(mappedBy = "departments")
    @EqualsAndHashCode.Exclude private Set<DeptManager> deptManagers;

}
