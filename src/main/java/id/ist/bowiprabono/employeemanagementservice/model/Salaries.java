package id.ist.bowiprabono.employeemanagementservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "salaries")
@IdClass(SalariesKey.class)
public class Salaries implements Serializable {

    private static final long serialVersionUID = -3877305075289542068L;

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Id
    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "to_date")
    private Date toDate;

    @JsonBackReference(value="employees-salaries")
    @OneToOne
    @MapsId("empNo")
    @JoinColumn(name = "emp_no")
    @EqualsAndHashCode.Exclude private Employees employees;

}
