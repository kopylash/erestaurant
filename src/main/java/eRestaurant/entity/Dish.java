package eRestaurant.entity;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "dish")
public class Dish implements Comparable<Dish> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantity = 1;
	private BigDecimal totalPrice;
	private boolean doneStatus;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "orderId")
	private ClientOrder order;
	@OneToOne
	@JoinColumn(name = "mdishId")
	private MenuDish mDish;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isDoneStatus() {
		return doneStatus;
	}

	public void setDoneStatus(boolean doneStatus) {
		this.doneStatus = doneStatus;
	}

	public ClientOrder getOrder() {
		return order;
	}

	public void setOrder(ClientOrder order) {
		this.order = order;
	}

	public MenuDish getmDish() {
		return mDish;
	}

	public void setmDish(MenuDish mDish) {
		this.mDish = mDish;
	}

	//decsending sort
	@Override
	public int compareTo(Dish o) {
		if (this.getQuantity() > o.getQuantity()) {
			return -1;
		} else {
			if (this.getQuantity() == o.getQuantity()) {
				return 0;
			}
		}
		return 1;

	}

}
