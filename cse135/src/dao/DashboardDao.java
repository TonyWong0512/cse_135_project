package dao;

import java.sql.Connection;

import util.DbUtil;

public class DashboardDao {
	private Connection connection;

	public DashboardDao() {
		connection = DbUtil.getConnection();
	}
	
	private void getCustomersFilter(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private void getStatesFilter(String[] args) {
		// TODO Auto-generated method stub

	}
}
