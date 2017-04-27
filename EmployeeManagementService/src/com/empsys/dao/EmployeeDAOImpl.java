package com.empsys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import com.empsys.model.Employee;


public class EmployeeDAOImpl implements EmployeeDAO {

	private Connection cn;
	private PreparedStatement selectQuery, selectEngineerQuery, selectManagerQuery, selectEmployeeByIdQuery, selectEmployeeByNameQuery; 
	private PreparedStatement deleteQuery, insertQuery;
	
	public EmployeeDAOImpl() throws Exception {
		super();
		
		// load Driver
		Class.forName("oracle.jdbc.OracleDriver");
		//System.out.println("[ DRIVER LOADED ]");
		// create Connection
		cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"sunbeam", "sunbeam");
		
		// prepare statements
		selectQuery = cn
				.prepareStatement("SELECT * from Employee");
		selectEmployeeByIdQuery = cn.prepareStatement("SELECT * from Employee WHERE empId=?");
		selectEmployeeByNameQuery = cn.prepareStatement("SELECT * from Employee WHERE upper(name)=upper(?)");
		selectEngineerQuery = cn.prepareStatement("SELECT e1.empId, e1.name, e1.sal, e1.dept, e1.designation, e1.managerId, e1.emailId, e1.mobileNo, e1.address, e2.name as MANAGER_NAME from Employee e1, Employee e2 WHERE upper(e1.designation)=upper('engineer') AND e1.managerId=e2.empId");
		selectManagerQuery = cn.prepareStatement("SELECT empId, name, sal, dept, designation, emailId, mobileNo, address from Employee WHERE upper(designation)=upper('manager')");
		deleteQuery = cn.prepareStatement("DELETE from Employee WHERE empId=?");
		insertQuery = cn.prepareStatement("INSERT into Employee(name, sal, dept, designation, managerId, emailId, mobileNo, address) values(?,?,?,?,?,?,?,?)");
		//System.out.println("dao inst created");
	}

	@Override
	public void cleanUp() throws Exception {
		
		// close Connection
				if (selectQuery != null)
					selectQuery.close();
				if (selectEmployeeByIdQuery != null)
					selectEmployeeByIdQuery.close();
				if (selectEmployeeByNameQuery != null)
					selectEmployeeByNameQuery.close();
				if (selectEngineerQuery != null)
					selectEngineerQuery.close();
				if (selectManagerQuery != null)
					selectManagerQuery.close();
				if (deleteQuery != null)
					deleteQuery.close();
				if (insertQuery != null)
					insertQuery.close();
				if (cn != null)
					cn.close();
				//System.out.println("dao cleaned up");

	}

	@Override
	public boolean addEmployee(Employee e) throws Exception {
		
		// set Params
		insertQuery.setString(1, e.getName());
		insertQuery.setDouble(2, e.getSal());
		insertQuery.setString(3, e.getDept());
		insertQuery.setString(4, e.getDesignation());
		insertQuery.setInt(5, e.getManagerId());
		if (e.getManagerId() == 0)
			insertQuery.setNull(5, Types.INTEGER);
		insertQuery.setString(6, e.getEmailId());
		insertQuery.setString(7, e.getMobileNo());
		insertQuery.setString(8, e.getAddress());
		
		// add Employee
		int res = insertQuery.executeUpdate();
		
		if (res > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteEmployee(Employee e) throws Exception {
		
		// set Params
		deleteQuery.setInt(1, e.getEmpId());
		
		// delete Employee
		int res = deleteQuery.executeUpdate();
		
		if(res > 0)
			return true;
		return false;
	}

	@Override
	public ArrayList<Employee> listAllEmployees() throws Exception {
		
		ArrayList<Employee> empList = new ArrayList<Employee>();
		
		// Get List of All Employee
		try (ResultSet rst = selectQuery.executeQuery()) {
			while (rst.next()) {
				empList.add(new Employee(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getString(4), rst.getString(5), rst.getString(7), rst.getString(8), rst.getString(9)));
			}
		}
		return empList;
	}

	@Override
	public ArrayList<Employee> listEngineers() throws Exception {

		ArrayList<Employee> engList = new ArrayList<Employee>();
		
		// Get List of All Engineers
		try (ResultSet rst = selectEngineerQuery.executeQuery()) {
			while (rst.next()) {
				engList.add(new Employee(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getString(4), rst.getString(5), rst.getInt(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10)));
			}
		}
		return engList;
	}

	@Override
	public ArrayList<Employee> listManagers() throws Exception {

		ArrayList<Employee> mngList = new ArrayList<Employee>();
		
		// Get List of All Managers
		try (ResultSet rst = selectManagerQuery.executeQuery()) {
			while (rst.next()) {
				mngList.add(new Employee(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8)));
			}
		}
		return mngList;
	}

	@Override
	public Employee getEmployee(int empId) throws Exception {
		
		// set params
		selectEmployeeByIdQuery.setInt(1, empId);
		
		// Get An Employee
		try (ResultSet rst = selectEmployeeByIdQuery.executeQuery()) {
			if (rst.next()) {
				Employee e = new Employee(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getString(4), rst.getString(5), rst.getInt(6), rst.getString(7), rst.getString(8), rst.getString(9));
				
				return e;
			}
		}
		return null;
	}

	@Override
	public Employee getEmployee(String name) throws Exception {
		
		// set params
		selectEmployeeByNameQuery.setString(1, name);
		
		// Get An Employee
		try (ResultSet rst = selectEmployeeByNameQuery.executeQuery()) {
			if (rst.next()) {
				Employee e = new Employee(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getString(4), rst.getString(5), rst.getInt(6), rst.getString(7), rst.getString(8), rst.getString(9));
				return e;
			}
		}
		return null;
	}

}
