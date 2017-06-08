package com.apd.skilldb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apd.skilldb.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	//public List<Employee> findByNameOrSkill(String keyword);
}
