package krishna.learnprogramming.employee.service;

import krishna.learnprogramming.employee.exception.EmployeeNotFoundException;
import krishna.learnprogramming.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    Employee getEmployee(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Employee employee) throws EmployeeNotFoundException;
    void deleteEmployee(Long id);

    long getEmployeeCount();
}
