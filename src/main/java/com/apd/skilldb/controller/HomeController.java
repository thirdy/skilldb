package com.apd.skilldb.controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.apd.skilldb.entity.Check;
import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.repository.EmployeeRepository;
import com.apd.skilldb.service.CheckService;
import com.apd.skilldb.service.EmployeeService;
import com.apd.skilldb.service.EmployeeSkillService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{employeeSkillService}")
	private EmployeeSkillService employeeSkillService;
	
	private List<EmployeeSkill> employeeSkills;
	private List<EmployeeSkill> filteredEmployeeSkills;
	
	private String searchQuery;
	
	@PostConstruct
	public void loadChecks() {
		employeeSkills = employeeSkillService.findAll();
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
