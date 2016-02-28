package eRestaurant.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import eRestaurant.dao.StaffDao;
import eRestaurant.entity.Staff;

@Named
public class StaffService {
	@Inject
	StaffDao staffDao;

	@Transactional
	public List<Staff> getStaffList() {
		return staffDao.findAll();
	}

	@Transactional
	public Staff findById(int staffId) {
		return staffDao.findById(staffId);
	}

	@Transactional
	public Staff login(String login, String password) {
		Staff worker = staffDao.findByLogin(login);
		if (password.equals(worker.getPassword())) {
			return worker;
		}
		return null;
	}

	@Transactional
	public boolean checkUniqueLogin(String login) {
		Staff s = staffDao.findByLogin(login);
		if (s.equals(null)) {
			return true;
		}
		return false;
	}

	@Transactional
	public void addStaff(Staff worker) {
		staffDao.addStaff(worker);
	}

	@Transactional
	public void editStaff(int staffId, String surname, String name,
			String login, String password, String role) {
		Staff worker = findById(staffId);
		staffDao.editStaff(worker, surname, name, login, password, role);
	}

	@Transactional
	public void deleteStaff(int staffId) {
		Staff worker = findById(staffId);
		if (!("system officer".equals(worker.getRole()))) {
			staffDao.deleteStaff(worker);
		}
	}

}
