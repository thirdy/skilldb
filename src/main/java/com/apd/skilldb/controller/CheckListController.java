package com.apd.skilldb.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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
		addMessage("Check saved!");
		clear();
	}
	
	public void remove(Check check) {
		checkService.remove(check);
		checks = checkService.findAll();
		addMessage("Check removed!");
	}
	
	public void clear() {
		check = new Check();
	}

	public String viewProfile(Check check) {
		this.check = check;
		return "viewprofile.xhtml?faces-redirect=true";
	}

	public String editProfile(Check check) {
		this.check = check;
		return "editprofile.xhtml?faces-redirect=true";
	}

	public String saveEdit() {
		checkService.save(check);
		addMessage("Changes Saved!");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "viewprofile.xhtml?faces-redirect=true";
	}

	public String cancelEdit() {
		return "viewprofile.xhtml?faces-redirect=true";
	}
	
	private void addMessage(String message) {
		FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}
}
