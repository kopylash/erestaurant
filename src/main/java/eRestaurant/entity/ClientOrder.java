package eRestaurant.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "client_order")
public class ClientOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int tableNumber;
	private Timestamp orderDate;
	private boolean relevance;
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private Collection<Dish> dishList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isRelevance() {
		return relevance;
	}

	public void setRelevance(boolean relevance) {
		this.relevance = relevance;
	}

	public Collection<Dish> getDishList() {
		return dishList;
	}

	public void setDishList(Collection<Dish> dishList) {
		this.dishList = dishList;
	}

	public BigDecimal calculateTotalPrice() {
		BigDecimal result = new BigDecimal(0.0);
		for (Dish d : dishList) {
			result.add(d.getmDish().getPrice()
					.multiply(new BigDecimal(d.getQuantity())));
		}
		return result;
	}
}
