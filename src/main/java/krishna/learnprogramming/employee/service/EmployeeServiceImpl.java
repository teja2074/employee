package krishna.learnprogramming.employee.service;

import krishna.learnprogramming.employee.exception.EmployeeNotFoundException;
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
    public Employee getEmployee(Long id) {

        Optional<Employee> optionalEmp = employeeRepository.findById(id);
        if(optionalEmp.isPresent()){
            return  optionalEmp.get();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        Optional<Employee> employee1 = employeeRepository.findById(employee.getId());

       if(employee1.isPresent()) {
           Employee employee2 = employee1.get();
           employee2.setEmployeeId(employee.getEmployeeId());
           employee2.setName(employee.getName());
           employeeRepository.save(employee2);
           return employee2;
       }else{
           System.out.println("Please enter valid details"); //log.error();
           throw new EmployeeNotFoundException("Employee not found for given employee id"+ employee.getEmployeeId());

       }

    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public long getEmployeeCount() {
        return employeeRepository.count();
    }
}
