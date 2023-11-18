package jdbc;

public class Sinhvien {
	private int ID;
	private String Mssv;
	private String Hoten;
	private int Tuoi;
	private double diem;

	public Sinhvien(String mssv, String hoten, int tuoi, double diem) {
		super();
		Mssv = mssv;
		Hoten = hoten;
		Tuoi = tuoi;
		this.diem = diem;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMssv() {
		return Mssv;
	}

	public void setMssv(String mssv) {
		Mssv = mssv;
	}

	public String getHoten() {
		return Hoten;
	}

	public void setHoten(String hoten) {
		Hoten = hoten;
	}

	public int getTuoi() {
		return Tuoi;
	}

	public void setTuoi(int tuoi) {
		Tuoi = tuoi;
	}

	public double getDiem() {
		return diem;
	}

	public void setDiem(double diem) {
		this.diem = diem;
	}

	@Override
	public String toString() {
		return "ID: " + ID + ", MSSV: " + Mssv + ", HOTEN: " + Hoten + ", TUOI: " + Tuoi + ", DIEM: " + diem;
	}
}
