package com.aurionpro.model;

import java.util.Date;

public class Employee {
	private int empId;
    private String empName;
    private String job;
    private int managerId;
    private String hireDate;
    private int salary;
    private Integer commission;
    private int departmentId;
    
    public Employee(int empId, String empName, String job, int managerId, String hireDate, int salary, Integer commission, int departmentId) {
        this.empId = empId;
        this.empName = empName;
        this.job = job;
        this.managerId = managerId;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commission = commission;
        this.departmentId = departmentId;
    }

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Integer getCommission() {
		return commission;
	}

	public void setCommission(Integer commission) {
		this.commission = commission;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	@Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", job='" + job + '\'' +
                ", managerId=" + managerId +
                ", hireDate='" + hireDate + '\'' +
                ", salary=" + salary +
                ", commission=" + commission +
                ", departmentId=" + departmentId +
                '}';
    }
}






    
	

