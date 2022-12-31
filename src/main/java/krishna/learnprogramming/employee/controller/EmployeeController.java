package krishna.learnprogramming.employee.controller;

import krishna.learnprogramming.employee.model.Employee;
import krishna.learnprogramming.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    public static Map<Integer, Employee> empMap = new HashMap<>();

    @Autowired
    EmployeeService employeeService;
    static{
        Employee emp = new Employee(101, "Java");
        empMap.put(emp.getEmployeeId(), emp);
    }
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        /*empMap.put(employee.getEmployeeId(), employee);
        return empMap.get(employee.getEmployeeId());*/
       return employeeService.createEmployee(employee);

    }
    @GetMapping(path = "/{employeeId}")
    public Employee getEmployee(@PathVariable Integer employeeId){
       // return empMap.get(employeeId);
        return employeeService.getEmployee(employeeId);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        /*Employee employee1 = empMap.get(employee.getEmployeeId());
        employee1.setName(employee.getName());
        empMap.put(employee.getEmployeeId(),employee);
        return employee.getEmployeeId();*/
        return employeeService.updateEmployee(employee);
    }
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId){
       // empMap.remove(employeeId);
        employeeService.deleteEmployee(employeeId);
        return "Employee deleted Successfully!";
    }


      /*Employee emp1 = empMap.get(employeeId);
       emp1.setName(employee.getName());
       empMap.put(employee.getEmployeeId(), employee);
    @PostMapping
    public List<Transaction> getTransactionDetails(Date)

    @GetMapping(path = "/{accountNumber}")
    public Account getEmployee(@PathVariable Long accountNumber){
       return accountService.getAccount(accountNumber);
       // return empMap.get(employeeId);

    }*/



}
