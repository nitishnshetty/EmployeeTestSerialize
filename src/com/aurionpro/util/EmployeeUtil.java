package com.aurionpro.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Employee2;

public class EmployeeUtil {
	List<Employee2> employeeList;
	private static boolean initialload = false;

	public EmployeeUtil() {
		this.employeeList = new ArrayList<>();
		loadEmployee();
	}

	public List<Employee2> getEmployeeList() {
		return employeeList;
	}

	@SuppressWarnings("unchecked")
	private void loadEmployee() {
		if (checkIfEmpty() && initialload) {
			List<Employee2> file = new EmployeeReader().readEmployeeFromFile("C:\\Users\\Nitish.Shetty\\Desktop\\empList.txt");
			initialload = true;
		} else if (!checkIfEmpty()) {
			try {
				FileInputStream file = new FileInputStream("C:\\Users\\Nitish.Shetty\\Desktop\\empSerialize.txt");
				ObjectInputStream in = new ObjectInputStream(file);

				this.employeeList = (List<Employee2>) in.readObject();
				in.close();
				file.close();

				System.out.println("Object de-serialized successfully");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addEmployee(Employee2 employee) {
		employeeList.add(employee);
		saveEmployeeList();
	}
	
	public void removeEmployee(Employee2 employee) {
		if(employeeList.contains(employee)) {
			employeeList.remove(employee);
			saveEmployeeList();
		}
		
	}

	private void saveEmployeeList() {
		try {
			FileOutputStream fileStream = new FileOutputStream("C:\\Users\\Nitish.Shetty\\Desktop\\empSerialize.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileStream);

			out.writeObject(employeeList);

			out.close();
			fileStream.close();
			System.out.println("Object Serialized");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	private boolean checkIfEmpty() {
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\Nitish.Shetty\\Desktop\\empSerialize.txt");
			if (file.read() != -1) {
				return false;
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

//	public static void addEmployee(List<Employee2> employees, Employee2 employee) {
//		employees.add(employee);
//		System.out.println("Employee added: " + employee.getName());
//	}
}
