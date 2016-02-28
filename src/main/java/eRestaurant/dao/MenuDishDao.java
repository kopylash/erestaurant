package eRestaurant.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import eRestaurant.entity.DishCategory;
import eRestaurant.entity.DishImage;
import eRestaurant.entity.MenuDish;

@Repository
public class MenuDishDao {
	@PersistenceContext
	private EntityManager em;

	public List<MenuDish> findAll(DishCategory category) {
		return em
				.createQuery(
						"Select md from MenuDish md where md.dishCategory.id=:catId AND md.deprecatedStatus=:status")
				.setParameter("catId", category.getId())
				.setParameter("status", false).getResultList();
	}

	public List<MenuDish> findDeprecated() {
		return em
				.createQuery(
						"Select md from MenuDish md where md.deprecatedStatus=:status")
				.setParameter("status", true).getResultList();
	}

	public MenuDish findById(int menuDishId) {
		return (MenuDish) em
				.createQuery(
						"Select md from MenuDish md where md.id=:menuDishId")
				.setParameter("menuDishId", menuDishId).getSingleResult();
	}

	public void addMenuDish(MenuDish menuDish) {
		em.persist(menuDish);
	}

	public void removeMenuDish(MenuDish menuDish) {
		menuDish.setDeprecatedStatus(true);
	}

	public void editMenuDish(MenuDish md, String name, String description,
			BigDecimal price, boolean dishType, DishCategory category,DishImage image) {
		md.setName(name);
		md.setDescription(description);
		md.setPrice(price);
		md.setDishType(dishType);
		md.setDeprecatedStatus(false);
		md.setDishCategory(category);
		md.setImage(image);
	}
}
