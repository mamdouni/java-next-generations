package org.example.java.tutorial.java15.localrecords;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    
    public static void main(String[] args) {

        Stream<Employee> employees = getEmployees();
        System.out.println(lowestPaidEmployeeFirstImplementation(employees));

        employees = getEmployees();
        System.out.println(lowestPaidEmployeeRecordImplementation(employees));
    }

    private static Stream<Employee> getEmployees() {
        return Stream.of(
                new Employee("1", "John", 1),
                new Employee("2", "Doe", 2),
                new Employee("3", "Smith", 3)
        );
    }

    private static Employee lowestPaidEmployeeFirstImplementation(Stream<Employee> employees) {

        Map<Integer, List<Employee>> employeesPerSalary = employees.collect(Collectors.groupingBy(Main::findSalary));
        Integer min = Collections.min(employeesPerSalary.keySet());
        return employeesPerSalary.get(min).get(0);
        // as we need to group employees by salary, we can use the groupingBy collector or thinking for a tuple or pair classes
        // when we need to return a pair of values, we can use a local record
        // by the way, we can use a comparator on the employees stream and make this implementation more readable
    }

    private static Employee lowestPaidEmployeeRecordImplementation(Stream<Employee> employees) {

        record Salary(Employee employee, Integer amount) {}

        return employees
                .map(employee -> new Salary(employee, findSalary(employee)))
                .sorted(Comparator.comparing(Salary::amount))
                .map(Salary::employee)
                .findFirst()
                .orElseThrow();
        // this prevents you from creating a new class or using tuples or pairs to hold the salary and the employee
    }

    private static int findSalary(Employee employee) {
        return switch (employee.level()) {
            case 1 -> 1000;
            case 2 -> 3000;
            default -> 2000;
        };
    }
}