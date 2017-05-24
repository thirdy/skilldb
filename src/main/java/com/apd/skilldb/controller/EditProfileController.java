package com.apd.skilldb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

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
public class EditProfileController {

	private Employee employee = new Employee();
	private List<EmployeeSkill> skills;
	
	@ManagedProperty("#{skillService}")
	private SkillService skillService;
	
	@ManagedProperty("#{employeeService}")
	private EmployeeService employeeService;
	
	@ManagedProperty("#{viewProfileController}")
	private ViewProfileController viewProfileController;
	
	
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
		List<EmployeeSkill> empSkills = new ArrayList<EmployeeSkill>();
		
		for(EmployeeSkill empSkill : skills){
			if(StringUtils.isNotBlank(empSkill.getYearsOfExperience())){
				empSkills.add(empSkill);
			}
		}
		
		employee.setSkills(empSkills);		
		employeeService.add(employee);
		
		viewProfileController.setEmployeeId(employee.getEmployeeId());
		
		return "viewprofile?faces-redirect=true";
	}
	
	
}
