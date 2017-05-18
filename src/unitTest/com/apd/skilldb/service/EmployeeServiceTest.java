package com.apd.skilldb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apd.skilldb.MyWebAppInitializer;
import com.apd.skilldb.SpringConfiguration;
import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.service.EmployeeService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyWebAppInitializer.class, SpringConfiguration.class})
public class EmployeeServiceTest {
	 @Autowired
	 EmployeeService employeeService;
	 
	 private Employee createEmployee(){
		 Employee emp = new Employee();
		 emp.setFirstName("firstName");
		 emp.setEmployeeId(123);
		 //emp.setEmployeeSkills(employeeSkills);
		 emp.setLastName("lastName");
		 emp.setMiddleName("MiddleName");
		 emp.setOfficeLocation("officeLocation");
		 emp.setRole("role");
		 emp.setYearsOfWorkExperience("20100203");
		 
		 return emp;
	 }
	 
	  @Test
	  public void addEmployee(){
		  Employee emp = createEmployee();
		  
		  employeeService.add(emp);
		  System.out.println("Add");
	  }
}
