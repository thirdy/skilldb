package com.apd.skilldb.util;

import java.io.IOException;
import java.io.OutputStream;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;

public class ExportUtil {
	
	public static void exportProfile(Employee employee, OutputStream output) throws IOException{
		output.write(("First Name, " + employee.getFirstName()).getBytes());
		output.write(("\nLast Name, " + employee.getLastName()).getBytes());
		output.write(("\nEmail Address, " + employee.getEmail()).getBytes());
		output.write(("\nGender, " + employee.getGender()).getBytes());
		output.write(("\nPosition/Role, " + employee.getRole()).getBytes());
		output.write(("\nDate Employed, " + employee.getDateHired()).getBytes());
		output.write(("\nCountry, " + employee.getCountry()).getBytes());
		output.write(("\nYears of working experience, " + employee.getYearsOfWorkExperience()).getBytes());
		output.write(("\nManager, " + employee.getManager()).getBytes());
		
		// skills		
		if(employee.getSkills() != null){
			output.write(("\nList of Skills").getBytes());
			output.write(("\nSkill Category, Skill Name, Years of Experience, Level, Certified, Type of Certification, Date of certification, Comments\n").getBytes());
			for(EmployeeSkill empSkill: employee.getSkills()){
				output.write(("\"" + (empSkill.getSkill() != null ? empSkill.getSkill().getSkillCategory() : "") + "\",").getBytes());
				output.write(("\"" + (empSkill.getSkill() != null ? empSkill.getSkill().getSkillName() : "") + "\",").getBytes());
				output.write((empSkill.getYearsOfExperience() + ",").getBytes());
				output.write((empSkill.getLevel() + ",").getBytes());
				output.write(((empSkill.getCertified() != null ? empSkill.getCertified() : "") + ",").getBytes());
				output.write(((empSkill.getTypeOfCertification() != null ? empSkill.getTypeOfCertification() : "") + ",").getBytes());
				output.write(((empSkill.getDateOfCertification() != null ? empSkill.getDateOfCertification() : "") + ",").getBytes());
				output.write(((empSkill.getComment() != null ? empSkill.getComment() : "") + ",").getBytes());
				output.write(("\n").getBytes());
			}
		}
	}

}
