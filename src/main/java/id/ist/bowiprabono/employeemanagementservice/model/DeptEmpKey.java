package id.ist.bowiprabono.employeemanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeptEmpKey implements Serializable {

    private static final long serialVersionUID = -451741004846371637L;

    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "dept_no")
    private String deptNo;

}
