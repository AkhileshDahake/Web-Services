package com.empsys.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int empId;
	String name;
	double sal;
	String dept;
	String designation;
	int managerId;
	String managerName;
	String emailId;
	String mobileNo;
	String address;
	
	
	
	public Employee() {
		super();
	}
	
	
	//Called by deleteEmployee()
	public Employee(int empId) {
		super();
		this.empId = empId;
	}



	public Employee(int empId, String name, double sal, String dept,
			String designation, String emailId, String mobileNo,
			String address) {
		super();
		this.empId = empId;
		this.name = name;
		this.sal = sal;
		this.dept = dept;
		this.designation = designation;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.address = address;
	}
	

	//Called by addEmployee()
	public Employee(String name, double sal, String dept,
			String designation, int managerId, String emailId, String mobileNo, String address) {
		super();
		this.name = name;
		this.sal = sal;
		this.dept = dept;
		this.designation = designation;
		this.managerId = managerId;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.address = address;
	}
	
	
	
	public Employee(int empId, String name, double sal, String dept,
			String designation, int managerId, String emailId, String mobileNo, String address) {
		super();
		this.empId = empId;
		this.name = name;
		this.sal = sal;
		this.dept = dept;
		this.designation = designation;
		this.managerId = managerId;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.address = address;
	}


	
	public Employee(int empId, String name, double sal, String dept,
			String designation, int managerId, String emailId, String mobileNo, String address, String managerName) {
		super();
		this.empId = empId;
		this.name = name;
		this.sal = sal;
		this.dept = dept;
		this.designation = designation;
		this.managerId = managerId;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.address = address;
		this.managerName = managerName;
	}


	public int getEmpId() {
		return empId;
	}


    @XmlElement
	public void setEmpId(int empId) {
		this.empId = empId;
	}



	public String getName() {
		return name;
	}


    @XmlElement
	public void setName(String name) {
		this.name = name;
	}



	public double getSal() {
		return sal;
	}


    @XmlElement
	public void setSal(double sal) {
		this.sal = sal;
	}



	public String getDept() {
		return dept;
	}


    @XmlElement
	public void setDept(String dept) {
		this.dept = dept;
	}



	public String getDesignation() {
		return designation;
	}


    @XmlElement
	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public int getManagerId() {
		return managerId;
	}



	public String getManagerName() {
		return managerName;
	}


    @XmlElement
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


    @XmlElement
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}



	public String getEmailId() {
		return emailId;
	}


    @XmlElement
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	public String getMobileNo() {
		return mobileNo;
	}


    @XmlElement
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public String getAddress() {
		return address;
	}


    @XmlElement
	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		} else if (!(obj instanceof Employee)) {
			return false;
		} else {
			Employee emp = (Employee) obj;
			if (this.empId == emp.getEmpId()) {
				return true;
			}
		}
		return false;
	}



	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", sal=" + sal
				+ ", dept=" + dept + ", designation=" + designation
				+ ", managerId=" + managerId + ", managerName=" + managerName
				+ ", emailId=" + emailId + ", mobileNo=" + mobileNo
				+ ", address=" + address + "]";
	}
	
	
}
