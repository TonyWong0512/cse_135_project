package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class User {
	private int id;
	private String name;
	private String role;
	private short age;
	private String state;
	
	public User() {
		super();
	}

	public User(String name, String role, short age, String state) {
		this.name = name;
		this.role = role;
		this.age = age;
		this.state = state;
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

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	static public boolean isOwner(String role) throws IOException {
		if ((String) role != null) {
			if (role.contains("owner")) {
				System.out.println("user is owner");
				return true;
			}
			System.out.println("user is customer");
		} else {
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role
				+ ", age=" + age + ", state=" + state + "]";
	}
}
