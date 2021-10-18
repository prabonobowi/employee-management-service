# employee-management-service
This project used H2 database, so no more configuration needed.

This project run in 8080 port, check application.properties to see the configuration.

Available API list:
Employees
  http://localhost:8080/api/employeemanagementservice/employees
  http://localhost:8080/api/employeemanagementservice/employee/{empNo}
  http://localhost:8080/api/employeemanagementservice/deleteEmployee/{empNo}
  http://localhost:8080/api/employeemanagementservice/createEmployee
  http://localhost:8080/api/employeemanagementservice/updateEmployee

Departments
  http://localhost:8080/api/employeemanagementservice/createDepartment
  http://localhost:8080/api/employeemanagementservice/deleteDepartment/{deptNo}
  http://localhost:8080/api/employeemanagementservice/departments
  http://localhost:8080/api/employeemanagementservice/department/{deptNo}
  http://localhost:8080/api/employeemanagementservice/updateDepartment

Salaries
  http://localhost:8080/api/employeemanagementservice/updateSalary

Titles
  http://localhost:8080/api/employeemanagementservice/updateTitle

By default, there should be 1 employee data for testing. You can change the data in data.sql

It is not perfect, I try my best to finish this project in my spare time, I hope this is enough.

Thanks
