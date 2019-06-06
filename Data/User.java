package Data;

public class User {
	private int cpf;
	private String name;
	private String email;
	private String password;
	private String contactNumber;
	private String address;
	private String birthday;
	private String level;

	public User(int cpf, String name, String email, String password, String contactNumber, String address, String birthday, String level) {
		this.cpf = cpf;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.address = address;
		this.birthday = birthday;
		this.level = level;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public int getCpf() {
		return cpf;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	} 

	public String getPassword() {
		return password;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setDate(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}
}
