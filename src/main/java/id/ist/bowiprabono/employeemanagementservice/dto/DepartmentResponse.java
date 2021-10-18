package id.ist.bowiprabono.employeemanagementservice.dto;

import id.ist.bowiprabono.employeemanagementservice.model.Departments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse implements Serializable {

    private static final long serialVersionUID = -1248669764092320795L;

    private Departments department;

}
