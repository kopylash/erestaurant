package eRestaurant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import eRestaurant.entity.Dish;
import eRestaurant.entity.ReportTotal;
import eRestaurant.entity.ReportByMenu;

;

@Repository
public class ReportDto {
	@PersistenceContext
	private EntityManager em;

	public ReportTotal getTotalReport(java.sql.Timestamp startDate,
			java.sql.Timestamp finishDate) {
		return (ReportTotal) em
				.createQuery(
						"select new eRestaurant.entity.ReportTotal(d.order.orderDate,count(distinct d.order.id),sum(d.totalPrice)) from Dish d"
								+ " where d.order.orderDate BETWEEN :startDate AND :finishDate")
				.setParameter("startDate", startDate)
				.setParameter("finishDate", finishDate).getSingleResult();
	}

	public List<Dish> getDishesByDate(java.sql.Timestamp startDate,
			java.sql.Timestamp finishDate) {
		return em
				.createQuery(
						"select d from Dish d where d.order.orderDate BETWEEN :startDate AND :finishDate")
				.setParameter("startDate", startDate)
				.setParameter("finishDate", finishDate).getResultList();
	}
}
