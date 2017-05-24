package com.apd.skilldb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>{
}
