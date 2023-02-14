package krishna.learnprogramming.employee.metrics;

import io.prometheus.client.Counter;

public class EmployeeMetrics {

    private static final Counter employeeCounter = Counter.build()
            .name("employee_count")
            .help("Total number of employees in the database")
            .register();

    public static void incrementEmployeeCount() {
        employeeCounter.inc();
    }

}
