import java.io.Serializable;

public class Person implements Serializable{

	private String name;
	private String phoneNumber;
	private String dateOfBirth;
	private String email;
	
	public Person() {
		super();
	}

	public Person(String name, String phoneNumber, String dateOfBirth, String email) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nPhone Number: " + phoneNumber + 
				"\nDate of Birth: " + dateOfBirth + "\nEmail: " + email + "\n";
	}
	
	
	
}
