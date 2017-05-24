package com.apd.skilldb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apd.skilldb.MyWebAppInitializer;
import com.apd.skilldb.SpringConfiguration;
import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyWebAppInitializer.class, SpringConfiguration.class})
public class EmployeeServiceTest {
	 @Autowired
	 EmployeeService employeeService;
	 
	 private Employee createEmployee(){
		 Employee emp = new Employee();
		 emp.setFirstName("firstName");
		 emp.setSkills(createEmployeeSkills(emp));
		 emp.setLastName("lastName");
		 emp.setManager("Manager");
		 emp.setCountry("country");
		 emp.setRole("role");
		 emp.setEmail("role");
		 emp.setYearsOfWorkExperience("7");
		 Calendar c1 = GregorianCalendar.getInstance();
		 c1.set(2000, 0, 30);  //January 30th 2000
		 emp.setDateHired(c1.getTime());
		 
		 return emp;
	 }
	 
	 private List<EmployeeSkill> createEmployeeSkills(Employee emp){
		 List<EmployeeSkill> empSkills = new ArrayList<EmployeeSkill>();
		 EmployeeSkill skill1 = new EmployeeSkill();
		 skill1.setEmployee(emp);
		 skill1.setLevel("advance");
		 skill1.setCertified(false);
		 skill1.setTypeOfCertification(null);
		 skill1.setComment("comment");
		 Calendar c1 = GregorianCalendar.getInstance();
		 c1.set(2011, 0, 30);  //January 30th 2011
		 skill1.setDateOfCertification(c1.getTime());
		 empSkills.add(skill1);	 
		 
		 return empSkills;
	 }	 
	 
	  @Test
	  public void addEmployee(){
		  Employee emp = createEmployee();
		  
		  employeeService.delete(emp);
		  int employeeId = employeeService.add(emp);
		  Employee empResult  = employeeService.find(employeeId);
		  assertEquals(emp.getLastName(), empResult.getLastName());
		  assertEquals(emp.getSkills().size(), empResult.getSkills().size());
	  }	  
}
