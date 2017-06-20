package com.apd.skilldb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity

@NamedQueries({
	@NamedQuery(name = "Skill.findBySkillName", query = "select skill from Skill skill"
		 + " where skill.skillName = ?1")})
		 
@Getter
@Setter
@ToString
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
