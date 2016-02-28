package eRestaurant.entity;

public class PendingDish {
	private int id;
	private String name;
	private java.sql.Timestamp time;

	public PendingDish() {

	}

	public PendingDish(int id, String name, java.sql.Timestamp time) {
		this.id=id;
		this.name = name;
		this.time = time;
	}

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

	public java.sql.Timestamp getTime() {
		return time;
	}

	public void setTime(java.sql.Timestamp time) {
		this.time = time;
	}

}
