import java.util.Scanner;

public class Food implements IMenuItem {
	/// @brief Constructs the object using the data read in via the scanner.
	Food(Scanner scanner) {
		m_name = scanner.nextLine();
		m_calories = Integer.parseInt(scanner.nextLine());
		m_price = scanner.nextLine();
		m_preparationTime = scanner.nextLine();
		m_allergenWarning = scanner.nextLine();
	}
	/// @brief Returns the type of the item.
	public EItemType getItemType() {
		return EItemType.FOOD;
	}
	/// @brief Writes the item's data to the menu.
	public void print() {
		System.out.println(m_name);
		System.out.println("Calories: " + m_calories + " Cal");
		System.out.println("Price: " + m_price);
		System.out.println("Preparation Time: " + m_preparationTime);
		System.out.println(m_allergenWarning);
	}

	/// @brief Stores the name of the food.
	private String m_name;
	/// @brief Stores the number of calories in the food.
	private int m_calories;
	/// @brief Stores the price of the food.
	/// @details This is stored as a string as the variable contains
	/// 	the dollar sign prepended to the price, and contains exactly
	///		two decimal points of precision.
	private String m_price;
	/// @brief Stores the average preparation time for the food.
	private String m_preparationTime;
	/// @brief Stores allergen information for the food.
	private String m_allergenWarning;
}