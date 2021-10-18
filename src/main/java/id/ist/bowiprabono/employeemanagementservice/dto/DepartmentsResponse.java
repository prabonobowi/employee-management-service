package id.ist.bowiprabono.employeemanagementservice.dto;

import id.ist.bowiprabono.employeemanagementservice.model.Departments;
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
public class DepartmentsResponse implements Serializable {

    private static final long serialVersionUID = -2562344322941696835L;

    private List<Departments> departments;

}
