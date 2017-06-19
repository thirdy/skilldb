package com.apd.skilldb.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

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
import com.apd.skilldb.util.ExportUtil;

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
	
	private String employeeId;	
	private String closable;
	
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
		for(int i = 0 ; i < allSkills.size(); i++){
			EmployeeSkill empSkill = allSkills.get(i);
			editSkill = new EmployeeSkill();
			
			Predicate predicate = HibernateUtils.attributePredicateFactory(EmployeeSkill.class, "skill.id", empSkill.getSkill().getId());
			@SuppressWarnings("unchecked")
			List<EmployeeSkill> selectedList = (List<EmployeeSkill>) CollectionUtils.select(employee.getSkills(), predicate);
			
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
		if(employee == null || !employee.getEmployeeId().equalsIgnoreCase(employeeId)){
			employee = employeeService.find(employeeId);
		}
				
		return employee;
	}
	
	public String edit(){			
		initializeEditSkills();	
		return "editprofile?faces-redirect=true&closable=" + closable;
	}
			
	public String save(){		
		List<EmployeeSkill> empSkills = new ArrayList<EmployeeSkill>();
		
		for(EmployeeSkill empSkill : skills){
			if(StringUtils.isNotBlank(empSkill.getYearsOfExperience()) || StringUtils.isNotBlank(empSkill.getLevel())){
				empSkills.add(empSkill);
			}
		}
		
		employee.setSkills(empSkills);		
		employeeService.save(employee);		
		
		return "viewprofile?faces-redirect=true&closable=" + closable;
	}
	
	
	public String cancelEdit(){				
		return "viewprofile?faces-redirect=true&closable=" + closable;
	}
	
	public String deactivate(){		
		employee.setIsActive(0);
		employeeService.save(employee);
		
		return "viewprofile?faces-redirect=true&closable=" + closable;
	}
	
	public String activate(){		
		employee.setIsActive(1);
		employeeService.save(employee);
		
		return "viewprofile?faces-redirect=true&closable=" + closable;
	}
	
	public String export() {
	    try {

	        String filename = employee.getFirstName() + "-" + employee.getLastName() + "_profile.csv";

	        FacesContext fc = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

	        response.reset();
	        response.setContentType("text/comma-separated-values");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

	        OutputStream output = response.getOutputStream();
	        ExportUtil.exportProfile(employee, output);

	        output.flush();
	        output.close();

	        fc.responseComplete();

	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	    return "";
	}	
	
	public void setEmployeeId(String employeeId, String closable){
		this.employeeId = employeeId;
		this.closable = closable;
	}
	
}
