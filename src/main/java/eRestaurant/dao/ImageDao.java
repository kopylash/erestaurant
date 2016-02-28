package eRestaurant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import eRestaurant.entity.DishImage;

@Repository
public class ImageDao {
	@PersistenceContext
	private EntityManager em;

	public List<DishImage> getImagesList() {
		return em.createQuery("select img from DishImage img").getResultList();
	}

	public DishImage findById(int imageId) {
		return (DishImage) em
				.createQuery(
						"select img from DishImage img where img.id=:imageId")
				.setParameter("imageId", imageId).getSingleResult();
	}

}
