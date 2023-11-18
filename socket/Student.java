package socket;

public class Student {
	private String studentCode;
	private String name;
	private int age;
	private double score;

	public Student(String studentCode, String name, int age, double score) {
		this.studentCode = studentCode;
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [studentCode=" + studentCode + ", name=" + name + ", age=" + age + ", score=" + score + "]";
	}

}
