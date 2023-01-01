package krishna.learnprogramming.employee.service;

import krishna.learnprogramming.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    Employee getEmployee(Integer id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
}
