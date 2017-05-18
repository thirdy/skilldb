package com.apd.skilldb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	public List<EmployeeSkill> findSkills(int employeeId);
}
