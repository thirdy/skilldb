package com.apd.skilldb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.repository.EmployeeDao;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao employeeDoa;
	
	public void add(Employee employee){
		System.out.println("---------------");
		employeeDoa.save(employee);
	}	
	
	

}
