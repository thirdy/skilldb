package com.apd.skilldb.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity

@NamedQueries({
	@NamedQuery(name = "EmployeeSkill.delete", query = "delete  from EmployeeSkill empSkill"
		 + " where empSkill.employee.employeeId = ?1")})
@Getter
@Setter
public class EmployeeSkill {
	
	@Id
	@GeneratedValue
	private int id;

	private String yearsOfExperience;
	private String level;
	private String certified;
	private String typeOfCertification;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfCertification;
	private String comment;	
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "skillId")
	private Skill skill;
	
	@Transient
	private Boolean isNewSkill; 
	
	public EmployeeSkill() {
	}
	
	public EmployeeSkill(String yearsOfExperience, String level, String certified, String typeOfCertification) {
		this.yearsOfExperience = yearsOfExperience;
		this.level = level;
		this.certified = certified;
		this.typeOfCertification = typeOfCertification;
	}
}
