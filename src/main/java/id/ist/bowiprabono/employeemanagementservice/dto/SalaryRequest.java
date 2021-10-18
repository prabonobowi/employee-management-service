package id.ist.bowiprabono.employeemanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRequest implements Serializable {

    private static final long serialVersionUID = 9102805181902275240L;

    private Integer empNo;
    private Date fromDate;
    private Integer salary;
    private Date toDate;

}
