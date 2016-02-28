package eRestaurant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import eRestaurant.entity.PendingDish;

@Repository
public class PendingDishDto {
	@PersistenceContext
	private EntityManager em;

	public List<PendingDish> getPendingList() {
		return (List<PendingDish>) em
				.createQuery(
						"Select new eRestaurant.entity.PendingDish(d.id ,d.mDish.name, d.order.orderDate) from Dish d"
								+ " where d.mDish.dishType=:dishType AND d.order.relevance=:relevance AND d.doneStatus=:status ORDER BY d.order.orderDate ASC")
				.setParameter("relevance", true).setParameter("dishType", true)
				.setParameter("status", false).getResultList();
	}

	public List<PendingDish> getPreparedDishes(java.sql.Timestamp time) {
		return em
				.createQuery(
						"select new eRestaurant.entity.PendingDish(d.id ,d.mDish.name, d.order.orderDate) from Dish d"
								+ " where d.mDish.dishType=:dishType AND d.order.relevance=:relevance AND d.doneStatus=:status AND d.order.orderDate>=:date ORDER BY d.order.id ASC")
				.setParameter("relevance", true).setParameter("dishType", true)
				.setParameter("date", time).setParameter("status", true)
				.getResultList();
	}
}
