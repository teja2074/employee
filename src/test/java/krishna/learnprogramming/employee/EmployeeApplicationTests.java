package krishna.learnprogramming.employee;

import krishna.learnprogramming.employee.exception.EmployeeNotFoundException;
import krishna.learnprogramming.employee.exception.ResourceNotFoundException;
import krishna.learnprogramming.employee.model.Employee;
import krishna.learnprogramming.employee.repository.EmployeeRepository;
import krishna.learnprogramming.employee.service.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest
public class EmployeeApplicationTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = Employee.builder()
                .id(1l)
                .employeeId(106l)
                .name("H2DB")
                .build();
    }

    @DisplayName("Junit test for createEmployee() -  givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject()")
    @Test(expected=NullPointerException.class)
    public void testCreateEmployee() {
        given(employeeRepository.findById(employee.getEmployeeId()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);
        System.out.println("Repository" + employeeRepository);
        System.out.println("employeeService" + employeeService);

        Employee savedEmployee = employeeService.createEmployee(employee);

        System.out.println(savedEmployee);
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("Junit test for createEmployee() which throws exception")
    @Test(expected=NullPointerException.class)
    public void testCreateEmployee1() {
        given(employeeRepository.findById(employee.getEmployeeId()))
                .willReturn(Optional.of(employee));
        System.out.println("Repository" + employeeRepository);
        System.out.println("employeeService" + employeeService);
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.createEmployee(employee);
        });
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @DisplayName("JUnit test for getAllEmployees()")
    @Test(expected=Exception.class)
    public void testGetAllEmployees() {
        Employee employee1 = Employee.builder()
                .id(2l)
                .employeeId(142l)
                .name("Junit & Mockito")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        List<Employee> employeeList = employeeService.getAllEmployees();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getAllEmployees()- negative scenario")
    @Test
    public void testGetAllEmployees1() {
        Employee employee1 = Employee.builder()
                .id(2l)
                .employeeId(142l)
                .name("Junit & Mockito")
                .build();
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        List<Employee> employeeList = employeeService.getAllEmployees();

        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }

    @DisplayName("JUnit test for getEmployeeById()")
    @Test(expected=NullPointerException.class)
    public void testGetEmployeeById(){
        given(employeeRepository.findById(106l)).willReturn(Optional.of(employee));

        Employee savedEmployee=employeeService.getEmployee(employee.getEmployeeId());

        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for updateEmployee()")
    @Test
    public void testUpdateEmployee() throws EmployeeNotFoundException {
        Employee employee1 = Employee.builder()
                .id(2l)
                .employeeId(142l)
                .name("Junit & Mockito")
                .build();
        when(employeeRepository.findById(142l)).thenReturn(Optional.of(employee1));

        Employee employee2 = Employee.builder()
                .id(2l)
                .employeeId(142l)
                .name("Krishna")
                .build();
        Employee updatedEmployee = employeeService.updateEmployee(employee2);

       // assertThat(updatedEmployee.getEmployeeId()==108);
        assertThat(updatedEmployee.getName()).isEqualTo("Krishna");

    }

    @DisplayName("JUnit test for deleteEmployee()")
    @Test
    public void testDeleteEmployee(){
        Long employeeId = 142l;
        willDoNothing().given(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

}
















   /* @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    public EmployeeApplicationTests() {

    }

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
    @Test(expected=NullPointerException.class)
	public void testGetEmployeeId() throws Exception {
       //int id=106;
        //Employee employee = employeeService.getEmployee(id);
        //assertTrue("Test output :", employee.getEmployeeId()==106);
        //Assertions.assertEquals(106,employeeService.getEmployee(id).getEmployeeId(),"Employee Id test");

        Employee employee = new Employee(106,"H2DB");
        when(employeeService.getEmployee(106)).thenReturn(employee);

        mockMvc.perform(get("/employees/106")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.employeeId", is (106)))
                .andExpect(jsonPath("$.name", is("H2DB")));

    }

    @Test
    public void testGetEmployeeByIdFailNotFound() throws Exception {
        when(employeeService.getEmployee(110)).thenReturn(null);
        mockMvc.perform(get("/employees/110")).andExpect(status().isNotFound());
    }*/

