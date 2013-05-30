package model;

public class SalesByCustomer {
	private int customer_pk;
	private String season;
	private long sales;

	public int getCustomer() {
		return customer_pk;
	}

	public void setCustomer(int customer_pk) {
		this.customer_pk = customer_pk;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public long getSales() {
		return sales;
	}

	public void setSales(long sales) {
		this.sales = sales;
	}

}
