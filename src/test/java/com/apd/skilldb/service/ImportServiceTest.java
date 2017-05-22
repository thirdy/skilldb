package com.apd.skilldb.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.InputStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.entity.Skill;
import com.apd.skilldb.repository.EmployeeRepository;
import com.apd.skilldb.repository.SkillRepository;

@RunWith(MockitoJUnitRunner.class)
public class ImportServiceTest {

	private ImportService importService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private SkillRepository skillRepository;
	
	@Before
	public void setUp() throws Exception {
		Skill skill = new Skill("Continuous Integration and Deployment", "Maven, Ant, Gradle, Grunt, npm, bower");
		when(skillRepository.findBySkillName(anyString())).thenReturn(Arrays.asList(skill ));
		
		importService = new ImportService();
		importService.setEmployeeRepository(employeeRepository);
		importService.setSkillRepository(skillRepository);
	}

	@Test
	public void parseAndSave() throws Exception {
		String FILE_NAME = "/sampleImport.xlsx";
		InputStream fileStream = this.getClass().getResourceAsStream(FILE_NAME);
		Employee employee = importService.parse(fileStream);
		
		assertEquals("Juan", employee.getFirstName());
		assertEquals("Dela Cruz", employee.getLastName());
		
		EmployeeSkill employeeSkill0 = employee.getSkills().get(0);
		assertEquals("Maven, Ant, Gradle, Grunt, npm, bower", employeeSkill0.getSkill().getSkillName());
		assertEquals("Continuous Integration and Deployment", employeeSkill0.getSkill().getSkillCategory());
		assertEquals("4 - 6 years", employeeSkill0.getYearsOfExperience());
		assertEquals("Senior", employeeSkill0.getLevel());
		assertEquals(false, employeeSkill0.isCertified());
	}

}
