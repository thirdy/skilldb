package com.apd.skilldb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Skill {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String skillCategory;
	@Column(unique=true)
	private String skillName;
	
	public Skill() {
	}
	
	public Skill(String skillCategory, String skillName) {
		this.skillCategory = skillCategory;
		this.skillName = skillName;
	}

	@OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
	private List<EmployeeSkill> employeeSkills;
}
