package eRestaurant.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import eRestaurant.dao.DishDao;
import eRestaurant.dao.MenuDishDao;
import eRestaurant.entity.ClientOrder;
import eRestaurant.entity.Dish;
import eRestaurant.entity.MenuDish;

@Named
public class OrderService {
	@Inject
	private MenuDishDao menuDishDao;
	@Inject
	private DishDao dishDao;

	@Transactional
	public void submitOrder(ClientOrder order, List<Dish> dishList,
			int tableNumber) {
		order.setRelevance(true);
		order.setTableNumber(tableNumber);
		order.setDishList(dishList);
		for (Dish d : order.getDishList()) {
			d.setOrder(order);
		}
		for (Dish d : order.getDishList()) {
			dishDao.addDish(d);
		}
	}

	@Transactional
	public MenuDish findMenuDish(int menuDishId) {
		return menuDishDao.findById(menuDishId);
	}

}
