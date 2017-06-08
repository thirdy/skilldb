package com.apd.skilldb.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.repository.EmployeeSkillRepository;

@Service
public class EmployeeSkillService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeSkillRepository repo;

	public List<EmployeeSkill> findAll() {
		List<EmployeeSkill> employeeSkills = new ArrayList<>();
		repo.findAll().forEach(employeeSkills::add);
		
		return employeeSkills;
	}	
}
