package model;

public class Category implements Comparable {

	private int id;
	private String name;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int categoryid) {
		this.id = categoryid;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name
				+ ", description=" + description + "]";
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Category c = (Category) o;
		return this.getName().compareTo(c.getName());
	}

}
