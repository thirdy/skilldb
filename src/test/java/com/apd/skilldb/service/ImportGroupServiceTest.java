package com.apd.skilldb.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.entity.Skill;
import com.apd.skilldb.repository.EmployeeRepository;
import com.apd.skilldb.repository.SkillRepository;

@RunWith(MockitoJUnitRunner.class)
public class ImportGroupServiceTest {

	private ImportGroupService importGroupService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private SkillRepository skillRepository;
	
	@Before
	public void setUp() throws Exception {
		Skill skill = new Skill("Continuous Integration and Deployment", "Maven, Ant, Gradle, Grunt, npm, bower");
		when(skillRepository.findBySkillName(anyString())).thenReturn(Arrays.asList(skill ));
		
		importGroupService = new ImportGroupService();
		importGroupService.setEmployeeRepository(employeeRepository);
		importGroupService.setSkillRepository(skillRepository);
	}

	@Test
	public void parseAndSaveMl() throws Exception {
		String FILE_NAME = "/APD-Malaysia-Team Skillset.xlsx";
		InputStream fileStream = this.getClass().getResourceAsStream(FILE_NAME);
		List<Employee> employees = importGroupService.parse(fileStream);

		Employee employee0 = employees.get(0);
		assertEquals("Arifuddin", employee0.getFirstName());
		assertEquals("Ahmad", employee0.getLastName());
		assertNull(employee0.getSkills());

		Employee employee1 = employees.get(1);
		assertEquals("Azmirrul Izzat", employee1.getFirstName());
		assertEquals("Abdul Aziz", employee1.getLastName());
		assertEquals("Technology", employee1.getDivision());
		assertEquals("Malaysia", employee1.getCountry());
		
		EmployeeSkill employeeSkill1 = employee1.getSkills().get(0);
		assertEquals("Maven, Ant, Gradle, Grunt, npm, bower", employeeSkill1.getSkill().getSkillName());
		assertEquals("Continuous Integration and Deployment", employeeSkill1.getSkill().getSkillCategory());
		assertEquals("Mid Level", employeeSkill1.getLevel());
		assertEquals("2 - 4 years", employeeSkill1.getYearsOfExperience());
		assertEquals("", employeeSkill1.getCertified());
	}

	@Test
	public void parseAndSavePh() throws Exception {
		String FILE_NAME = "/APD-Manila-Team Skillset.xlsx";
		InputStream fileStream = this.getClass().getResourceAsStream(FILE_NAME);
		List<Employee> employees = importGroupService.parse(fileStream);

		Employee employee2 = employees.get(2);
		assertEquals("Analyn", employee2.getFirstName());
		assertEquals("Javier", employee2.getLastName());
		assertNotNull(employee2.getSkills());

		Employee employee5 = employees.get(5);
		assertEquals("Ariel", employee5.getFirstName());
		assertEquals("Lepasana", employee5.getLastName());
		assertEquals("Delivery", employee5.getDivision());
		assertEquals("Philippines", employee5.getCountry());
		
		EmployeeSkill employeeSkill5 = employee5.getSkills().get(0);
		assertEquals("Junior", employeeSkill5.getLevel());
		assertEquals("less than a year", employeeSkill5.getYearsOfExperience());
		assertEquals("No", employeeSkill5.getCertified());
	}

	@Test
	public void parseAndSaveShnghai() throws Exception {
		String FILE_NAME = "/APD-Shanghai-Team Skillset.xlsx";
		InputStream fileStream = this.getClass().getResourceAsStream(FILE_NAME);
		List<Employee> employees = importGroupService.parse(fileStream);
	}
}
