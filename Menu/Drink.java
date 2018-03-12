import java.util.Scanner;

public class Drink implements IMenuItem {
	/// @brief Constructs the object using the data read in via the scanner.
	Drink(Scanner scanner) {
		m_name = scanner.nextLine();
		m_price = scanner.nextLine();
	}
	/// @brief Returns the type of the item.
	public EItemType getItemType() {
		return EItemType.DRINK;
	}
	/// @brief Writes the item's data to the menu.
	public void print() {
		System.out.println(m_name);
		System.out.println("Price: " + m_price);
	}

	/// @brief Stores the name of the food.
	private String m_name;
	/// @brief Stores the price of the food.
	/// @details This is stored as a string as the variable contains
	/// 	the dollar sign prepended to the price, and contains exactly
	///		two decimal points of precision.
	private String m_price;
}