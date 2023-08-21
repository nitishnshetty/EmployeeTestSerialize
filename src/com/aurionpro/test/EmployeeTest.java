package com.aurionpro.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.aurionpro.model.Employee;

public class EmployeeTest {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(7698, "BLAKE", "MANAGER", 7839, "1-MAY-81", 2850, null, 30));
		employees.add(new Employee(7369, "SMITH", "CLERK", 7902, "17-DEC-80", 800, null, 20));
		employees.add(new Employee(7788, "SCOTT", "ANALYST", 7566, "09-DEC-82", 3000, null, 20));
		employees.add(new Employee(7934, "MILLER", "CLERK", 7782, "23-JAN-82", 1300, null, 10));
		employees.add(new Employee(7654, "MARTIN", "SALESMAN", 7698, "28-SEP-81", 1250, 1400, 30));
		employees.add(new Employee(7499, "ALLEN", "SALESMAN", 7698, "20-FEB-81", 1600, 300, 30));
		employees.add(new Employee(7782, "CLARK", "MANAGER", 7839, "9-JUN-81", 2450, null, 10));
		employees.add(new Employee(7844, "TURNER", "SALESMAN", 7698, "8-SEP-81", 1500, 0, 30));
		employees.add(new Employee(7900, "JAMES", "CLERK", 7698, "3-DEC-81", 950, null, 30));
		employees.add(new Employee(7521, "WARD", "SALESMAN", 7698, "22-FEB-81", 1250, 500, 30));
		employees.add(new Employee(7902, "FORD", "ANALYST", 7566, "3-DEC-81", 3000, null, 20));
		employees.add(new Employee(7876, "ADAMS", "CLERK", 7788, "12-JAN-83", 1100, null, 20));
		employees.add(new Employee(7566, "JONES", "MANAGER", 7839, "2-APR-81", 2975, null, 20));
		employees.add(new Employee(7698, "BLAKE", "MANAGER", 7839, "1-MAY-81", 2850, null, 30));
		employees.add(new Employee(7369, "SMITH", "CLERK", 7902, "17-DEC-80", 800, null, 20));
		employees.add(new Employee(7788, "SCOTT", "ANALYST", 7566, "09-DEC-82", 3000, null, 20));
		employees.add(new Employee(7654, "MARTIN", "SALESMAN", 7698, "28-SEP-81", 1250, 1400, 30));
		employees.add(new Employee(7499, "ALLEN", "SALESMAN", 7698, "20-FEB-81", 1600, 300, 30));
		employees.add(new Employee(7839, "KING-WEB", "PRESIDENT", 0, "17-NOV-81", 15000, null, 10));
		employees.add(new Employee(7782, "CLARK", "MANAGER", 7839, "9-JUN-81", 2450, null, 10));
		employees.add(new Employee(7844, "TURNER", "SALESMAN", 7698, "8-SEP-81", 1500, 0, 30));
		employees.add(new Employee(7900, "JAMES", "CLERK", 7698, "3-DEC-81", 950, null, 30));

//      for (Employee employee : employees) {
//          System.out.println(employee);
//      }

		Scanner scanner = new Scanner(System.in);


		System.out.println("=================================Employees in a Dept=============================================");
		System.out.print("Enter the department number: ");
		int departmentNumber = scanner.nextInt();
		System.out.println("Employees in Department " + departmentNumber + ":");

		for (Employee employee : employees) {
			if (employee.getDepartmentId() == departmentNumber) {
				System.out.println(employee);
			}
		}

		System.out.println("=================================Employees in a Dept count=============================================");
		System.out.print("Enter the department number: ");
		System.out.println("Employees in Department " + departmentNumber + ":");
		int count = 0;
		for (Employee employee : employees) {
			if (employee.getDepartmentId() == departmentNumber) {
				count++;
				System.out.println(count);
			}
		}
		System.out
				.println("=================================ManagerFinder=============================================");
		System.out.print("Enter an employee's ID: ");
		int employeeId = scanner.nextInt();
		Employee manager = findManager(employees, employeeId);
		if (manager != null) {
			System.out.println("Immediate Manager: " + manager.getEmpName());
		} else {
			System.out.println("Employee not found or has no manager.");
		}

		System.out.println(
				"=================================HighestPaidEmployeesInDept=============================================");
		List<Employee> highbalanceAccount = new ArrayList<>();

		Map<Integer, Employee> highestPaidEmployees = new HashMap<>();

		for (Employee x : employees) {
			int departmentId = x.getDepartmentId();
			if (!highestPaidEmployees.containsKey(departmentId)	|| x.getSalary() > highestPaidEmployees.get(departmentId).getSalary()) {
				highestPaidEmployees.put(departmentId, x);
			}
		}
		for (Map.Entry<Integer, Employee> entry : highestPaidEmployees.entrySet()) {
			int departmentId = entry.getKey();
			Employee highestPaidEmployee = entry.getValue();
			System.out.println("Department " + departmentId + ": " + highestPaidEmployee.getEmpName() + " - Salary: $"
					+ highestPaidEmployee.getSalary());
		}

		System.out.println(
				"=================================HighestPaidEmployeesInRole=============================================");
		List<Employee> highBalanceAccountRole = new ArrayList<>();
		Map<String, Employee> highestPaidInEachJob = findHighestPaidInEachJob(employees);

		for (Map.Entry<String, Employee> entry : highestPaidInEachJob.entrySet()) {
			String role = entry.getKey();
			Employee employee = entry.getValue();
			System.out.println("Role: " + role + ", Highest Paid Employee: " + employee);
		}

		System.out.println("=================================joiningEmployees=============================================");
		List<Employee> joiningEmployees = new ArrayList<>();

		// Add your employees here as you've already done.

		List<Employee> employeesJoinedBetween = findEmployeesJoinedBetween(employees, "01-NOV-82", "31-DEC-82");

		for (Employee employee : employeesJoinedBetween) {
			System.out.println(employee);
		}

		System.out.println(
				"=================================highestCommissionSalesman=============================================");
		Optional<Employee> highestCommissionSalesman = employees.stream()
				.filter(employee -> "SALESMAN".equals(employee.getJob()))
				.max(Comparator.comparingDouble(employee -> employee.getCommission()));

		if (highestCommissionSalesman.isPresent()) {
			Employee salesman = highestCommissionSalesman.get();
			System.out.println("Salesman with the highest commission:");
			System.out.println(salesman);
		} else {
			System.out.println("No salesman found.");
		}

		System.out.println("=================================totalSalary=============================================");
		double totalSalary = employees.stream()
				.mapToDouble(Employee::getSalary)
				.sum();

		System.out.println("Total salary of all employees: " + totalSalary);
	}

	private static Employee findManager(List<Employee> employees, int employeeId) {
		for (Employee employee : employees) {
			if (employee.getEmpId() == employeeId) {
				int managerId = employee.getManagerId();
				for (Employee managerFinder : employees) {
					if (managerFinder.getEmpId() == managerId) {
						return managerFinder;
					}
				}
				break;
			}
		}
		return null;
	}

	public static Map<String, Employee> findHighestPaidInEachJob(List<Employee> employees) {
		Map<String, Employee> highestPaidInEachJob = new HashMap<>();

		for (Employee employee : employees) {
			String job = employee.getJob();
			double salary = employee.getSalary();

			if (!highestPaidInEachJob.containsKey(job) || salary > highestPaidInEachJob.get(job).getSalary()) {
				highestPaidInEachJob.put(job, employee);
			}
		}

		return highestPaidInEachJob;
	}

	public static List<Employee> findEmployeesJoinedBetween(List<Employee> employees, String startDateStr,
			String endDateStr) {
		List<Employee> result = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

		try {
			Date startDate = dateFormat.parse(startDateStr);
			Date endDate = dateFormat.parse(endDateStr);

			for (Employee employee : employees) {
				Date joinDate = dateFormat.parse(employee.getHireDate());
				if (joinDate.after(startDate) && joinDate.before(endDate)) {
					result.add(employee);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

}
