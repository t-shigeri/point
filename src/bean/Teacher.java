package bean;

public class Teacher extends User {
	private String id;
	private String password;
	private String name;
	private School school;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return School;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}
