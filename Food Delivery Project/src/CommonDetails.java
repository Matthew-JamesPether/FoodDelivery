// The class  declaration
public class CommonDetails {

	// CommonDetails attributes
	String name;
	String city;
	String contactNumber;

	// CommonDetails constructor method
	public CommonDetails(String name, String city, String contactNumber) {

		this.name = name;
		this.city = city;
		this.contactNumber = contactNumber;
	}

	// A method that returns the name of the constructor
	public String getName() {
		return name;
	}

	// A method that returns the city of the constructor
	public String getCity() {
		return city;
	}

	// A method that returns the contact number of the constructor
	public String getContactNumber() {
		return contactNumber;
	}
}
