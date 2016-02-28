package eRestaurant.web;

import java.io.IOException;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import eRestaurant.entity.PendingDish;
import eRestaurant.service.KitchenService;

@Named
@Scope("session")
public class KitchenBean {
	@Inject
	private KitchenService kitchenService;
	@Inject
	private LoginBean loginBean;

	public KitchenBean() {

	}

	public List<PendingDish> getPendingList() {
		return kitchenService.getPendingList();
	}

	public List<PendingDish> getPreparedDishes() {
		return kitchenService.getPreparedDishes();
	}

	public String markAsDone(int dishId) {
		kitchenService.markDishAsDone(dishId);
		// System.out.println("DONE!!!!!!");
		return "kitchen";
	}

	public String checkOrderId(int dishId) {
		return "Order";
	}

	public void onPreRenderView(ComponentSystemEvent event) {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();

		if (loginBean.getWorker() == null
				|| !("kitchen staff".equals(this.loginBean.getWorker()
						.getRole()))) {
			try {
				context.redirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
