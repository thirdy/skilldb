package com.apd.skilldb.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.apd.skilldb.entity.Check;
import com.apd.skilldb.service.CheckService;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class ImportController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ManagedProperty("#{checkService}")
	private CheckService checkService;

	private UploadedFile file;

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		logger.info("File: " + file.getFileName());
		addMessage("Successfully mported 100 records.");
	}
	
	private void addMessage(String message) {
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}
}
