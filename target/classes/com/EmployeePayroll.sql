-- UC1 (Create Database)

CREATE DATABASE payroll_service;
SHOW DATABASES;
USE payroll_service;

-- UC2 (Creating table)

CREATE TABLE employee_payroll (
   id INT AUTO_INCREMENT PRIMARY KEY,
   emp_name VARCHAR(50),
   salary DOUBLE,
   start_date DATE
);

-- UC3 (Inserting in table)
INSERT INTO employee_payroll(id,emp_name,salary,start_date)
VALUES (1,'Himanshu',60000.00,'2023-01-08'),
	   (2,'Omkar','50000.00','2023-01-20'),
       (3,'Shrikant','45000.00','2023-02-01');

-- UC4 (Displaying Table)
SELECT * FROM employee_payroll;

-- UC5(Retrieving salary data)
SELECT emp_name,id,salary FROM employee_payroll WHERE emp_name = 'Shrikant';

SELECT emp_name,id,salary FROM employee_payroll WHERE start_date BETWEEN CAST('2023-01-01' AS DATE) AND DATE(NOW());

-- UC6(Adding Gender Column)
ALTER TABLE employee_payroll
ADD COLUMN gender VARCHAR(1);

UPDATE employee_payroll SET gender = 'M'
WHERE emp_name = 'Himanshu' OR emp_name = 'Shrikant' OR emp_name = 'Omkar';

INSERT INTO employee_payroll(id,emp_name,salary,start_date,gender)
VALUES (4, 'Anita',75000.00,'2023-04-20','F');
INSERT INTO employee_payroll(id,emp_name,salary,start_date,gender)
VALUES (5, 'Richa',55000.00,'2023-05-02','F');

SELECT * FROM employee_payroll;

-- UC7 (SUM,AVERAGE,MIN,MAX)

-- SUM
SELECT gender,SUM(salary) FROM employee_payroll
GROUP BY gender;

-- AVERAGE
SELECT gender,AVG(salary) FROM employee_payroll
GROUP BY gender;

-- MIN
SELECT gender,MIN(salary) FROM employee_payroll
GROUP BY gender;

-- MAX
SELECT gender,MAX(salary) FROM employee_payroll
GROUP BY gender;

-- COUNT
SELECT gender,COUNT(id) FROM employee_payroll
GROUP BY gender;


-- UC8 (Store employee phone number, employee address(Default value), employee department)

ALTER TABLE employee_payroll
ADD COLUMN phone_number VARCHAR(15),
ADD COLUMN address VARCHAR(50) DEFAULT '123 MG ROAD',
ADD COLUMN department VARCHAR(15);

UPDATE employee_payroll SET phone_number = '9876543211', department = 'Technical' WHERE id = 1;
UPDATE employee_payroll SET phone_number = '7894561230', department = 'Development' WHERE id = 2;
UPDATE employee_payroll SET phone_number = '8974563210', department = 'Marketing' WHERE id = 3;
UPDATE employee_payroll SET phone_number = '9638527416', department = 'Sales' WHERE id = 4;
UPDATE employee_payroll SET phone_number = '6549781230', department = 'HR' WHERE id = 5;

-- UC9(Basic pay, Deductions, Taxable pay, Tax, Net pay)
ALTER TABLE employee_payroll
ADD COLUMN basic_pay DOUBLE NOT NULL,
ADD COLUMN deductions DOUBLE NOT NULL,
ADD COLUMN taxable_pay DOUBLE NOT NULL,
ADD COLUMN income_tax DOUBLE NOT NULL,
ADD COLUMN net_pay DOUBLE NOT NULL;


UPDATE employee_payroll SET basic_pay =60000.00, deductions = 1000.00, taxable_pay = 59000.00, income_tax=1000.00, net_pay = 58000.00 WHERE id=1;
UPDATE employee_payroll SET basic_pay =50000.00, deductions = 900.00, taxable_pay = 49100.00, income_tax=800.00, net_pay = 48300.00 WHERE id=2;
UPDATE employee_payroll SET basic_pay =45000.00, deductions = 800.00, taxable_pay = 44200.00, income_tax=700.00, net_pay = 43500.00 WHERE id=3;
UPDATE employee_payroll SET basic_pay =75000.00, deductions = 1500.00, taxable_pay = 73500.00, income_tax=1500.00, net_pay = 72000.00 WHERE id=4;
UPDATE employee_payroll SET basic_pay =55000.00, deductions = 1200.00, taxable_pay = 53800.00, income_tax=850.00, net_pay = 52950.00 WHERE id=5;


-- UC10 (Adding Terissa)
INSERT INTO employee_payroll(emp_name,salary,start_date,gender,phone_number,department,basic_pay,deductions,taxable_pay,income_tax,net_pay)
VALUES ('Terissa','40000.00','2023-03-25','F','6549873217','Sales','40000.00','600.00','39400.00','400.00','39000.00'),
('Terissa','40000.00','2023-03-25','F','6549873217','Marketing','40000.00','600.00','39400.00','400.00','39000.00');

-- HERE came redundancy as same employee has 2 id i.e Terissa
-- Removing redundancy by normalizing database

ALTER TABLE employee_payroll
DROP COLUMN department;

-- Removing Terissa
DELETE FROM employee_payroll 
WHERE emp_name = 'Terissa';

-- Inserting Terissa again
INSERT INTO employee_payroll(emp_name,salary,start_date,gender,phone_number,basic_pay,deductions,taxable_pay,income_tax,net_pay)
VALUES ('Terissa','40000.00','2023-03-25','F','6549873217','40000.00','600.00','39400.00','400.00','39000.00');

-- UC11 (CREATING Tables)

CREATE TABLE department(
   dept_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   dept_name VARCHAR(50)
);

INSERT INTO department(dept_name)
VALUES ('Technical'),
('Development'),
('Marketing'),
('Sales'),
('HR');

SELECT * FROM department;

-- MAPPING EMP_ID AND DEP_ID
CREATE TABLE employee_department(
  id INT NOT NULL,
  dept_id INT NOT NULL,
  FOREIGN KEY(id) REFERENCES employee_payroll(id),
  FOREIGN KEY(dept_id) REFERENCES department(dept_id)
);

INSERT INTO employee_department(id,dept_id)
VALUES (1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(8,3),
(8,4);

SELECT * FROM employee_department;
 
 -- Displaying data by joining tables
SELECT 
    ep.id,
    ep.emp_name,
    ep.salary,
    ep.start_date,
    ep.gender,
    ep.phone_number,
    ep.address,
    ep.basic_pay,
    ep.deductions,
    ep.taxable_pay,
    ep.income_tax,
    ep.net_pay,
    dep.dept_name
FROM 
    employee_payroll ep
INNER JOIN 
    employee_department ed ON ep.id = ed.id
INNER JOIN 
    department dep ON ed.dept_id = dep.dept_id;
    

-- UC12 (Ensure all retrieve queries done especially in UC 4, UC 5 and UC 7 are working with new table structure)

-- UC4 (Displaying Table)
SELECT * FROM employee_payroll;

-- UC5(Retrieving salary data)
SELECT emp_name,id,salary FROM employee_payroll WHERE emp_name = 'Shrikant';

SELECT emp_name,id,salary FROM employee_payroll WHERE start_date BETWEEN CAST('2023-01-01' AS DATE) AND DATE(NOW());

-- UC7 (SUM,AVERAGE,MIN,MAX)

-- SUM
SELECT gender,SUM(salary) FROM employee_payroll
GROUP BY gender;

-- AVERAGE
SELECT gender,AVG(salary) FROM employee_payroll
GROUP BY gender;

-- MIN
SELECT gender,MIN(salary) FROM employee_payroll
GROUP BY gender;

-- MAX
SELECT gender,MAX(salary) FROM employee_payroll
GROUP BY gender;

-- COUNT
SELECT gender,COUNT(id) FROM employee_payroll
GROUP BY gender;





