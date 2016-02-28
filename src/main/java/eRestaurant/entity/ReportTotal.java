package eRestaurant.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ReportTotal {

	private Timestamp date;
	private long orderCount;
	private BigDecimal totalSum;

	public ReportTotal() {

	}

	public ReportTotal(java.sql.Timestamp date, long orderCount, BigDecimal totalSum) {
		this.date = date;
		this.orderCount = orderCount;
		this.totalSum = totalSum;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}

	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}
