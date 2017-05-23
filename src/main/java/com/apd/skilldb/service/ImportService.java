package com.apd.skilldb.service;

import static org.apache.commons.lang.StringUtils.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apd.skilldb.entity.Employee;
import com.apd.skilldb.entity.EmployeeSkill;
import com.apd.skilldb.entity.Skill;
import com.apd.skilldb.repository.EmployeeRepository;
import com.apd.skilldb.repository.SkillRepository;

import lombok.Setter;

@Service
@Setter
public class ImportService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SkillRepository skillRepository;

	public void parseAndSave(InputStream inputStream, String fileName) throws ImportServiceException {
		try {
			logger.info("Importing file: " + fileName);
			Employee employee = parse(inputStream);
			employeeRepository.save(employee);
		} catch (IOException e) {
			throw new ImportServiceException(fileName, e);
		}
	}
	
	Employee parse(InputStream inputStream) throws IOException {
		Employee employee = new Employee();
		
		try(Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			List<Row> rows = new ArrayList<>(200);
			iterator.forEachRemaining(rows::add);
			
			// Name
			String name = cellVal(rows, 0, 1);
			employee.setFirstName(substringBefore(name, " "));
			employee.setLastName(substringAfter(name, " "));
			
			// Skills
			List<Row> skillRows = determineSkillRows(rows);
			for (Row sRow : skillRows) {
				String skillName = cellVal(sRow, 1);
				String yrsExp = cellVal(sRow, 2);
				String level = cellVal(sRow, 3);
				boolean certified = equalsIgnoreCase(cellVal(sRow, 3), "Yes");
				
				Skill skill = skillRepository.findBySkillName(skillName).get(0);
				EmployeeSkill employeeSkill = new EmployeeSkill(yrsExp, level, certified);
				employeeSkill.setSkill(skill);
				employee.addSkill(employeeSkill);
			}
		}
		return employee;
	}
	
	private List<Row> determineSkillRows(List<Row> rows) {
		List<Row> skillRows = new ArrayList<>();
		List<Row> rowsAfter8 = rows.subList(8, rows.size() - 1);
		String endRowMarker = "If we have missed any skills you have and you think it might be relevant to APD - you are highly encouraged to list it down";
		for (Row skillRow : rowsAfter8) {
			String cat = cellVal(skillRow, 0);
			String yrsExp = cellVal(skillRow, 2);
			if (equalsIgnoreCase(endRowMarker, cat) || isBlank(cat)) {
				break;
			}
			if (isNotBlank(yrsExp)) {
				skillRows.add(skillRow);
			}
		}
		return skillRows;
	}

	private String cellVal(List<Row> rows, int i, int j) {
		Cell cell = rows.get(i).getCell(j);
		return cell.getStringCellValue();
	}

	private String cellVal(Row row, int j) {
		Cell cell = row.getCell(j);
		return cell != null ? cell.getStringCellValue() : "";
	}

	public static class ImportServiceException extends Exception {
		private static final long serialVersionUID = 1L;

		public ImportServiceException(String fileName, Throwable t) {
			super("Failed to import file: " + fileName + ", cause: " + t.getMessage(), t);
		}
	}
}
