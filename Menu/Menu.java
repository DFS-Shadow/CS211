import java.util.ArrayList;

public class Menu {
	public Menu() {
		m_menuItems = new ArrayList<IMenuItem>();
	}

	/// @brief Adds an item to the menu.
	public void addItem(IMenuItem item) {
		m_menuItems.add(item);
	}

	/// @brief Displays each item in the menu.
	public void printMenu() {
		for(IMenuItem item : m_menuItems) {
			// Print the item's information
			item.print();
			// Add a blank line after each item
			System.out.println();
		}
	}

	private ArrayList<IMenuItem> m_menuItems;
}