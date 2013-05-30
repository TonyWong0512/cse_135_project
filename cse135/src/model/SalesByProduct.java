package model;

public class SalesByProduct {
	private Product product;
	private long sales;
	private User user;
	private String state;
	private String season;

	public SalesByProduct() {

	}

	public SalesByProduct(Product product, long sales, User user, String state) {
		super();
		this.product = product;
		this.sales = sales;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
				+ ", user=" + user + ", state=" + state + ", season=" + season
				+ "]";
	}
	
	

}
