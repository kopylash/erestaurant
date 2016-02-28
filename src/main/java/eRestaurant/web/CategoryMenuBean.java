package eRestaurant.web;

import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import eRestaurant.entity.DishCategory;
import eRestaurant.entity.MenuDish;
import eRestaurant.service.MenuService;

@Named
@Scope("session")
public class CategoryMenuBean {

	@Inject
	private MenuService menuService;
	private DishCategory dc;
	private List<MenuDish> menuList;

	public CategoryMenuBean() {

	}

	public DishCategory getDc() {
		return dc;
	}

	public void setDc(DishCategory dc) {
		this.dc = dc;
	}

	public List<MenuDish> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuDish> menuList) {
		this.menuList = menuList;
	}

	public void addDishMessage(int menuDishId) {
		FacesContext context = FacesContext.getCurrentInstance();
		for (MenuDish menuDish : menuList) {
			if (menuDish.getId() == menuDishId) {
				context.addMessage(null,
						new FacesMessage("" + menuDish.getName()
								+ " added to your cart"));
			}
		}

	}

	public String chooseCategory(int categoryId) {
		dc = menuService.findCategory(categoryId);
		menuList = menuService.getMenu(dc.getId());
		return "category?faces-redirect=true";
	}
}
