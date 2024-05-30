
//Imports the java utilities
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

//The class  declaration
public class Main {

	public static ArrayList<String> listOfMeals = new ArrayList<>();

	// The main method declaration
	public static void main(String[] args) throws FileNotFoundException {

		// Declaring variables for later use
		String bestDriver = null;
		int totalAmount = 0;

		// Scans what the user entered into the console
		try (Scanner scanner = new Scanner(System.in)) {

			// Capture details the customer inputed
			System.out.println("Enter restaurant name: ");
			String restaurantName = scanner.nextLine();
			System.out.println("Enter the city you stay in: ");
			String customerCity = scanner.nextLine();
			System.out.println("Enter customer name: ");
			String customerName = scanner.nextLine();
			// Calls a method to retrieve the valid contact number
			String customerContactNum = getValidContact(scanner);
			// Calls a method to retrieve the valid street address
			String customerStreetAddress = getValidStreet(scanner);
			// Calls a method to retrieve the valid email
			String customerEmailAddress = getValidEmail(scanner);
			// Calls a method that stores the users order and retrieves the total amount
			totalAmount = extracted(scanner, totalAmount);
			System.out.println("Any special instructions: ");
			String restaurantInstructions = scanner.nextLine();

			// Closes scanner
			scanner.close();

			// Creates the Customer and Restaurant methods
			// in order to create a Customer and Restaurant object
			Customer customer = new Customer(customerName, customerCity, customerContactNum, 1234,
					customerStreetAddress, customerEmailAddress);
			Restaurant restaurant = new Restaurant(restaurantName, customerCity, "075 330 2020",
					restaurantInstructions);

			// Try to see if the file can be found
			try {

				int smallestLoad = Integer.MAX_VALUE;

				// Creating File instance to reference driver file
				File driversFile = new File("drivers.txt");

				// Creating Scanner instance to read driversFile
				Scanner driversScan = new Scanner(driversFile);

				// loops through each line of the file
				while (driversScan.hasNextLine()) {
					String line = driversScan.nextLine();

					// splits the line into an array using ", "
					String arrayLine[] = line.split(", ");

					// Checks to see which driver matches the city entered
					if (arrayLine[1].equals(restaurant.getCity())) {

						// stores each the drives load into a variable
						int load = Integer.parseInt(arrayLine[2]);

						// compares the appropriate drivers load and
						// stores the one with the smallest load
						if (load < smallestLoad) {
							smallestLoad = load;
							bestDriver = arrayLine[0];
						}
					}
				}

				// closes scan file
				driversScan.close();
			}
			// Catches the error if the file is not found
			catch (FileNotFoundException e) {
				System.out.println("File not found");
			}

			// Calls a method that writes a invoice
			invoice(bestDriver, totalAmount, customer, restaurant);

		}
		// Catches incorrect string format converting to integer
		// and displays where it occurred
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	// A method when called stores the users order and returns the total cost
	private static int extracted(Scanner scanner, int totalAmount) {

		// Declares variables
		String restaurantOrder = "";
		int numPos = 0;
		Random random = new Random();

		// if addMore is true repeat the output messages
		while (!restaurantOrder.equals("0")) {

			System.out.println("What would you like to order (enter '0' to exit): ");
			restaurantOrder = scanner.nextLine();

			// if the use does not enter '0' execute the following instructions
			if (!restaurantOrder.equals("0")) {

				// randomly generates a number between 30 and 130
				int price = random.nextInt(100) + 30;

				System.out.println("The price will be: R" + price + ".00");
				System.out.println("How many would you like to order: ");
				String numberOfOrders = scanner.nextLine();

				// Stores the relevant meal information into an array
				listOfMeals.add(numPos, restaurantOrder + "," + numberOfOrders + "," + price);

				// calculates the total price of all the meals
				totalAmount = totalAmount + (Integer.parseInt(numberOfOrders) * price);

				numPos++;
			}
			// if user enters 0 breaks the loop
			else {
				break;
			}
		}
		// returns the total amount
		return totalAmount;
	}

	// A method when called returns the valid contact number
	private static String getValidContact(Scanner scanner) {

		// declares the variables
		String customerContactNum = "";
		boolean contactBoolean = false;

		// Loops the code if the contact number does not have the required length or
		// does not contain only letters
		while (!contactBoolean || customerContactNum.length() < 10 || customerContactNum.length() > 10) {
			System.out.println("Enter customers contact number: ");
			customerContactNum = scanner.nextLine();

			// Calls a method to valid if the variable is only numbers
			contactBoolean = isAllNumbers(customerContactNum);

			// Displays what the input requires
			if (!contactBoolean) {
				System.out.println("Please enter only numbers without any spaces: ");
			} else if (customerContactNum.length() < 10 || customerContactNum.length() > 10) {
				System.out.println("Please enter only 10 numbers: ");
			}
		}

		// returns the customer contact number
		return customerContactNum;
	}

	// A method when called returns the valid Street address
	private static String getValidStreet(Scanner scanner) {

		// Declares a string
		String customerStreetAddress = "  ";

		// Catches the StringIndexOutOfBoundsException
		try {
			// Loops code if the input does not have a street number or a street name
			while (!Character.isDigit(customerStreetAddress.charAt(0))
					|| !Character.isLetter(customerStreetAddress.charAt(customerStreetAddress.length() - 1))) {

				// Asks the user for an input
				System.out.println("Enter customer street address: ");
				customerStreetAddress = scanner.nextLine();

				// Displays what the user is missing
				if (!Character.isDigit(customerStreetAddress.charAt(0))) {
					System.out.println("Please enter the street number");

				} else if (!Character.isLetter(customerStreetAddress.charAt(customerStreetAddress.length() - 1))) {
					System.out.println("Please enter the street name");
				}
			}
		}
		// Displays what and where the Exception happened
		catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("String index out of bounds for customerStreetAddress. String length: "
					+ customerStreetAddress.length());
		}

		// returns the customers street address
		return customerStreetAddress;
	}

	// A method when called returns the valid email
	private static String getValidEmail(Scanner scanner) {

		// Declares a String
		String customerEmailAddress = "";

		// Loops code if the email does not contain '@'
		while (!customerEmailAddress.contains("@")) {

			// Asks the user for an input
			System.out.println("Enter customers email address: ");
			customerEmailAddress = scanner.nextLine();

			// Displays a message to indicate to the user what they are missing
			if (!customerEmailAddress.contains("@")) {
				System.out.println("The email you have entered does not contain the '@' symbol!");
			}
		}

		// returns the email if it contains '@'
		return customerEmailAddress;
	}

	// A method when called checks to see if the given variable is valid or not
	public static boolean isAllNumbers(String contactNum) {

		// loops through the given string
		for (int i = 0; i < contactNum.length(); i++) {

			// Checks if the string contains a number
			if (!Character.isDigit(contactNum.charAt(i)) || contactNum.contains(" ")) {
				// returns turn if it does
				return false;
			}
		}

		// else returns false if it does not
		return true;
	}

	// A method when called writes an invoice to a text file
	private static void invoice(String bestDriver, int totalAmount, Customer customer, Restaurant restaurant)
			throws FileNotFoundException {

		// if there is a driver near by write to a text file
		if (bestDriver != null) {

			// Creates a new formatter for invoice file
			Formatter invoiceFile = new Formatter("invoice.txt");

			// writes to the invoiceFile
			invoiceFile.format("Order number %s", customer.getOrderNumber() + "\r");
			invoiceFile.format("Customer: %s", customer.getName() + "\r");
			invoiceFile.format("Email: %s", customer.getEmailAddress() + "\r");
			invoiceFile.format("Phone number: %s", customer.getContactNumber() + "\r");
			invoiceFile.format("Location: %s", customer.getCity() + "\r");
			invoiceFile.format("\r");
			invoiceFile.format("You have ordered the following from %s in %s: \n", restaurant.getName(),
					restaurant.getCity());
			invoiceFile.format("\r");

			// loops through the array and displays the relevant information
			for (int i = 0; i < listOfMeals.size(); i++) {

				invoiceFile.format("%s x %s (R%s)\n", restaurant.getNumberOfMeal(listOfMeals.get(i)),
						restaurant.getRestaurantOrder(listOfMeals.get(i)), restaurant.getPrice(listOfMeals.get(i)));
			}

			invoiceFile.format("\r");
			invoiceFile.format("Special Instructions: %s", restaurant.getSpecialInstructions() + "\r");
			invoiceFile.format("\r");
			invoiceFile.format("Total: R%s.00 \r", totalAmount);
			invoiceFile.format("\r");
			invoiceFile.format(
					"%s is nearest to the restaurant and so he will be delivering your\n" + "order to you at:\n\r",
					bestDriver);
			invoiceFile.format("%s", customer.getStreetAddress() + "\r");
			invoiceFile.format("\r");
			invoiceFile.format("If you need to contact the restaurant, their number is %s.",
					restaurant.getContactNumber());

			// Closes the invoiceFile
			invoiceFile.close();

			System.out.println("An Invoice has been issued");
		}
		// If no driver is found or is in the are a message will be displayed
		else {
			System.out.println(
					"Sorry! Our " + "drivers are too far away from you to be able to " + "deliver to your location.");
		}
	}
}
/*
 * References:
 * https://www.geeksforgeeks.org/integer-max_value-and-integer-min_value-in-java
 * -with-examples/ https://youtu.be/VMZLPl16P5c?si=UeVXgjSpBKrFs2LE
 */
