package midEX2;

public class Candidate {
	private String id;
	private String fullName;
	private int age;
	private String linkCV;

	public Candidate(String id, String fullName, int age, String linkCV) {
		this.id = id;
		this.fullName = fullName;
		this.age = age;
		this.linkCV = linkCV;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLinkCV() {
		return linkCV;
	}

	public void setLinkCV(String linkCV) {
		this.linkCV = linkCV;
	}

}
