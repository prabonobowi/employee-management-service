package id.ist.bowiprabono.employeemanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest implements Serializable {

    private static final long serialVersionUID = -4377329705717523143L;

    private String deptNo;
    private String deptName;

}
