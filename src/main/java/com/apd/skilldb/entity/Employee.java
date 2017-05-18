package com.apd.skilldb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries({
    @NamedQuery(name = "Employee.findSkills", query = "select es from EmployeeSkill es"
    		 + " where es.employee.employeeId = ?1 ")})
@Getter
@Setter
public class Employee {

	
	@Id
	private int employeeId;
	
	//@Size(min = 1, message = "First Name cannot be empty!")
	@Column
	private String firstName;

	//@Size(min = 1, message = "Middle Name cannot be empty!")
	@Column
	private String MiddleName;
	
	//@Size(min = 1, message = "Last Name cannot be empty!")
	@Column
	private String lastName;
	
	//@Size(min = 1, message = "Date Hired cannot be empty!")
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateHired;
	
	//@Size(min = 1, message = "Role cannot be empty!")
	@Column
	private String role;
	
	//@Size(min = 1, message = "Years of work experience cannot be empty!")
	@Column
	private String yearsOfWorkExperience;	
	
	//@Size(min = 1, message = "Office Location cannot be empty!")
	@Column
	private String officeLocation;
	
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)	
	private List<EmployeeSkill> skills;
	
	@Transactional
	public List<EmployeeSkill> getSkills(){
		return skills;
	}
	
	public void setSkills(List<EmployeeSkill> skills){
		this.skills = skills;
	}
}
