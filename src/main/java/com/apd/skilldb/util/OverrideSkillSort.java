package com.apd.skilldb.util;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class OverrideSkillSort {
	@Transient
	private String sortYearsOfExperience;

	@Transient
	private String sortLevel;

	public String getSortYearsOfExperience(){
		if(sortYearsOfExperience != null)			
			return sortYearsOfExperience;				

		if("less than a year".equalsIgnoreCase(getYearsOfExperience())){			
			setSortYearsOfExperience("1");
		}else{
			setSortYearsOfExperience(getYearsOfExperience());
		}

		return sortYearsOfExperience;
	}

	public String getSortLevel(){
		if(sortLevel != null)			
			return sortLevel;				

		if("Expert".equalsIgnoreCase(getLevel())){			
			setSortLevel("X");
		}else{
			setSortLevel(getLevel());
		}

		return sortLevel;
	}

	abstract public String getLevel();	
	abstract public String getYearsOfExperience();
}
