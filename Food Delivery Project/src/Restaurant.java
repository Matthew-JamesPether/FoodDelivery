
//The class  declaration and extends the CommonDetails class
public class Restaurant extends CommonDetails {

	// Restaurant attributes
	String specialInstructions;

	// Customer constructor method
	public Restaurant(String name, String city, String contactNumber, String specialInstructions) {

		super(name, city, contactNumber);
		this.specialInstructions = specialInstructions;
	}

	// A method that returns the special instructions for the restaurant
	public String getSpecialInstructions() {
		return specialInstructions;
	}

	// Gets splits the value received into an array and returns a specific value
	public String getNumberOfMeal(String mealNum) {
		String arrayMeal[] = mealNum.split(",");

		return arrayMeal[1];
	}

	// Gets splits the value received into an array and returns a specific value
	public String getRestaurantOrder(String mealOrder) {
		String arrayMeal[] = mealOrder.split(",");

		return arrayMeal[0];
	}

	// Gets splits the value received into an array and returns a specific value
	public String getPrice(String mealPrice) {
		String arrayMeal[] = mealPrice.split(",");

		return arrayMeal[2] + ".00";
	}
}
