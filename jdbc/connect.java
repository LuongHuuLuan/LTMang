package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {
	private static Connection conn;
	private String classForName;
	private String url;

	private connect(String classForName, String url) {
		this.classForName = classForName;
		this.url = url;
		try {
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Class.forName(this.classForName);
//			String url = "jdbc:ucanaccess://src\\jdbc\\db\\database.accdb;memory=true";
			conn = DriverManager.getConnection(this.url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getIntance(String classForName, String url) {
		if (conn == null) {
			new connect(classForName, url);
		}
		return conn;
	}
	
	
	
	
	
	
	
	
}
