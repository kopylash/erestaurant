package eRestaurant.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import eRestaurant.dao.DishDao;
import eRestaurant.dao.PendingDishDto;
import eRestaurant.entity.PendingDish;

@Named
public class KitchenService {
	@Inject
	PendingDishDto pendingDishDto;
	@Inject 
	DishDao dishDao;
		
	@Transactional
	public List<PendingDish> getPendingList() {
		return pendingDishDto.getPendingList();
	}
	
	@Transactional
	public List<PendingDish> getPreparedDishes() {
		Calendar c =Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Timestamp time = new Timestamp(c.getTimeInMillis());
	//	System.out.println("this is spartaaaaaaaaaaaaaa "+time);
		return pendingDishDto.getPreparedDishes(time);
	}
	
	@Transactional
	public void markDishAsDone(int dishId) {
		dishDao.markAsDone(dishId);
	}
	
	
}
