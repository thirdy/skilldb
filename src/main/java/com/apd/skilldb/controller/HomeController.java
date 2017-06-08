package com.apd.skilldb.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.service.EmployeeService;
import com.apd.skilldb.util.EmployeeData;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{employeeService}")
	private EmployeeService employeeService;
	private EmployeeData selectedEmployee;
	private List<EmployeeData> employees;
	private List<EmployeeSkill> filteredEmployeeSkills;
		
	private String searchQuery;
	
	@PostConstruct
	public void loadChecks() {
		//employees = employeeService.findByNameOrSkill("");
		employees = new ArrayList<EmployeeData>();
		
		List<Employee> tempEmployees = employeeService.findAll();
		for(Employee emp : tempEmployees){
			EmployeeData empData = new EmployeeData();
			BeanUtils.copyProperties(emp, empData);
			
			if(emp.getSkills() != null && emp.getSkills().size() > 0){
				for(EmployeeSkill empSkill : emp.getSkills()){
					
					EmployeeData empData2 = new EmployeeData();
					BeanUtils.copyProperties(emp, empData2);
					BeanUtils.copyProperties(empSkill, empData2);
					BeanUtils.copyProperties(empSkill.getSkill(), empData2);
					employees.add(empData2);
				}
			}else{
				employees.add(empData);
			}			
		}
		

//		employeeService.findAll(new PageRequest(0, 10)).forEach(employeeSkills::add);
		
	}

	
//	public String viewProfile(Check check) {
//		this.check = check;
//		return "viewprofile.xhtml?faces-redirect=true";
//	}
//
//	public String editProfile(Check check) {
//		this.check = check;
//		return "editprofile.xhtml?faces-redirect=true";
//	}
//
//	public String saveEdit() {
//		checkService.save(check);
//		addMessage("Changes Saved!");
//		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//		return "viewprofile.xhtml?faces-redirect=true";
//	}
//
//	public String cancelEdit() {
//		return "viewprofile.xhtml?faces-redirect=true";
//	}
//	
//	private void addMessage(String message) {
//		FacesContext.getCurrentInstance().addMessage
//			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
//	}
	
	
}
