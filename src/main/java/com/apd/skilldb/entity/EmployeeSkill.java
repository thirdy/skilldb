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
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EmployeeSkill && (id == 0 || ((EmployeeSkill)  obj).getId() == 0) 
				&& (skill != null && ((EmployeeSkill)  obj).getSkill() != null)){
			
			return (skill.getId() == ((EmployeeSkill)  obj).getSkill().getId());
		}		
		
		return super.equals(obj);
	}
}
