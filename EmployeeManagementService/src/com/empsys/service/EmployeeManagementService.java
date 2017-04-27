package com.empsys.service;

import java.util.List;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empsys.dao.EmployeeDAO;
import com.empsys.dao.EmployeeDAOImpl;
import com.empsys.model.Employee;



@Path("/EmployeeService")
public class EmployeeManagementService {

	
	
	private EmployeeDAO empDAO;
	private static String SUCCESS_RESULT = "<result>success</result>";
	private static String FAILURE_RESULT = "<result>failure</result>";
	private static String SUPPORTED_OPERATIONS = "<operations>GET, PUT, DELETE</operations>";
	
	
	
	public EmployeeManagementService() {
		
		try {
			empDAO = new EmployeeDAOImpl();
		} catch (Exception e) {
			// Failed to create DAO
			e.printStackTrace();
		}
	}
	
	
	
	@GET
	@Path("/employee/{type}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> listEmployee(@PathParam("type") String type) {
		
		List<Employee> list = null;
		
		try {
			if(type.equals("engineers")) {
				list = empDAO.listEngineers();
			} else if (type.equals("managers")) {
				list = empDAO.listManagers();
			} else if (type.equals("allemployees")) {
				list = empDAO.listAllEmployees();
			}
		} catch(Exception e) {
			// Failed to load list
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	@GET
	@Path("/employee")
	@Produces(MediaType.APPLICATION_XML)
	public Employee getEmployee(@QueryParam("type") String type, @QueryParam("searchkey") String empSearchKey) {
		
		Employee emp = null;
		
		if (type.equals("empid")) {
			
			int empId = Integer.parseInt(empSearchKey);
			try {
				emp = empDAO.getEmployee(empId);
			} catch (Exception e) {
				// Employee ID not found
				e.printStackTrace();
			}
		} else if (type.equals("name")) {
			try {
				emp = empDAO.getEmployee(empSearchKey);
			} catch (Exception e) {
				// Employee name not found
				e.printStackTrace();
			}
		}
		return emp;
	}
	
	
	
	@PUT
	@Path("/employee")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addEmployee(@FormParam("name") String name,
			@FormParam("sal") Double sal,
			@FormParam("dept") String dept,
			@FormParam("designation") String designation,
			@FormParam("managerId") int managerId,
			@FormParam("emailId") String emailId,
			@FormParam("mobileNo") String mobileNo,
			@FormParam("address") String address,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		
		Employee emp = new Employee(name, sal, dept, designation, managerId, emailId, mobileNo, address);
		try {
			empDAO.addEmployee(emp);
			return SUCCESS_RESULT;
		} catch (Exception e) {
			// Failed to add employee
			e.printStackTrace();
		}
		return FAILURE_RESULT;
	}
	
	
	
	
	@DELETE
	@Path("/employee/{empid}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteEmployee(@PathParam("empid") int empId) {
		
		boolean result = false;
		try {
			result = empDAO.deleteEmployee(new Employee(empId));
		} catch (Exception e) {
			// Failed to delete employee
			e.printStackTrace();
		}
		if (result == true)
			return SUCCESS_RESULT;
		return FAILURE_RESULT;
	}
	
	
	
	@OPTIONS
	@Path("/employee")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		
		return SUPPORTED_OPERATIONS;
	}
	
}
