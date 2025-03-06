package com.kh.mvc.model.dto;

import java.sql.Date;

public class EmployeeDTO {
	
	private String EmpId;
	private String EmpName;
	private String EmpNo;
	private String Email;
	private String Phone;
	private String DeptCode;
	private String JobCode;
	private String SalLevel;
	private int Salary;
	private int Bonus;
	private String ManagerId;
	private Date HireDate;
	private Date EntDate;
	private char EntYn;
	
	public EmployeeDTO() {
		super();
	}
	
	public EmployeeDTO(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, int salary, int bonus, String managerId, Date hireDate, Date entDate,
			char entYn) {
		super();
		EmpId = empId;
		EmpName = empName;
		EmpNo = empNo;
		Email = email;
		Phone = phone;
		DeptCode = deptCode;
		JobCode = jobCode;
		SalLevel = salLevel;
		Salary = salary;
		Bonus = bonus;
		ManagerId = managerId;
		HireDate = hireDate;
		EntDate = entDate;
		EntYn = entYn;
	}
	
	public String getEmpId() {
		return EmpId;
	}
	public void setEmpId(String empId) {
		EmpId = empId;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public String getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(String empNo) {
		EmpNo = empNo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getDeptCode() {
		return DeptCode;
	}
	public void setDeptCode(String deptCode) {
		DeptCode = deptCode;
	}
	public String getJobCode() {
		return JobCode;
	}
	public void setJobCode(String jobCode) {
		JobCode = jobCode;
	}
	public String getSalLevel() {
		return SalLevel;
	}
	public void setSalLevel(String salLevel) {
		SalLevel = salLevel;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public int getBonus() {
		return Bonus;
	}
	public void setBonus(int bonus) {
		Bonus = bonus;
	}
	public String getManagerId() {
		return ManagerId;
	}
	public void setManagerId(String managerId) {
		ManagerId = managerId;
	}
	public Date getHireDate() {
		return HireDate;
	}
	public void setHireDate(Date hireDate) {
		HireDate = hireDate;
	}
	public Date getEntDate() {
		return EntDate;
	}
	public void setEntDate(Date entDate) {
		EntDate = entDate;
	}
	public char getEntYn() {
		return EntYn;
	}
	public void setEntYn(char entYn) {
		EntYn = entYn;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [EmpId=" + EmpId + ", EmpName=" + EmpName + ", EmpNo=" + EmpNo + ", Email=" + Email
				+ ", Phone=" + Phone + ", DeptCode=" + DeptCode + ", JobCode=" + JobCode + ", SalLevel=" + SalLevel
				+ ", Salary=" + Salary + ", Bonus=" + Bonus + ", ManagerId=" + ManagerId + ", HireDate=" + HireDate
				+ ", EntDate=" + EntDate + ", EntYn=" + EntYn + "]";
	}
	
	
}