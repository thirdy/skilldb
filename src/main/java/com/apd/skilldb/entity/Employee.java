package com.apd.skilldb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@NamedQueries({
    @NamedQuery(name = "Employee.findByNameOrSkill", query = "select empSkill from Employee emp, EmployeeSkill empSkill"
    		 + " where (emp.employeeId = empSkill.employee.employeeId) and "
    		 + "(emp.firstName like ?1 or emp.lastName like ?1 or concat(emp.firstName, ' ', emp.lastName) LIKE ?1 or empSkill.skill.skillCategory like ?1 or empSkill.skill.skillName like ?1)"),

	@NamedQuery(name = "Employee.findByName", query = "select emp from Employee emp"
		 + " where (emp.firstName like ?1 or emp.lastName like ?1 or concat(emp.firstName, ' ', emp.lastName) LIKE ?1)")})

@Getter
@Setter
@ToString
public class Employee {
	

	@Id
	private String employeeId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String manager;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dateHired;

	@Column
	private String role;

	@Column
	private String yearsOfWorkExperience;	

	@Column
	private String country;

	@Column
	private String division;

	@Column(unique=true)	
	private String email;

	@Column
	private String gender;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", cascade = CascadeType.ALL)	
	private List<EmployeeSkill> skills;

	public void addSkill(EmployeeSkill employeeSkill) {
		if (getSkills() == null) skills = new ArrayList<>();
		getSkills().add(employeeSkill);
	}

	@Transient
	private String sheetName;
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
}
