package com.apd.skilldb.service;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImportServiceTest {

	private ImportService importService;

	@Mock
	private EmployeeService employeeService;

	@Before
	public void setUp() throws Exception {
		importService = new ImportService();
		importService.setEmployeeService(employeeService);
	}

	@Test
	public void parseAndSave() throws Exception {
		String FILE_NAME = "/sampleImport.xlsx";
		InputStream fileStream = this.getClass().getResourceAsStream(FILE_NAME);
		importService.parseAndSave(fileStream);

		// List<Check> checks = new ArrayList<Check>();
		// Check check1 = new Check();
		// check1.setActive(true);
		// check1.setId(1);
		// Check check2 = new Check();
		// check2.setActive(true);
		// check2.setId(2);
		// checks.add(check1);
		// checks.add(check2);
		// List<CheckResult> checkResults = new ArrayList<CheckResult>();
		// checkResults.add(new CheckResult());
		// Mockito.when(checkResultRepository.findByCheck(Mockito.any(Check.class),
		// Mockito.any(PageRequest.class))).thenReturn(checkResults);
		// Map<Integer, CheckResultDto> map =
		// checkResultService.getLastResults(checks);
		// assertEquals(2, map.size());
		// assertNotNull(map.get(1));
		// assertNotNull(map.get(2));
	}

}
