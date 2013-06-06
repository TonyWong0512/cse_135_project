package model;

public class Ordered {
	private int Id;
	private Product product;
	private int quantity;
	private int order_pk;
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrder_pk() {
		return order_pk;
	}

	public void setOrder_pk(int order_pk) {
		this.order_pk = order_pk;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
