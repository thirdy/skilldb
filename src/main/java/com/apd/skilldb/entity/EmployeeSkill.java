package com.apd.skilldb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmployeeSkill {
	
	@Id
	@GeneratedValue
	private int id;

	private String yearsOfExperience;
	private String level;
	private boolean certified;
	private String typeOfCertification;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfCertification;
	private String comment;	
	
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "skillId")
	private Skill skill;
	
	public EmployeeSkill() {
	}
	
	public EmployeeSkill(String yearsOfExperience, String level, boolean certified) {
		this.yearsOfExperience = yearsOfExperience;
		this.level = level;
		this.certified = certified;
	}
}
