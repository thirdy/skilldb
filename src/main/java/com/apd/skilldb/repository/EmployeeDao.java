package com.apd.skilldb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apd.skilldb.entity.Check;
import com.apd.skilldb.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
