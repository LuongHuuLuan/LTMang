package bt20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static Connect instance;
	private static Connection conn;
	private String classForName;
	private String url;

	private Connect() {
		this.classForName = "net.ucanaccess.jdbc.UcanaccessDriver";
		this.url = "jdbc:ucanaccess://AccessDB\\database.accdb;memory=true";
		try {
			Class.forName(this.classForName);
			conn = DriverManager.getConnection(this.url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connect getIntance() {
		if (instance == null) {
			return new Connect();
		}
		return instance;
	}
	public Connection getConnection() {
		return conn;
	}
}
