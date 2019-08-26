package kurs.popnet.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Service {
	
	static {
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        	ex.printStackTrace();
        }
	}

	protected String dbUrl;
	protected String user;
	protected String pswd;

	protected Connection conn;

	public Service(String url, String usr, String psw) throws SQLException {
		dbUrl = url;
		user = usr;
		pswd = psw;
	}
	
	public void createConnection(boolean autoCommit) throws SQLException {
		conn = DriverManager.getConnection(dbUrl, user, pswd);
		conn.setAutoCommit(autoCommit);
	}
	
	public void commit() throws SQLException {
		conn.commit();
	}
	
	public void rollback() throws SQLException {
		conn.rollback();
	}}