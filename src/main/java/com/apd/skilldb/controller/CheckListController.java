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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class CheckListController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{checkService}")
	private CheckService checkService;
	
	private List<Check> checks;
	
	private Check check = new Check();
	
	@PostConstruct
	public void loadChecks() {
		checks = checkService.findAll();
	}
	
	public void save() {
		checkService.save(check);
		check = new Check();
		checks = checkService.findAll();
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Check saved!", null));
		clear();
	}
	
	public void remove(Check check) {
		checkService.remove(check);
		checks = checkService.findAll();
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Check removed!", null));
	}
	
	public void clear() {
		check = new Check();
	}

	public String viewProfile(Check check) {
		this.check = check;
		return "viewprofile.xhtml?faces-redirect=true";
	}
}
