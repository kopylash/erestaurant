package eRestaurant.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import eRestaurant.dao.ImageDao;
import eRestaurant.dao.MenuDishDao;
import eRestaurant.entity.DishCategory;
import eRestaurant.entity.DishImage;
import eRestaurant.entity.MenuDish;

@Named
public class AdminService {
	@Inject
	private MenuDishDao menuDishDao;
	@Inject
	private ImageDao imageDao;

	@Transactional
	public void deleteMenuDish(int menuDishId) {
		MenuDish md = menuDishDao.findById(menuDishId);
		menuDishDao.removeMenuDish(md);
	}

	@Transactional
	public void addMenuDish(int menuDishId) {
		MenuDish md = menuDishDao.findById(menuDishId);
		md.setDeprecatedStatus(false);
	}

	@Transactional
	public void createMenuDish(String name, String description,
			BigDecimal price, boolean dishType, DishCategory category,
			DishImage image) {
		MenuDish md = new MenuDish();
		md.setName(name);
		md.setDescription(description);
		md.setPrice(price);
		md.setDishType(dishType);
		md.setDeprecatedStatus(false);
		md.setDishCategory(category);
		md.setImage(image);
		menuDishDao.addMenuDish(md);
	}

	@Transactional
	public void editMenuDish(int menuDishId, String name, String description,
			BigDecimal price, boolean dishType, DishCategory category,
			int imageId) {
		MenuDish md = menuDishDao.findById(menuDishId);
		DishImage img = new DishImage();
		if (imageId == 0) {
			img = md.getImage();
		} else {
			img = imageDao.findById(imageId);
		}
		menuDishDao.editMenuDish(md, name, description, price, dishType,
				category, img);
	}

	@Transactional
	public List<DishImage> getImageList(int menuDishId) {
		List<DishImage> imgList = Collections.emptyList();
		imgList = imageDao.getImagesList();
		DishImage img = findImageById(menuDishDao.findById(menuDishId)
				.getImage().getId());
		imgList.remove(img);
		imgList.add(0, img);
		return imgList;

	}

	@Transactional
	public DishImage findImageById(int imageId) {
		return imageDao.findById(imageId);
	}
}
