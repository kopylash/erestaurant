package eRestaurant.entity;

import java.sql.Timestamp;
import java.util.List;

public class ReportByMenu {
	private Timestamp date;
	private List<Dish> dishList;

	public ReportByMenu() {

	}

	public ReportByMenu(Timestamp date, List<Dish> dishList) {
		this.date = date;
		this.dishList = dishList;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<Dish> getDishList() {
		return dishList;
	}

	public void setDishList(List<Dish> dishList) {
		this.dishList = dishList;
	}

}
