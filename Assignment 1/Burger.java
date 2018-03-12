/******************************************************************************
* Burger.java 
*	This class is used to store information about burgers.
*
******************************************************************************/
public class Burger implements IMenuItem {
	/// @brief Constructs and initializes the object to the specified values.
	public Burger(String name, String price, int calories) {
		m_name = name;
		m_price = price;
		m_calories = calories;
	}

	/// @brief Returns the name of the item.
	public String getName() {
		// A copy of the string is returned to prevent outside code from
		// modifying the stored string
		return new String(m_name);
	}

	/// @brief Returns the price of the item.
	/// @details The returned string includes the preceeding dollar sign and
	///		has two decimal points of precision.
	public String getPrice() {
		// A copy of the string is returned to prevent outside code from
		// modifying the stored string
		return new String(m_price);
	}

	/// @brief Returns the calorie count of the item.
	public int getCalories() {
		return m_calories;
	}

	/// @brief Sets the price of the burger to the specified price.
	public void setPrice(String price) {
		m_price = price;
	}

	/// @brief Stores the name of the burger.
	private final String m_name;
	/// @brief Stores the price of the burger, including the leading dollar
	///		sign and two decimal places of precision.
	private String m_price;
	/// @brief Stores the calorie count of the burger.
	private final int m_calories;
}