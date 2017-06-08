package com.apd.skilldb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepo.findAll(pageable);
	}
	
	public String add(Employee employee){
		Employee emp =  employeeRepo.save(employee);
		return emp.getEmployeeId();
	}	
	
	public Employee find(String employeeId){
		if(logger.isDebugEnabled()){
			logger.debug(String.format("Find Employee Id %s", employeeId));
		}
		
		return employeeRepo.findOne(employeeId);
	}
	
	public List<EmployeeSkill> findByNameOrSkill(String keyword){
		if(logger.isDebugEnabled()){
			logger.debug(String.format("Find Employee by keyword %s", keyword));
		}
		
		return employeeRepo.findByNameOrSkill(keyword);
	}
	
	public List<Employee> findByName(String keyword){
		if(logger.isDebugEnabled()){
			logger.debug(String.format("Find Employee by keyword %s", keyword));
		}
		
		return employeeRepo.findByName(keyword);
	}
	
	public void delete(Employee employee){
		if(logger.isDebugEnabled()){
			logger.debug(String.format("Delete Employee Id %s", employee.getEmployeeId()));
		}

		employeeRepo.delete(employee);
	}
	
	public List<Employee> findAll() {		
		return employeeRepo.findAll(new Sort(Sort.Direction.ASC, "firstName", "lastName"));		
	}
	
}
