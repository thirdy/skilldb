package com.apd.skilldb.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.repository.EmployeeRepository;
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
	
//	public Page<Employee> findAll(Pageable pageable) {
//		return repo.findAll(pageable);
//	}
//	
//	public String add(Employee employee){
//		Employee emp =  repo.save(employee);
//		return emp.getEmployeeId();
//	}	
//	
//	public Employee find(String employeeId){
//		if(logger.isDebugEnabled()){
//			logger.debug(String.format("Find Employee Id %s", employeeId));
//		}
//		
//		return repo.findOne(employeeId);
//	}
//	
//	public void delete(Employee employee){
//		if(logger.isDebugEnabled()){
//			logger.debug(String.format("Delete Employee Id %s", employee.getEmployeeId()));
//		}
//
//		repo.delete(employee);
//	}
	
}
