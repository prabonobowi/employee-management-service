package id.ist.bowiprabono.employeemanagementservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import id.ist.bowiprabono.employeemanagementservice.constants.Gender;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employees implements Serializable {

    private static final long serialVersionUID = -4380621156222820768L;

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "hire_date")
    private Date hireDate;

    @JsonManagedReference(value="employees-titles")
    @OneToOne(mappedBy = "employees", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude private Titles titles;

    @JsonManagedReference(value="employees-salaries")
    @OneToOne(mappedBy = "employees", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude private Salaries salaries;

    @JsonManagedReference(value="employee-deptEmp")
    @OneToMany(mappedBy = "employees")
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude private List<DeptEmp> deptEmps;

    @JsonManagedReference(value="employee-deptManager")
    @OneToMany(mappedBy = "employees")
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude private List<DeptManager> deptManagers;

}
