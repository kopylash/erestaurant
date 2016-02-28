package eRestaurant.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import eRestaurant.dao.DishCategoryDao;
import eRestaurant.dao.MenuDishDao;
import eRestaurant.entity.DishCategory;
import eRestaurant.entity.MenuDish;

@Named
public class MenuService {
	@Inject
	private DishCategoryDao categoryDao;
	@Inject
	private MenuDishDao menuDishDao;

	@Transactional
	public List<MenuDish> getMenu(int categoryId) {
		DishCategory cat = categoryDao.findById(categoryId);
		return menuDishDao.findAll(cat);
	}
	@Transactional
	public DishCategory findCategory(int categoryId) {
		return categoryDao.findById(categoryId);
	}
	
	@Transactional
	public List<DishCategory> getCategories() {
		return categoryDao.getCategoryList();
	}
	
	@Transactional
	public List<MenuDish> findOldDishes() {
		return menuDishDao.findDeprecated();
	}
	@Transactional
	public MenuDish findMenuDishById(int menuDishId) {
		return menuDishDao.findById(menuDishId);
	}
}
