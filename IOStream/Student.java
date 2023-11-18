package IOStream;

public class Student {
	private String name;
	private String birthDay;
	private String mssv;
	private double averageScore;

	public Student(String name, String birthDay, String mssv, double averageScore) {
		this.name = name;
		this.birthDay = birthDay;
		this.mssv = mssv;
		this.averageScore = averageScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", birthDay=" + birthDay + ", mssv=" + mssv + ", averageScore=" + averageScore
				+ "]";
	}

}
