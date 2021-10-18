INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES
    (1, '2001-01-01', 'First', 'Dummy', 'M', '2021-10-15')
    ;

INSERT INTO departments (dept_no, dept_name) VALUES
    ('0001', 'Java Developer'),
    ('0002', 'Business Analyst')
    ;

INSERT INTO dept_emp (emp_no, dept_no, from_date, to_date) VALUES
    (1, '0001', '2021-10-15', '2022-10-14')
    ;

INSERT INTO dept_manager (emp_no, dept_no, from_date, to_date) VALUES
    (1, '0001', '2021-10-15', '2022-10-14')
    ;

INSERT INTO titles (emp_no, title, from_date, to_date) VALUES
    (1, 'Java Developer', '2021-10-15', '2022-10-14')
    ;

INSERT INTO salaries (emp_no, salary, from_date, to_date) VALUES
    (1, 10000000, '2021-10-15', '2022-10-14')
    ;