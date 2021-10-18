package id.ist.bowiprabono.employeemanagementservice.dto;

import id.ist.bowiprabono.employeemanagementservice.model.Salaries;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryResponse implements Serializable {

    private static final long serialVersionUID = 3658991826455395252L;

    private Salaries salary;

}
