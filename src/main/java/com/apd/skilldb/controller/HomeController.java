package com.apd.skilldb.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.service.EmployeeService;
import com.apd.skilldb.util.EmployeeData;


/**
 * @author alepasana
 *
 */
@ManagedBean
@SessionScoped
@Getter
@Setter
public class HomeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{employeeService}")
	private EmployeeService employeeService;
	
	@ManagedProperty("#{viewEditProfileController}")
	private ViewEditProfileController viewEditProfileController;
	
	private List<EmployeeData> employees;
	private String searchQuery;

	@PostConstruct
	public void loadDefault() {
		if(StringUtils.isBlank(getSearchQuery())){
			List<Employee> tempEmployees = employeeService.findAll();		
			mapToEmployeeData(tempEmployees);
		}
	}

	public String search() {

		if(!StringUtils.isBlank(getSearchQuery())){
			employees = new ArrayList<EmployeeData>();
			String q = null;
			boolean isSpecificQuery = false; 
			if(!(getSearchQuery().trim().startsWith("\"") && getSearchQuery().trim().endsWith("\""))){
				q = "%" + getSearchQuery() + "%";
			}else{
				q = getSearchQuery().substring(1).substring(0, getSearchQuery().length() - 2);
				isSpecificQuery = true;
			}
					
			// search employee with empty skills
			List<Employee> tempEmployees = employeeService.findByName(q);
			for(Employee emp: tempEmployees){
				if(emp.getSkills() == null || emp.getSkills().size() == 0 || (isSpecificQuery)){
					EmployeeData empData = new EmployeeData();
					BeanUtils.copyProperties(emp, empData);
					
					employees.add(empData);
				}
			}			
			
			// search employees with skills
			List<EmployeeSkill> tempEmployeeSkills = employeeService.findByNameOrSkill(q);
			for(EmployeeSkill empSkill: tempEmployeeSkills){
				EmployeeData empData = new EmployeeData();
				BeanUtils.copyProperties(empSkill.getEmployee(), empData);
				BeanUtils.copyProperties(empSkill, empData);
				BeanUtils.copyProperties(empSkill.getSkill(), empData);

				employees.add(empData);
			}			
		}else{
			loadDefault();
		}

		return "index.xhtml?faces-redirect=false";
	}

	private void mapToEmployeeData(List<Employee> tempEmployees){
		employees = new ArrayList<EmployeeData>();
		for(Employee emp : tempEmployees){
			if(emp.getIsActive() == 1){
				EmployeeData empData = new EmployeeData();
				BeanUtils.copyProperties(emp, empData);
				empData.setSkillCategory("-");
				empData.setSkillName("-");
				empData.setYearsOfExperience("-");
				employees.add(empData);
			}
		}
	}
	
	public String viewDetail(String employeeId ) {
        viewEditProfileController.setEmployeeId(employeeId, "true");		
		return "viewprofile?faces-redirect=true&closable=true";	
	}
	
	public int getSize(){
		if(employees == null){
			return 0;
		}
		
		return employees.size();
	}
}
