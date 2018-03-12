/******************************************************************************
* IMenuItem.java 
*	Defines the interface that all menu items must adhere to.
*
******************************************************************************/
public interface IMenuItem {
	/// @brief Returns the name of the item.
	String getName();
	/// @brief Returns the price of the item.
	/// @details This string should include the preceeding dollar sign and
	///		have two decimal points of precision.
	String getPrice();
	/// @brief Returns the calorie count of the item.
	int getCalories();
	/// @brief Sets the price of the item to the specified price.
	void setPrice(String price);
}