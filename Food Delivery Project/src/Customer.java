// The class  declaration and extends the CommonDetails class
public class Customer extends CommonDetails {

	// Customer attributes
	int orderNumber;
	String streetAddress;
	String emailAddress;

	// Customer constructor method
	public Customer(String name, String city, String contactNumber, int orderNumber, String streetAddress,
			String emailAddress) {

		super(name, city, contactNumber);
		this.orderNumber = orderNumber;
		this.streetAddress = streetAddress;
		this.emailAddress = emailAddress;
	}

	// A method that returns the order number of the customer
	public int getOrderNumber() {
		return orderNumber;
	}

	// A method that returns the street address of the customer
	public String getStreetAddress() {
		return streetAddress;
	}

	// A method that returns the email address of the customer
	public String getEmailAddress() {
		return emailAddress;
	}

}
