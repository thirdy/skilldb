package com.apd.skilldb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.entity.Skill;
import com.apd.skilldb.service.EmployeeService;
import com.apd.skilldb.service.SkillService;

/**
 * @author alepasana
 *
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class AddProfileController{

	private Employee employee = new Employee();
	private List<EmployeeSkill> skills;
	
	@ManagedProperty("#{skillService}")
	private SkillService skillService;
	
	@ManagedProperty("#{employeeService}")
	private EmployeeService employeeService;
	
	@ManagedProperty("#{viewEditProfileController}")
	private ViewEditProfileController viewEditProfileController;
		
	@PostConstruct
	public void loadSkills(){
		skills = new ArrayList<EmployeeSkill>();
		List<Skill> skillList = skillService.findAll();
		
		if(skillList != null){
			for(Skill skill: skillList){
				EmployeeSkill empSkill = new EmployeeSkill();
				empSkill.setSkill(skill);
				empSkill.setEmployee(this.employee);
				skills.add(empSkill);
			}
		}
	}
		
	public String save(){	

		Employee existingEmployee = employeeService.find(getEmployeeId(employee.getEmail()));

		if(existingEmployee == null){

			List<EmployeeSkill> empSkills = new ArrayList<EmployeeSkill>();

			for(EmployeeSkill empSkill : skills){
				if(StringUtils.isNotBlank(empSkill.getYearsOfExperience()) || StringUtils.isNotBlank(empSkill.getLevel())){			

					if(empSkill.getIsNewSkill()){
						Skill newSkill = skillService.save(empSkill.getSkill());
						empSkill.getSkill().setId(newSkill.getId());
					}
					
					empSkills.add(empSkill);
				}			
			}

			employee.setSkills(empSkills);		
			employee.setEmployeeId(getEmployeeId(employee.getEmail()));

			employeeService.save(employee);

			viewEditProfileController.setEmployeeId(employee.getEmployeeId(), "false");

			// reset session data
			employee = new Employee();
			loadSkills();
			
			return "viewprofile?faces-redirect=true";
		}

		addMessage("Profile associated to this email address '"+ employee.getEmail() +"' is already exist.");
		
		return "addprofile?faces-redirect=false";
	}
	
	private String getEmployeeId(String email){
		email = email.trim();
		
		if(email.indexOf("@") != -1){
			return email.substring(0, email.indexOf("@"));
		}

		return email;
	}
	
	public String newSkill(){
		EmployeeSkill empSkill = new EmployeeSkill();
		empSkill.setEmployee(employee);
		empSkill.setIsNewSkill(true);
		
		Skill skill = new Skill();
		empSkill.setSkill(skill);
		skills.add(empSkill);

		return "addprofile?faces-redirect=false";
	}
	
	private void addMessage(String message) {
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
}
