package eRestaurant.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "menu_dish")
public class MenuDish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private BigDecimal price;
	private boolean dishType;
	private boolean deprecatedStatus;
	@OneToOne
	@JoinColumn(name = "imageID")
	private DishImage image;
	@OneToOne
	@JoinColumn(name = "categoryId")
	private DishCategory dishCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isDishType() {
		return dishType;
	}

	public void setDishType(boolean dishType) {
		this.dishType = dishType;
	}

	public boolean isDeprecatedStatus() {
		return deprecatedStatus;
	}

	public void setDeprecatedStatus(boolean deprecatedStatus) {
		this.deprecatedStatus = deprecatedStatus;
	}

	public DishCategory getDishCategory() {
		return dishCategory;
	}

	public void setDishCategory(DishCategory dc) {
		this.dishCategory = dc;
	}

	@Override
	public String toString() {
		String str = null;
		str = "" + getName() + "   Price: " + price + "$";
		return str;

	}

	public DishImage getImage() {
		return image;
	}

	public void setImage(DishImage image) {
		this.image = image;
	}

	

}
