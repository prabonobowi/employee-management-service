package id.ist.bowiprabono.employeemanagementservice.service;

import id.ist.bowiprabono.employeemanagementservice.dto.*;
import id.ist.bowiprabono.employeemanagementservice.model.*;
import id.ist.bowiprabono.employeemanagementservice.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeManagementService {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Autowired
    private DeptEmpRepository deptEmpRepository;

    @Autowired
    private DeptManagerRepository deptManagerRepository;

    @Autowired
    private SalariesRepository salariesRepository;

    @Autowired
    private TitlesRepository titlesRepository;

    // Create employee
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<EmployeeResponse> createEmployee(EmployeeRequest request) {
        log.info("Starting createEmployee service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Optional<Employees> employeesOptional = employeesRepository.findById(request.getEmpNo());
            if (employeesOptional.isEmpty()) {
                Titles title = request.getTitles();
                title.setEmpNo(request.getEmpNo());

                Salaries salary = request.getSalaries();
                salary.setEmpNo(request.getEmpNo());

                Employees employee = Employees.builder()
                        .empNo(request.getEmpNo())
                        .birthDate(request.getBirthDate())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .gender(request.getGender())
                        .hireDate(request.getHireDate())
                        .titles(title)
                        .salaries(salary)
                        .build();
                employeesRepository.save(employee);

                // Set DeptEmp if request request.getDeptEmps() not null
                List<DeptEmp> deptEmps = new ArrayList<>();
                if (request.getDeptEmps() != null) {
                    for (DeptEmp deptEmp : request.getDeptEmps()) {
                        DeptEmpKey deptEmpKey = deptEmp.getDeptEmpKey();
                        Optional<Departments> departments = departmentsRepository.findById(deptEmpKey.getDeptNo());
                        if (departments.isPresent()) {
                            DeptEmp deptEmp1 = DeptEmp.builder()
                                    .deptEmpKey(DeptEmpKey.builder()
                                            .empNo(request.getEmpNo())
                                            .deptNo(deptEmpKey.getDeptNo())
                                            .build())
                                    .fromDate(deptEmp.getFromDate())
                                    .toDate(deptEmp.getToDate())
                                    .employees(employee)
                                    .departments(departments.get())
                                    .build();
                            deptEmps.add(deptEmp1);
                            deptEmpRepository.save(deptEmp1);
                        } else {
                            throw new Exception("Set DeptEmp failed! Department " + deptEmpKey.getDeptNo() + " does not exist!");
                        }
                    }
                }

                // Set DeptEmp if request request.getDeptManagers() not null
                List<DeptManager> deptManagers = new ArrayList<>();
                if (request.getDeptManagers() != null) {
                    for (DeptManager deptManager : request.getDeptManagers()) {
                        DeptManagerKey deptManagerKey = deptManager.getDeptManagerKey();
                        Optional<Departments> departments = departmentsRepository.findById(deptManagerKey.getDeptNo());
                        if (departments.isPresent()) {
                            DeptManager deptManager1 = DeptManager.builder()
                                    .deptManagerKey(DeptManagerKey.builder()
                                            .empNo(request.getEmpNo())
                                            .deptNo(deptManagerKey.getDeptNo())
                                            .build())
                                    .fromDate(deptManager.getFromDate())
                                    .toDate(deptManager.getToDate())
                                    .employees(employee)
                                    .departments(departments.get())
                                    .build();
                            deptManagers.add(deptManager1);
                            deptManagerRepository.save(deptManager1);
                        }
                    }
                }

                // Set deptEmps and deptManagers for return value
                employee.setDeptEmps(deptEmps);
                employee.setDeptManagers(deptManagers);

                return ResponseEntity.status(httpStatus).body(EmployeeResponse.builder()
                        .employee(employee)
                        .build());
            } else {
                httpStatus = HttpStatus.FORBIDDEN;
                throw new Exception("Employee already exist!");
            }
        } catch (Exception e) {
            log.error("Create new employee failed, transaction will be rollback! {}", e);
            return ResponseEntity.status(httpStatus).body(null);
        }
    }

    // Read employee
    public ResponseEntity<EmployeesResponse> getEmployees() {
        log.info("Starting getEmployees service");
        try {
            List<Employees> employees = employeesRepository.findAll();
            if (!employees.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(EmployeesResponse.builder()
                        .employees(employees)
                        .build());
            } else {
                throw new Exception("Employees not found!");
            }
        } catch (Exception e) {
            log.error("Get employees failed! {}", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<EmployeeResponse> getEmployee(Integer empNo) {
        log.info("Starting getEmployee service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            if (empNo != null) {
                Optional<Employees> employeesOptional = employeesRepository.findById(empNo);
                if (employeesOptional.isPresent()) {
                    return ResponseEntity.status(httpStatus).body(EmployeeResponse.builder()
                            .employee(employeesOptional.get())
                            .build());
                } else {
                    httpStatus = HttpStatus.NOT_FOUND;
                    throw new Exception("Can't find employee with empNo " + empNo);
                }
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
                throw new Exception("Parameter empNo is empty");
            }
        } catch (Exception e) {
            log.error("Get employee failed! {}", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update employee
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<EmployeeResponse> updateEmployee(EmployeeRequest employeeRequest) {
        log.info("Starting updateEmployee service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            if (employeeRequest.getEmpNo() != null) {
                Integer empNo = employeeRequest.getEmpNo();
                Optional<Employees> employeesOptional = employeesRepository.findById(empNo);
                if (employeesOptional.isPresent()) {
                    Employees employee = employeesOptional.get();
                    employee.setBirthDate(employeeRequest.getBirthDate());
                    employee.setFirstName(employeeRequest.getFirstName());
                    employee.setLastName(employeeRequest.getLastName());
                    employee.setGender(employeeRequest.getGender());
                    employee.setHireDate(employeeRequest.getHireDate());

                    if (employeeRequest.getTitles() != null) {
                        employee.getTitles().setToDate(employeeRequest.getTitles().getToDate());
                    }

                    if (employeeRequest.getSalaries() != null) {
                        employee.getSalaries().setSalary(employeeRequest.getSalaries().getSalary());
                        employee.getSalaries().setToDate(employeeRequest.getSalaries().getToDate());
                    }

                    // Update deptEmp if empNo and deptNo exist
                    if (employeeRequest.getDeptEmps() != null) {
                        List<DeptEmp> deptEmps = new ArrayList<>();
                        for (DeptEmp deptEmp : employee.getDeptEmps()) {
                            deptEmpRepository.delete(deptEmp);
                        }
                        for (DeptEmp deptEmp : employeeRequest.getDeptEmps()) {
                            DeptEmpKey deptEmpKey = deptEmp.getDeptEmpKey();
                            Optional<Departments> departments = departmentsRepository.findById(deptEmpKey.getDeptNo());
                            if (departments.isPresent()) {
                                DeptEmp deptEmp1 = DeptEmp.builder()
                                        .deptEmpKey(DeptEmpKey.builder()
                                                .empNo(empNo)
                                                .deptNo(departments.get().getDeptNo())
                                                .build())
                                        .fromDate(deptEmp.getFromDate())
                                        .toDate(deptEmp.getToDate())
                                        .employees(employee)
                                        .departments(departments.get())
                                        .build();
                                deptEmps.add(deptEmp1);
                                deptEmpRepository.save(deptEmp1);
                            } else {
                                httpStatus = HttpStatus.BAD_REQUEST;
                                throw new Exception("Set DeptEmp failed! Department " + deptEmpKey.getDeptNo() + " does not exist!");
                            }
                        }
                        employee.setDeptEmps(deptEmps);
                    }

                    // Update deptManager if empNo and deptNo exist
                    if (employeeRequest.getDeptManagers() != null) {
                        List<DeptManager> deptManagers = new ArrayList<>();
                        for (DeptManager deptManager : employee.getDeptManagers()) {
                            deptManagerRepository.delete(deptManager);
                        }
                        for (DeptManager deptManager : employeeRequest.getDeptManagers()) {
                            DeptManagerKey deptManagerKey = deptManager.getDeptManagerKey();
                            Optional<Departments> departments = departmentsRepository.findById(deptManagerKey.getDeptNo());
                            if (departments.isPresent()) {
                                DeptManager deptManager1 = DeptManager.builder()
                                        .deptManagerKey(DeptManagerKey.builder()
                                                .empNo(empNo)
                                                .deptNo(departments.get().getDeptNo())
                                                .build())
                                        .fromDate(deptManager.getFromDate())
                                        .toDate(deptManager.getToDate())
                                        .employees(employee)
                                        .departments(departments.get())
                                        .build();
                                deptManagers.add(deptManager1);
                                deptManagerRepository.save(deptManager1);
                            } else {
                                httpStatus = HttpStatus.BAD_REQUEST;
                                throw new Exception("Set DeptEmp failed! Department " + deptManagerKey.getDeptNo() + " does not exist!");
                            }
                        }
                        employee.setDeptManagers(deptManagers);
                    }

                    employeesRepository.save(employee);
                    return ResponseEntity.status(httpStatus).body(EmployeeResponse.builder()
                            .employee(employee)
                            .build());
                } else {
                    httpStatus = HttpStatus.NOT_FOUND;
                    throw new Exception("Employee not found!");
                }
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
                throw new Exception("Parameter empNo is empty");
            }
        } catch (Exception e) {
            log.error("Get employee failed! {}", e);
            return ResponseEntity.status(httpStatus).body(null);
        }
    }

    // Delete employee
    public ResponseEntity deleteEmployee(Integer empNo) {
        log.info("Starting deleteEmployee service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            if (empNo != null) {
                Optional<Employees> employeesOptional = employeesRepository.findById(empNo);
                if (employeesOptional.isPresent()) {
                    employeesRepository.delete(employeesOptional.get());
                } else {
                    httpStatus = HttpStatus.NOT_FOUND;
                }
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            log.error("Error deleting employee number {}", empNo);
        } finally {
            return ResponseEntity.status(httpStatus).body(null);
        }
    }

    // Create department
    public ResponseEntity<DepartmentResponse> createDepartment(DepartmentRequest departmentRequest) {
        log.info("Starting createDepartment service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Optional<Departments> departmentsOptional = departmentsRepository.findById(departmentRequest.getDeptNo());
            if (departmentsOptional.isEmpty()) {
                Departments department = Departments.builder()
                        .deptNo(departmentRequest.getDeptNo())
                        .deptName(departmentRequest.getDeptName())
                        .build();

                departmentsRepository.save(department);
                return ResponseEntity.status(httpStatus).body(DepartmentResponse.builder()
                        .department(department)
                        .build());
            } else {
                httpStatus = HttpStatus.FORBIDDEN;
                throw new Exception("Department " + departmentRequest.getDeptNo() + " already exist");
            }
        } catch (Exception e) {
            log.error("Creating new department failed: {}", e);
            return ResponseEntity.status(httpStatus).body(null);
        }
    }

    // Read department
    public ResponseEntity<DepartmentsResponse> getDepartments() {
        log.info("Starting getDepartments service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            List<Departments> departments = departmentsRepository.findAll();
            if (!departments.isEmpty()) {
                return ResponseEntity.status(httpStatus).body(DepartmentsResponse.builder()
                        .departments(departments)
                        .build());
            } else {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new Exception("No employee found");
            }
        } catch (Exception e) {
            log.error("Get departments failed: {}", e);
            return ResponseEntity.status(httpStatus).body(null);
        }
    }

    public ResponseEntity<DepartmentResponse> getDepartment(String deptNo) {
        log.info("Starting getDepartment service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            if (deptNo != null && !deptNo.trim().isEmpty()) {
                Optional<Departments> departmentsOptional = departmentsRepository.findById(deptNo);
                if (departmentsOptional.isPresent()) {
                    return ResponseEntity.status(httpStatus).body(DepartmentResponse.builder()
                            .department(departmentsOptional.get())
                            .build());
                } else {
                    httpStatus = HttpStatus.NOT_FOUND;
                    throw new Exception("Can't find department with deptNo " + deptNo);
                }
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
                throw new Exception("Parameter deptNo is empty");
            }
        } catch (Exception e) {
            log.error("Get department failed! {}", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update department
    public ResponseEntity<DepartmentResponse> updateDepartment(DepartmentRequest departmentRequest) {
        log.info("Starting updateDepartment service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Optional<Departments> departmentsOptional = departmentsRepository.findById(departmentRequest.getDeptNo());
            if (departmentsOptional.isPresent()) {
                Departments department = departmentsOptional.get();
                department.setDeptName(departmentRequest.getDeptName());

                departmentsRepository.save(department);
                return ResponseEntity.status(httpStatus).body(DepartmentResponse.builder()
                        .department(department)
                        .build());
            } else {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new Exception("Department " + departmentRequest.getDeptNo() + " not found");
            }
        } catch (Exception e) {
            log.error("Update department failed! {}", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete department
    public ResponseEntity deleteDepartment(String deptNo) {
        log.info("Starting deleteDepartment service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            if (deptNo != null && !deptNo.trim().isEmpty()) {
                Optional<Departments> departmentsOptional = departmentsRepository.findById(deptNo);
                if (departmentsOptional.isPresent()) {
                    departmentsRepository.delete(departmentsOptional.get());
                } else {
                    httpStatus = HttpStatus.NOT_FOUND;
                }
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            log.error("Error deleting department {}", deptNo);
        } finally {
            return ResponseEntity.status(httpStatus).body(null);
        }
    }

    // Update salary
    public ResponseEntity<SalaryResponse> updateSalary(SalaryRequest salaryRequest) {
        log.info("Starting updateSalary service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Optional<Salaries> salariesOptional = salariesRepository.findBySalariesKey(salaryRequest.getEmpNo(), salaryRequest.getFromDate());

//                    salariesRepository.findById(SalariesKey.builder()
//                    .empNo(salaryRequest.getEmpNo())
//                    .fromDate(salaryRequest.getFromDate())
//                    .build());
            if (salariesOptional.isPresent()) {
                Salaries salary = salariesOptional.get();
                salary.setSalary(salaryRequest.getSalary());
                salary.setToDate(salaryRequest.getToDate());

                salariesRepository.save(salary);
                return ResponseEntity.status(httpStatus).body(SalaryResponse.builder()
                        .salary(salary)
                        .build());
            } else {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new Exception("Salary with empNo " + salaryRequest.getEmpNo() + " not found");
            }
        } catch (Exception e) {
            log.error("Update salary failed! {}", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update title
    public ResponseEntity<TitleResponse> updateTitle(TitleRequest titleRequest) {
        log.info("Starting updateTitle service");
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Optional<Titles> titlesOptional = titlesRepository.findById(TitlesKey.builder()
                    .empNo(titleRequest.getEmpNo())
                    .title(titleRequest.getTitle())
                    .fromDate(titleRequest.getFromDate())
                    .build());
            if (titlesOptional.isPresent()) {
                Titles title = titlesOptional.get();
                title.setToDate(titleRequest.getToDate());

                titlesRepository.save(title);
                return ResponseEntity.status(httpStatus).body(TitleResponse.builder()
                        .title(title)
                        .build());
            } else {
                httpStatus = HttpStatus.NOT_FOUND;
                throw new Exception("Title with empNo " + titleRequest.getEmpNo() + " not found");
            }
        } catch (Exception e) {
            log.error("Update salary failed! {}", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
