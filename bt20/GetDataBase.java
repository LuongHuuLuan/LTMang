package bt20;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetDataBase {
	public static ArrayList<Sinhvien> findByName(String name) {
		ArrayList<Sinhvien> result = new ArrayList<>();
		Connection conn = Connect.getIntance().getConnection();
		String sql = "select * from SINHVIEN where HOTEN like ?";
		try {
			PreparedStatement preStament = conn.prepareStatement(sql);
			preStament.setString(1, name);
			ResultSet resultSet = preStament.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String mssv = resultSet.getString("MSSV");
				String hoten = resultSet.getString("hoten");
				int tuoi = resultSet.getInt("TUOI");
				double diem = resultSet.getDouble("DIEM");
				Sinhvien s = new Sinhvien(mssv, hoten, tuoi, diem);
				s.setID(id);
				result.add(s);
			}
			resultSet.close();
			preStament.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<Sinhvien> findByAge(int Age) {
		ArrayList<Sinhvien> result = new ArrayList<>();
		Connection conn = Connect.getIntance().getConnection();
		String sql = "select * from SINHVIEN where TUOI = ?";
		try {
			PreparedStatement preStament = conn.prepareStatement(sql);
			preStament.setInt(1, Age);
			ResultSet resultSet = preStament.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String mssv = resultSet.getString("MSSV");
				String hoten = resultSet.getString("hoten");
				int tuoi = resultSet.getInt("TUOI");
				double diem = resultSet.getDouble("DIEM");
				Sinhvien s = new Sinhvien(mssv, hoten, tuoi, diem);
				s.setID(id);
				result.add(s);
			}
			resultSet.close();
			preStament.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<Sinhvien> findByScore(double Score) {
		ArrayList<Sinhvien> result = new ArrayList<>();
		Connection conn = Connect.getIntance().getConnection();
		String sql = "select * from SINHVIEN where DIEM = ?";
		try {
			PreparedStatement preStament = conn.prepareStatement(sql);
			preStament.setDouble(1, Score);
			ResultSet resultSet = preStament.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String mssv = resultSet.getString("MSSV");
				String hoten = resultSet.getString("hoten");
				int tuoi = resultSet.getInt("TUOI");
				double diem = resultSet.getDouble("DIEM");
				Sinhvien s = new Sinhvien(mssv, hoten, tuoi, diem);
				s.setID(id);
				result.add(s);
			}
			resultSet.close();
			preStament.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
//		GetDataBase getData = new GetDataBase();
//		System.out.println(getData.findByName("Nguyen Van A"));
//		System.out.println(getData.findByAge(20));
//		System.out.println(getData.findByScore(10));
	}
}
