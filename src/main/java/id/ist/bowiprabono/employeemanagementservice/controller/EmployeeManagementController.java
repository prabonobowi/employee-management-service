package id.ist.bowiprabono.employeemanagementservice.controller;

import id.ist.bowiprabono.employeemanagementservice.dto.*;
import id.ist.bowiprabono.employeemanagementservice.model.Departments;
import id.ist.bowiprabono.employeemanagementservice.model.Employees;
import id.ist.bowiprabono.employeemanagementservice.service.EmployeeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeManagementController {

    @Autowired
    private EmployeeManagementService employeeManagementService;

    // Employees
    @PostMapping(value = "/createEmployee", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeManagementService.createEmployee(employeeRequest);
    }

    @PostMapping(value = "/employees", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeesResponse> getEmployees() {
        return employeeManagementService.getEmployees();
    }

    @PostMapping(value = "/employee/{empNo}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable("empNo") Integer empNo) {
        return employeeManagementService.getEmployee(empNo);
    }

    @PostMapping(value = "/updateEmployee", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeManagementService.updateEmployee(employeeRequest);
    }

    @PostMapping(value = "/deleteEmployee/{empNo}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteEmployee(@PathVariable("empNo") Integer empNo) {
        return employeeManagementService.deleteEmployee(empNo);
    }

    // Departments
    @PostMapping(value = "/createDepartment", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return employeeManagementService.createDepartment(departmentRequest);
    }

    @PostMapping(value = "/departments", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentsResponse> getDepartments() {
        return employeeManagementService.getDepartments();
    }

    @PostMapping(value = "/department/{deptNo}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponse> getDepartment(@PathVariable("deptNo") String deptNo) {
        return employeeManagementService.getDepartment(deptNo);
    }

    @PostMapping(value = "/updateDepartment", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return employeeManagementService.updateDepartment(departmentRequest);
    }

    @PostMapping(value = "/deleteDepartment/{deptNo}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteDepartment(@PathVariable("deptNo") String deptNo) {
        return employeeManagementService.deleteDepartment(deptNo);
    }

    @PostMapping(value = "/updateSalary", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SalaryResponse> updateSalary(@RequestBody SalaryRequest salaryRequest) {
        return employeeManagementService.updateSalary(salaryRequest);
    }

    @PostMapping(value = "/updateTitle", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TitleResponse> updateTitle(@RequestBody TitleRequest titleRequest) {
        return employeeManagementService.updateTitle(titleRequest);
    }

}
