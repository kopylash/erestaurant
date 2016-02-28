package eRestaurant.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import eRestaurant.entity.ClientOrder;
import eRestaurant.entity.Dish;

import eRestaurant.service.OrderService;

@Named
@Scope("session")
public class ShoppingCartBean {
	@Inject
	private OrderService orderService;
	private int tableNumber = 1;
	private int quantity = 1;
	private List<Dish> dishList;
	private BigDecimal orderSum;

	public ShoppingCartBean() {
		dishList = new ArrayList<>();
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Dish> getDishList() {
		return dishList;
	}

	public void setDishList(List<Dish> dishList) {
		this.dishList = dishList;
	}

	public BigDecimal getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(BigDecimal orderSum) {
		this.orderSum = orderSum;
	}

	public void removeDish(int menuDishId) {
		Iterator<Dish> iter = null;
		for (iter = dishList.iterator(); iter.hasNext();) {
			if (iter.next().getmDish().getId() == menuDishId) {
				iter.remove();
			}
		}
		calculateOrderSum();
	}

	public void calculateOrderSum() {
		double result = 0.0;
		for (Dish d : dishList) {
			result += d.getTotalPrice().doubleValue();

		}
		setOrderSum(new BigDecimal(result));

	}

	public void calculateDishSum() {
		BigDecimal sum = BigDecimal.ZERO;
		for (Dish d : dishList) {
			sum = BigDecimal.ZERO;
			sum = (d.getmDish().getPrice().multiply(new BigDecimal(d
					.getQuantity())));
			d.setTotalPrice(sum);
			// System.out.println(sum);
		}
		calculateOrderSum();
	}

	public String cancelOrder() {
		dishList.clear();
		orderSum = BigDecimal.ZERO;
		return "Hello?faces-redirect=true";
	}

	private boolean checkExistingDish(Dish dish, List<Dish> list) {
		for (Dish d : list) {
			if (d.getmDish().getId() == dish.getmDish().getId()) {
				d.setQuantity(d.getQuantity() + 1);
				return false;
			}
		}
		return true;
	}

	public void addDish(int menuDishId) {
		Dish d = new Dish();
		d.setmDish(orderService.findMenuDish(menuDishId));
		if (checkExistingDish(d, dishList)) {
			dishList.add(d);
		}
	}

	public String submitOrder() {
		ClientOrder order = new ClientOrder();
		orderService.submitOrder(order, dishList, tableNumber);
		dishList.clear();
		orderSum = BigDecimal.ZERO;
		return "Hello?faces-redirect=true";
	}
}
