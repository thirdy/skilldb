package com.apd.skilldb.util;

import java.util.Date;

import org.primefaces.model.SelectableDataModel;


public class EmployeeData implements SelectableDataModel{

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
	
		
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Date getDateHired() {
		return dateHired;
	}
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getYearsOfWorkExperience() {
		return yearsOfWorkExperience;
	}
	public void setYearsOfWorkExperience(String yearsOfWorkExperience) {
		this.yearsOfWorkExperience = yearsOfWorkExperience;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCertified() {
		return certified;
	}
	public void setCertified(String certified) {
		this.certified = certified;
	}
	public String getTypeOfCertification() {
		return typeOfCertification;
	}
	public void setTypeOfCertification(String typeOfCertification) {
		this.typeOfCertification = typeOfCertification;
	}
	public Date getDateOfCertification() {
		return dateOfCertification;
	}
	public void setDateOfCertification(Date dateOfCertification) {
		this.dateOfCertification = dateOfCertification;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSkillCategory() {
		return skillCategory;
	}
	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	@Override
	public Object getRowData(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getRowKey(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
