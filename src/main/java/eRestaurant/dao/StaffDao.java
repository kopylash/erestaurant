package eRestaurant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import eRestaurant.entity.Staff;

@Repository
public class StaffDao {
	@PersistenceContext
	private EntityManager em;

	public void addStaff(Staff worker) {
		em.persist(worker);
	}

	public void editStaff(Staff worker, String surname, String name,
			String login, String password,String role) {
		worker.setName(name);
		worker.setSurname(surname);
		worker.setRole(role);
		worker.setLogin(login);
		worker.setPassword(password);
	}

	public void deleteStaff(Staff worker) {
		em.remove(worker);
	}

	public List<Staff> findAll() {
		return em.createQuery("select s from Staff s").getResultList();
	}

	public Staff findById(int staffId) {
		return (Staff) em
				.createQuery("select s from Staff s where s.id=:staffId")
				.setParameter("staffId", staffId).getSingleResult();
	}

	public Staff findByLogin(String login) {
		return (Staff) em
				.createQuery("select s from Staff s where s.login=:staffLogin")
				.setParameter("staffLogin", login).getSingleResult();
	}
}
