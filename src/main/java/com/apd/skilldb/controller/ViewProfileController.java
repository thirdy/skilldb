package com.apd.skilldb.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.service.EmployeeService;

/**
 * @author alepasana
 *
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class ViewProfileController {

	private Employee employee = new Employee();
	private int employeeId;
	
	@ManagedProperty("#{employeeService}")
	private EmployeeService employeeService;
		
	public String save(){		
		return "viewprofile?faces-redirect=true";
	}	
	
	public Employee getEmployee(){
		employee = employeeService.find(employeeId);
		
		return employee;
	}
}
