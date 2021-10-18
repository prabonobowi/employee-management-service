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
public class TitlesKey implements Serializable {

    private static final long serialVersionUID = -5770859836151139927L;

    private Integer empNo;
    private String title;
    private Date fromDate;

}
