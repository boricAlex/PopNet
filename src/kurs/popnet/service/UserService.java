package kurs.popnet.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kurs.popnet.beans.UserBean;

public class UserService extends Service {

	public UserService(String url, String usr, String psw) throws SQLException {
		super(url, usr, psw);
	}

	
	
	public UserBean logService(String name, String pswd) {
		
		UserBean ub = new UserBean();
		
		String sql = "SELECT userId, uname, password, email, createdate FROM users WHERE uname = ? AND password = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setString(2, pswd);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ub.setUserId(rs.getInt(1));
				ub.setName(rs.getString(2));
				ub.setPassword(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setCreatedate(rs.getDate(5));
			}else {
				return null;
				
			}
			return ub;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return ub;
	}
	
	public static void main (String[] args) {
		try {
			UserService us = new UserService("jdbc:mysql://localhost/popnet", "dev", "mysql");
			us.createConnection(true);
			UserBean ubn = us.logService("admin", "adminpass");
			System.out.println(ubn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
