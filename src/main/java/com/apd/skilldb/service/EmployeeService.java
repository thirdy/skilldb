package com.apd.skilldb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public int add(Employee employee){
		Employee emp =  employeeRepo.save(employee);
		return emp.getEmployeeId();
	}	
	
	public Employee find(int employeeId){
		if(logger.isDebugEnabled()){
			logger.debug(String.format("Find Employee Id %s", employeeId));
		}
		
		return employeeRepo.findOne(employeeId);
	}	
	
	public List<EmployeeSkill> findSkills(int employeeId){
		if(logger.isDebugEnabled()){
			logger.debug(String.format("Find Skills Employee Id %s", employeeId));
		}
		
		return employeeRepo.findSkills(employeeId);
	}	
	
	public void delete(Employee employee){
		if(logger.isDebugEnabled()){
			logger.debug(String.format("Delete Employee Id %s", employee.getEmployeeId()));
		}

		employeeRepo.delete(employee);
	}	
}
