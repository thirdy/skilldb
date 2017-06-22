package com.apd.skilldb.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apd.skilldb.service.EmployeeService;
import com.apd.skilldb.service.ImportGroupService;
import com.apd.skilldb.service.ImportGroupService.ImportServiceException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class ImportController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ManagedProperty("#{importGroupService}")
	private ImportGroupService importGroupService;

	private UploadedFile file;

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		try {
			importGroupService.parseAndSave(file.getInputstream(), file.getFileName());
		} catch (ImportServiceException e) {
			addMessage(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		addMessage("Successfully imported: " + file.getFileName());
	}
	
	private void addMessage(String message) {
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}
}
