package eRestaurant.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import eRestaurant.entity.Dish;

@Repository
public class DishDao {
	@PersistenceContext
	private EntityManager em;

	public void addDish(Dish d) {
		em.persist(d);
	}

	public Dish findById(int dishId) {
		return (Dish) em.createQuery("select d from Dish d where d.id=:dishId")
				.setParameter("dishId", dishId).getSingleResult();
	}

	public void markAsDone(int dishId) {
		Dish d=findById(dishId);
		d.setDoneStatus(true);
	}
}
