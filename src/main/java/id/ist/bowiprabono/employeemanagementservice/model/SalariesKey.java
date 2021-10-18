package id.ist.bowiprabono.employeemanagementservice.model;

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
public class SalariesKey implements Serializable {

    private static final long serialVersionUID = 6002310677597332387L;

    private Integer empNo;
    private Date fromDate;
}
