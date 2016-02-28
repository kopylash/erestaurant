package eRestaurant.service;

import java.util.Collections;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import eRestaurant.dao.ReportDto;
import eRestaurant.entity.Dish;
import eRestaurant.entity.ReportTotal;
import eRestaurant.entity.ReportByMenu;

@Named
public class ReportService {
	@Inject
	private ReportDto reportDto;

	@Transactional
	public List<ReportTotal> getTotalReport(Date startDate, Date finishDate) {
		ArrayList<ReportTotal> reportList = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		Date tempDate = null;
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_YEAR, 1);
		tempDate = c.getTime();
		while (startDate.before(finishDate)) {
			ReportTotal report = reportDto.getTotalReport(
					new java.sql.Timestamp(startDate.getTime()),
					new java.sql.Timestamp(tempDate.getTime()));
			if (report.getOrderCount() != 0) {
				reportList.add(report);
			}
			c.setTime(tempDate);
			startDate = c.getTime();
			c.add(Calendar.DAY_OF_YEAR, 1);
			tempDate = c.getTime();

		}
		return reportList;
	}

	public List<ReportByMenu> getReportByMenuItems(Date startDate,
			Date finishDate) {
		List<Dish> dishList = new ArrayList<>();
		List<Dish> tempList = null;
		ArrayList<ReportByMenu> reportList = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		Date tempDate = null;
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_YEAR, 1);
		tempDate = c.getTime();
		while (startDate.before(finishDate)) {
			dishList = new ArrayList<>();

			tempList = reportDto.getDishesByDate(new java.sql.Timestamp(
					startDate.getTime()),
					new java.sql.Timestamp(tempDate.getTime()));
			filterDishList(tempList, dishList);
			Collections.sort(dishList);
			ReportByMenu report = new ReportByMenu(new java.sql.Timestamp(
					startDate.getTime()), dishList);
			if (!(report.getDishList().isEmpty())) {
				reportList.add(report);
			}

			c.setTime(tempDate);
			startDate = c.getTime();
			c.add(Calendar.DAY_OF_YEAR, 1);
			tempDate = c.getTime();
			tempList.clear();
		}
		return reportList;
	}

	private void filterDishList(List<Dish> inputList, List<Dish> outputList) {
		for (Dish d : inputList) {
			if (checkExistingDish(d, outputList)) {
				outputList.add(d);
			}
		}
	}

	private boolean checkExistingDish(Dish dish, List<Dish> list) {
		for (Dish d : list) {
			if (d.getmDish().getId() == dish.getmDish().getId()) {
				d.setQuantity(d.getQuantity() + dish.getQuantity());
				d.setTotalPrice(d.getTotalPrice().add(dish.getTotalPrice()));
				return false;
			}
		}
		return true;
	}
}
