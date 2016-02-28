package eRestaurant.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import eRestaurant.entity.DishCategory;
import eRestaurant.entity.DishImage;
import eRestaurant.entity.MenuDish;
import eRestaurant.service.AdminService;
import eRestaurant.service.MenuService;

@Named
@Scope("session")
public class AdminPanelBean {
	@Inject
	private AdminService adminService;
	@Inject
	private MenuService menuService;
	@Inject
	private LoginBean loginBean;
	private List<DishImage> imgList;
	private List<DishCategory> categoryList;
	private List<MenuDish> menuList;
	private int dishCategoryId;
	private int imageId;
	private MenuDish menuDish;

	public AdminPanelBean() {

	}

	@PostConstruct
	public void init() {
		categoryList = menuService.getCategories();
		menuList = menuService.getMenu(1);
		
	}

	public int getDishCategoryId() {
		return dishCategoryId;
	}

	public void setDishCategoryId(int dishCategoryId) {
		this.dishCategoryId = dishCategoryId;
	}

	public List<DishCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<DishCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public List<MenuDish> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuDish> menuList) {
		this.menuList = menuList;
	}

	public MenuDish getMenuDish() {
		return menuDish;
	}

	public void setMenuDish(MenuDish menuDish) {
		this.menuDish = menuDish;
	}

	public List<DishImage> getImgList() {
		return imgList;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public void setImgList(List<DishImage> imgList) {
		this.imgList = imgList;
	}

	public String setMenuDishId(int menuDishId) {
		menuDish = menuService.findMenuDishById(menuDishId);
		imgList = adminService.getImageList(menuDishId);
		return "editDish?faces-redirect=true";
	}

	public void mdNameChanged(ValueChangeEvent event) {
		menuDish.setName(event.getNewValue().toString());
	}

	public void mdPriceChanged(ValueChangeEvent event) {
		menuDish.setPrice(new BigDecimal(event.getNewValue().toString()));
	}

	public void mdDescriptionChanged(ValueChangeEvent event) {
		menuDish.setDescription(event.getNewValue().toString());

	}

	public void mdDishTypeChanged(ValueChangeEvent event) {
		menuDish.setDishType((boolean) event.getNewValue());

	}

	public void mdCategoryChanged(ValueChangeEvent event) {
		setDishCategoryId((int) event.getNewValue());
	}

	// image
	public void changeImagePrevious() {
		if (imageId <= 0) {
			imageId += imgList.size();
		} else {
			imageId--;
		}
		System.out.println(imageId);
	}

	public void changeImageNext() {
		if (imageId > imgList.size()) {
			imageId -= imgList.size();
		} else {
			imageId++;
		}
		System.out.println(imageId);
	}

	public List<MenuDish> getMenuByCategory(int categoryId) {
		return menuService.getMenu(categoryId);
	}

	public List<MenuDish> getOldDishes() {
		return menuService.findOldDishes();
	}

	public void deleteMenuDish(int menuDishId) {
		adminService.deleteMenuDish(menuDishId);
	}

	public String editDish() {
		adminService.editMenuDish(menuDish.getId(), menuDish.getName(),
				menuDish.getDescription(), menuDish.getPrice(),
				menuDish.isDishType(),
				menuService.findCategory(dishCategoryId), imageId);
		return "admin?faces-redirect=true";
	}

	public void addDish(int menuDishId) {
		adminService.addMenuDish(menuDishId);
	}

	// why it doesn't working??
	public void addDishMessage(int menuDishId) {
		FacesContext context = FacesContext.getCurrentInstance();
		for (MenuDish menuDish : getOldDishes()) {
			if (menuDish.getId() == menuDishId) {
				context.addMessage(null,
						new FacesMessage("" + menuDish.getName()
								+ " successfully added to menu"));
				System.out.println("ok");
			}
		}

	}

	public String goToCreatePage() {
		menuDish = new MenuDish();
		imgList=adminService.getImageList(1);
		imageId = 0;
		return "createMenuDish?faces-redirect=true";
	}

	public String createMenuDish() {
		adminService.createMenuDish(menuDish.getName(),
				menuDish.getDescription(), menuDish.getPrice(),
				menuDish.isDishType(),
				menuService.findCategory(dishCategoryId),
				adminService.findImageById(imageId));
		return "admin?faces-redirect=true";
	}

	public void onPreRenderView(ComponentSystemEvent event) {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();

		if (loginBean.getWorker() == null|| !("administrator".equals(this.loginBean.getWorker().getRole()))) {
			try {
				context.redirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
