package eRestaurant.web;

import java.io.IOException;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import eRestaurant.entity.Staff;
import eRestaurant.service.StaffService;

@Named
@Scope("session")
public class StaffBean {
	@Inject
	private StaffService staffService;
	@Inject
	private LoginBean loginBean;
	private Staff worker;

	public StaffBean() {

	}

	public List<Staff> getStaffList() {
		return staffService.getStaffList();
	}

	public Staff getWorker() {
		return worker;
	}

	public void setWorker(Staff worker) {
		this.worker = worker;
	}

	public void setWorkerId(int staffId) {
		worker = staffService.findById(staffId);
	//	System.out.println(worker.getName());

	}

	public void deleteStaff(int staffId) {
		staffService.deleteStaff(staffId);
	}

	public String createStaff() {
		staffService.addStaff(worker);
		return "staff?faces-redirect=true";
	}
	
	public void onCreateStaff() {
		worker=new Staff();
	}

	public void editStaff() {
		staffService.editStaff(worker.getId(), worker.getSurname(),
				worker.getName(), worker.getLogin(), worker.getPassword(),
				worker.getRole());
	}

	public void onPreRenderView(ComponentSystemEvent event) {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();

		if (loginBean.getWorker() == null
				|| !("system officer".equals(this.loginBean.getWorker()
						.getRole()))) {
			try {
				context.redirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
