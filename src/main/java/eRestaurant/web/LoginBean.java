package eRestaurant.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import eRestaurant.entity.Staff;
import eRestaurant.service.StaffService;

@Named
@Scope("session")
public class LoginBean {
	@Inject
	StaffService staffService;
	private String password;
	private String login;
	private Staff worker;

	public LoginBean() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Staff getWorker() {
		return worker;
	}

	public void setWorker(Staff worker) {
		this.worker = worker;
	}

	public String enter() {
		String page = "login";
		try {
			worker = staffService.login(login, password);
		} catch (NullPointerException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Incorrect login or password"));
		}

		switch (worker.getRole()) {
		case "system officer":
			page = "staff?faces-redirect=true";
			break;
		case "administrator":
			page = "admin?faces-redirect=true";
			break;
		case "kitchen staff":
			page = "kitchen?faces-redirect=true";
		}
		return page;
	}
	
	public String logout() {
		worker=null;
		return "login?faces-redirect=true";
	}
}
