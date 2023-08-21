package com.aurionpro.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aurionpro.model.Employee2;
import com.aurionpro.util.EmployeeUtil;

public class Employee2Test {

    static int count = 0;

    public static void main(String[] args) {
        EmployeeUtil employeeUtil = new EmployeeUtil();
        List<Employee2> employees = employeeUtil.getEmployeeList();

        System.out.println(employeeUtil.getEmployeeList());

        List<Employee2> distinctEmployees = distinctEmployeeList(employees);
        printAllEmployees(distinctEmployees);

        System.out.println("---------------------------------");
        Map<Integer, Long> departmentEmployeeCounts = countEmployeesByDepartment(employees);
        printEmployeeCountsByDepartment(departmentEmployeeCounts);

        System.out.println("----------------------------------");
        Employee2 employee = findManager(employees, 7866);
        System.out.println("Immediate Manager: " + employee.getName());

        System.out.println("-------------------------------------------------");
        Double totalSalary = TotalSalary(employees);
        System.out.println("The total salary of all employees is: " + totalSalary);

        getHighestPaidInEachDepartment(employees);
    }	
	
    public static void getHighestPaidInEachDepartment(List<Employee2> employees) {
        Map<Integer, Employee2> highestPaidInEachDept = new HashMap<>();

        for (Employee2 employee : employees) {
            int departmentId = employee.getDepartment();
            if (!highestPaidInEachDept.containsKey(departmentId)
                    || employee.getSalary() > highestPaidInEachDept.get(departmentId).getSalary()) {
                highestPaidInEachDept.put(departmentId, employee);
            }
        }

        highestPaidInEachDept.forEach((deptId, emp) -> {
            System.out.println("Department " + deptId + ": Highest Paid Employee - " + emp.getName()
                    + " (Salary: " + emp.getSalary() + ")");
        });
    }
	
	public static List<Employee2> distinctEmployeeList(List<Employee2> employees) {
		return employees.stream()
				.distinct()
				.collect(Collectors.toList());
	}

	public static double TotalSalary(List<Employee2> employees) {
        return employees.stream()
                .mapToDouble(Employee2::getSalary)
                .sum();
    }
	
	public static Employee2 findManager(List<Employee2> employees, int employeeId) {
        Employee2 employeeList = employees.stream()
                .filter(e -> e.getId() == employeeId)
                .findFirst()
                .orElse(null);

        if (employeeList != null) {
            int managerId = employeeList.getManagerId();
            return employees.stream()
                    .filter(e -> e.getId() == managerId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
	
	public static void printEmployeeCountsByDepartment(Map<Integer, Long> departmentEmployeeCounts) {
        departmentEmployeeCounts.forEach((departmentId, count) ->
                System.out.println("Department " + departmentId + ": " + count + " employees"));
    }
	
	public static Map<Integer, Long> countEmployeesByDepartment(List<Employee2> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee2::getDepartment, Collectors.counting()));
    }
	
	private static void printAllEmployees(List<Employee2> emps) {
        emps.forEach(System.out::println);
    }
}
