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
public class TitleRequest implements Serializable {

    private static final long serialVersionUID = -2898768922065159980L;

    private Integer empNo;
    private String title;
    private Date fromDate;
    private Date toDate;

}
