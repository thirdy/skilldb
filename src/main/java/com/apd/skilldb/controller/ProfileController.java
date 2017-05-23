package com.apd.skilldb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.entity.Skill;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class ProfileController {

	private int employeeId;
	private String firstName;
	private String MiddleName;
	private String lastName;
	private Date dateHired;
	private String role;
	private String yearsOfWorkExperience;	
	private String officeLocation;
	private List<EmployeeSkill> skills;
	
	@PostConstruct
	public void loadSkills(){
		skills = new ArrayList<EmployeeSkill>();
		
		Skill skill1 = new Skill();
		skill1.setSkillCategory("Category 1");
		skill1.setSkillName("Skill 1");
		EmployeeSkill empSkill1 = new EmployeeSkill();
		empSkill1.setSkill(skill1);
		
		skills.add(empSkill1);		
		
		Skill skill2 = new Skill();
		skill2.setSkillCategory("Category 2");
		skill2.setSkillName("Skill 2");
		EmployeeSkill empSkill2 = new EmployeeSkill();
		empSkill2.setSkill(skill2);
		
		skills.add(empSkill2);	
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateHired() {
		return dateHired;
	}
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getYearsOfWorkExperience() {
		return yearsOfWorkExperience;
	}
	public void setYearsOfWorkExperience(String yearsOfWorkExperience) {
		this.yearsOfWorkExperience = yearsOfWorkExperience;
	}
	public String getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	public List<EmployeeSkill> getSkills() {
		return skills;
	}
	public void setSkills(List<EmployeeSkill> skills) {
		this.skills = skills;
	}	
	
	public String save(){
		System.out.println("SAVE");
		System.out.println(skills.get(0).getTypeOfCertification());
		
		return null;
	}
}
