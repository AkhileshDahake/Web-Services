package com.empsys.dao;

import java.util.ArrayList;

import com.empsys.model.*;

public interface EmployeeDAO {
	
	void cleanUp() throws Exception;
	
	boolean addEmployee(Employee e) throws Exception;
	
	boolean deleteEmployee(Employee e) throws Exception;
	
	ArrayList<Employee> listAllEmployees() throws Exception;
	
	ArrayList<Employee> listEngineers() throws Exception;
	
	ArrayList<Employee> listManagers() throws Exception;
	
	Employee getEmployee(int empId) throws Exception;
	
	Employee getEmployee(String name) throws Exception;
}
