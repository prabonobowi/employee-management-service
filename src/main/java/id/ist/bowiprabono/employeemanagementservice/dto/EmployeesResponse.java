package id.ist.bowiprabono.employeemanagementservice.dto;

import id.ist.bowiprabono.employeemanagementservice.model.Employees;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesResponse implements Serializable {

    private static final long serialVersionUID = -7324822320899963355L;

    private List<Employees> employees;

}
