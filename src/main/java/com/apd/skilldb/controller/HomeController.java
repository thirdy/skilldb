package com.apd.skilldb.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.apd.skilldb.common.HibernateUtils;
import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.service.EmployeeService;
import com.apd.skilldb.util.EmployeeData;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{employeeService}")
	private EmployeeService employeeService;
	
	@ManagedProperty("#{viewEditProfileController}")
	private ViewEditProfileController viewEditProfileController;
	
	private List<EmployeeData> employees;

	private String searchQuery;

	@PostConstruct
	public void loadDefault() {
		if(StringUtils.isBlank(searchQuery)){
			List<Employee> tempEmployees = employeeService.findAll();		
			mapToEmployeeData(tempEmployees);
		}
	}

	public String search() {
		if(!StringUtils.isBlank(searchQuery)){
			employees = new ArrayList<EmployeeData>();

			// search employee with empty skills
			List<Employee> tempEmployees = employeeService.findByName("%" + searchQuery + "%");
			for(Employee emp: tempEmployees){
				if(emp.getSkills() == null || emp.getSkills().size() == 0){
					EmployeeData empData = new EmployeeData();
					BeanUtils.copyProperties(emp, empData);
					
					employees.add(empData);
				}
			}			
			
			// search employees with skills
			List<EmployeeSkill> tempEmployeeSkills = employeeService.findByNameOrSkill("%" + searchQuery + "%");
			for(EmployeeSkill empSkill: tempEmployeeSkills){
				EmployeeData empData = new EmployeeData();
				BeanUtils.copyProperties(empSkill.getEmployee(), empData);
				BeanUtils.copyProperties(empSkill, empData);
				BeanUtils.copyProperties(empSkill.getSkill(), empData);

				employees.add(empData);
			}			
		}else{
			loadDefault();
		}

		return "index.xhtml?faces-redirect=true";
	}

	private void mapToEmployeeData(List<Employee> tempEmployees){
		employees = new ArrayList<EmployeeData>();
		for(Employee emp : tempEmployees){
			EmployeeData empData = new EmployeeData();
			BeanUtils.copyProperties(emp, empData);
			empData.setSkillCategory("-");
			empData.setSkillName("-");
			empData.setYearsOfExperience("-");
			/*
			if(emp.getSkills() != null && emp.getSkills().size() > 0){
				for(EmployeeSkill empSkill : emp.getSkills()){

					EmployeeData empData2 = new EmployeeData();
					BeanUtils.copyProperties(emp, empData2);
					BeanUtils.copyProperties(empSkill, empData2);
					BeanUtils.copyProperties(empSkill.getSkill(), empData2);
					employees.add(empData2);
				}
			}else{
				employees.add(empData);
			}	
			*/
			employees.add(empData);
		}
	}
	
	public String viewDetail(String employeeId) {
		viewEditProfileController.setEmployeeId(employeeId);
		
		return "viewprofile?faces-redirect=true&closable=true";	
	}

	/*
	 * alternative solution
	public String search() {
		if(!StringUtils.isBlank(searchQuery)){
			List<Employee> tempEmployees = employeeService.findByNameOrSkill("%" + searchQuery + "%");
			List<Employee> tempSearchResultEmployees = new ArrayList<Employee>();
			Employee foundEmp = null;
			for(Employee emp: tempEmployees){

				Predicate predicate = HibernateUtils.attributePredicateFactoryContains(EmployeeSkill.class, "skill.skillCategory", "skill.skillName", searchQuery);
				@SuppressWarnings("unchecked")
				List<EmployeeSkill> resultList = (List<EmployeeSkill>) CollectionUtils.select(emp.getSkills(), predicate);

				System.out.println("resultList: " + resultList.size());

				foundEmp = new Employee();
				if(resultList.size() == 0){
					foundEmp.setSkills(emp.getSkills());
				}else{
					foundEmp.setSkills(resultList);
				}

				tempSearchResultEmployees.add(foundEmp);
			}

			mapToEmployeeData(tempSearchResultEmployees);		

		}else{
			loadDefault();
		}

		return "index.xhtml?faces-redirect=true";
	}
	 */

}
