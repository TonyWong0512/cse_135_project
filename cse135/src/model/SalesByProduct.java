package model;

public class SalesByProduct {
	private Product product;
	private long sales;
	private String customer;
	private String state;
	private String season;

	public SalesByProduct() {

	}

	public SalesByProduct(Product product, long sales, String user, String state) {
		super();
		this.product = product;
		this.sales = sales;
		this.customer = user;
		this.state = state;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getSales() {
		return sales;
	}

	public void setSales(long sales) {
		this.sales = sales;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String toString() {
		return "SalesByProduct [product=" + product + ", sales=" + sales
				+ ", user=" + customer + ", state=" + state + ", season=" + season
				+ "]";
	}
	
	

}
