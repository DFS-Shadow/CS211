public interface IMenuItem {
	/// @brief Returns the type of the item.
	EItemType getItemType();
	/// @brief Writes the item's data to the menu.
	void print();
}