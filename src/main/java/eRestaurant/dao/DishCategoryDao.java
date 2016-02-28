package eRestaurant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import eRestaurant.entity.DishCategory;

@Repository
public class DishCategoryDao {
	@PersistenceContext
	private EntityManager em;

	public List<DishCategory> getCategoryList() {
		return em.createQuery("select dc from DishCategory dc order by dc.id").getResultList();
	}

	public DishCategory findById(int categoryId) {
		return (DishCategory) em
				.createQuery(
						"select cat from DishCategory cat where cat.id=:categoryId")
				.setParameter("categoryId", categoryId).getSingleResult();
	}
}
