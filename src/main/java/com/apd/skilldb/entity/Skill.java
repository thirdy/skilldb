package com.apd.skilldb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Skill {
	
	@Id
	private String skillCode;
	private String skillCategory;
	private String skillName;
	
	@OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
	private List<EmployeeSkill> employeeSkills;
}
