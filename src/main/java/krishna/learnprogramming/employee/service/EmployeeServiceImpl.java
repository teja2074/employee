package krishna.learnprogramming.employee.service;

import krishna.learnprogramming.employee.model.Employee;
import krishna.learnprogramming.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Integer id) {
        Optional<Employee> optionalEmp = employeeRepository.findById(id);
        return optionalEmp.get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> employee1 = employeeRepository.findById(employee.getEmployeeId());
       Employee employee2 = employee1.get();
       employee2.setName(employee.getName());

        employeeRepository.save(employee2);

        return employee2;
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);

    }
}
