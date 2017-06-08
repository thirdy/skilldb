package com.apd.skilldb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	public List<EmployeeSkill> findByNameOrSkill(String keyword);
	
	public List<Employee> findByName(String keyword);
}
