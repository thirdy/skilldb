package com.apd.skilldb.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.apd.skilldb.entity.EmployeeSkill;

public interface EmployeeSkillRepository extends PagingAndSortingRepository<EmployeeSkill, Integer>{
	@Modifying
	void delete(String employeeId) ;
}
