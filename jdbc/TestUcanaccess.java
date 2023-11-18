package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUcanaccess {
	public static void main(String[] args) {
		try {
			String classForName = "net.ucanaccess.jdbc.UcanaccessDriver";
			String url = "jdbc:ucanaccess://src\\jdbc\\db\\database.accdb;memory=true";
			Connection conn = connect.getIntance(classForName, url);
			Statement stat = conn.createStatement();

			Sinhvien sv5 = new Sinhvien("19130135", "Tran Thi E", 20, 8);

//			insert(conn, sv5);
			delete(conn, "19130135");
//			sv5.setTuoi(22);
//			update(conn, sv5);

			String query = "select * from SINHVIEN";
			ResultSet rs = stat.executeQuery(query);
			printAll(rs);
			
			rs.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printAll(ResultSet rs) throws SQLException {
		while (rs.next()) {
			String s = "ID: " + rs.getInt("ID") + ", MSSV: " + rs.getString("MSSV") + ", HOTEN: "
					+ rs.getString("HOTEN") + ", TUOI: " + rs.getInt("TUOI") + ", DIEM: " + rs.getDouble("DIEM");
			System.out.println(s);
		}
	}

	public static int insert(Connection conn, Sinhvien s) throws SQLException {
		String query = "insert into SINHVIEN(MSSV, HOTEN, TUOI, DIEM) VALUES(?, ?, ?, ?)";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, s.getMssv());
		stat.setString(2, s.getHoten());
		stat.setInt(3, s.getTuoi());
		stat.setDouble(4, s.getDiem());
		int rows = stat.executeUpdate();
		return rows;
	}

	public static int delete(Connection conn, String mssv) throws SQLException {
		String query = "delete SINHVIEN where MSSV = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, mssv);
		int rows = stat.executeUpdate();
		return rows;
	}

	public static int update(Connection conn, Sinhvien s) throws SQLException {
		String query = "update SINHVIEN set HOTEN = ?, TUOI = ?, DIEM = ? where MSSV = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, s.getHoten());
		stat.setInt(2, s.getTuoi());
		stat.setDouble(3, s.getDiem());
		stat.setString(4, s.getMssv());
		int rows = stat.executeUpdate();
		return rows;
	}
	
}
