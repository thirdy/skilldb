package com.apd.skilldb.util;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.model.SelectableDataModel;

import com.apd.skilldb.service.EmployeeService;

@Getter
@Setter
public class EmployeeData{

	private String employeeId;
	private String firstName;
	private String lastName;
	private String manager;
	private Date dateHired;
	private String role;
	private String yearsOfWorkExperience;	
	private String country;
	private String division;
	private String email;
	private String gender;
	
	private String yearsOfExperience;
	private String level;
	private String certified;
	private String typeOfCertification;
	private Date dateOfCertification;
	private String comment;	
	
	private String skillCategory;
	private String skillName;		
}
