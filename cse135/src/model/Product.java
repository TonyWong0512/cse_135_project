package model;

import java.math.BigDecimal;

public class Product {
	private int id;
	private String name;
	private String SKU;
	private BigDecimal price;
	private int category;
	
	public Product() {}
	
	public Product(String name, String SKU, double price, int category) {
		this.name = name;
		this.SKU = SKU;
		this.price = new BigDecimal(price);
		this.category = category;
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

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
