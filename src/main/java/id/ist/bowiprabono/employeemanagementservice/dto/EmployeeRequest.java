package id.ist.bowiprabono.employeemanagementservice.dto;

import id.ist.bowiprabono.employeemanagementservice.constants.Gender;
import id.ist.bowiprabono.employeemanagementservice.model.DeptEmp;
import id.ist.bowiprabono.employeemanagementservice.model.DeptManager;
import id.ist.bowiprabono.employeemanagementservice.model.Salaries;
import id.ist.bowiprabono.employeemanagementservice.model.Titles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest implements Serializable {

    private static final long serialVersionUID = -5322202666996979551L;

    private Integer empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date hireDate;
    private Titles titles;
    private Salaries salaries;
    private List<DeptEmp> deptEmps;
    private List<DeptManager> deptManagers;

}
