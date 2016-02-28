package eRestaurant.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import eRestaurant.entity.ClientOrder;

@Repository
public class ClientOrderDao {
	@PersistenceContext
	private EntityManager em;

	public ClientOrder findById(int clientOrderId) {
		return (ClientOrder) em
				.createQuery(
						"select o from ClientOrder o where o.id=:clientOrderId")
				.setParameter("clientOrderId", clientOrderId).getSingleResult();
	}
	
	public void updateRelevance(ClientOrder order) {
		order.setRelevance(false);
	}
}
