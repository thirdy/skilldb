package com.apd.skilldb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.apd.skilldb.common.HibernateUtils;
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
public class ViewEditProfileController {

	private Employee employee;
	private List<EmployeeSkill> allSkills;
	private List<EmployeeSkill> skills;
	
	@ManagedProperty("#{skillService}")
	private SkillService skillService;
	
	@ManagedProperty("#{employeeService}")
	private EmployeeService employeeService;
	
	@ManagedProperty("#{viewProfileController}")
	private ViewEditProfileController viewProfileController;
	
	private String employeeId;	
	
	@PostConstruct
	public void loadSkills(){
		allSkills = new ArrayList<EmployeeSkill>();
		List<Skill> skillList = skillService.findAll();
		
		if(skillList != null){
			for(Skill skill : skillList){
				EmployeeSkill empSkill = new EmployeeSkill();
				empSkill.setSkill(skill);
				allSkills.add(empSkill);
			}
		}
	}
	
	private void initializeEditSkills(){
		skills = new ArrayList<EmployeeSkill>();
		EmployeeSkill editSkill = null;
		for(EmployeeSkill empSkill : allSkills){
			editSkill = new EmployeeSkill();
			
			Predicate guidPredicate = HibernateUtils.attributePredicateFactory(EmployeeSkill.class, "skill.id", empSkill.getSkill().getId());
			@SuppressWarnings("unchecked")
			List<EmployeeSkill> selectedList = (List<EmployeeSkill>) CollectionUtils.select(employee.getSkills(), guidPredicate);
			
			System.out.println(">>>>>>>>>>>>>>>>>>> " + selectedList.size());
			if(selectedList.size() > 0){
				BeanUtils.copyProperties(selectedList.get(0), editSkill);
			}else{
				BeanUtils.copyProperties(empSkill, editSkill);
			}
			
			editSkill.setEmployee(employee);
			skills.add(editSkill);
		}
	}
	
	public Employee getEmployee(){
		employee = employeeService.find(employeeId);
				
		return employee;
	}
	
	public String edit(){		
		
		initializeEditSkills();	
		return "editprofile?faces-redirect=true";
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
