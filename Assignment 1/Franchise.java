/******************************************************************************
* Franchise.java 
*	This class is used to handle a franchise location.
*
******************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Franchise {
	/// @brief Constructs the Franchise object using the data in the files
	///		specified.
	/// @param locationName Name of the franchise location.
	/// @param menuData File object representing the text file containing
	///		the data for Burger211's menu.
	/// @param locationData File object containing data for location-specific
	///		modifications.
	public Franchise(String locationName, File menuData, File locationData) {
		m_locationName = locationName;
		m_menuItems = new ArrayList<>();
		// Map used to hold modifier objects.
		// The name of the item that each modifier is supposed to apply to is
		// used as the map's key.
		Map<String, IModifier> locationModifiers =
			new TreeMap<String, IModifier>();
		// Read in the location data and construct modifier objects from
		// the data
		try {
			Scanner locationScanner = new Scanner(locationData);
			locationScanner.useDelimiter(DELIMITER);
			// Read in the data
			while (locationScanner.hasNext()) {
				String itemName = locationScanner.next();
				String price = locationScanner.next();
				// Store the modifier
				locationModifiers.put(itemName,
					new PriceModifier(itemName, price));
			}
		} catch (FileNotFoundException e) {
			assert false;
		}

		// Read in the menu data and apply any modifiers
		try {
			Scanner menuScanner = new Scanner(menuData);
			menuScanner.useDelimiter(DELIMITER);
			// Read in the item data
			while (menuScanner.hasNext()) {
				String itemName = menuScanner.next();
				String price = menuScanner.next();
				int calories = Integer.parseInt(menuScanner.next());
				// Construct the menu item
				IMenuItem item = new Burger(itemName, price, calories);
				// Check if a modifier should be applied
				if (locationModifiers.containsKey(itemName)) {
					// Get the modifier
					IModifier modifier = locationModifiers.get(itemName);
					// Apply the modifier
					modifier.applyModifier(item);
				}
				// Store the item in the menu
				m_menuItems.add(item);
			}
		} catch (FileNotFoundException e) {
			assert false;
		}
	}

	public void printMenu() {
		// Print the menu header
		System.out.print("============= Burger211 ");
		System.out.print(m_locationName);
		System.out.print(" - Menu");
		System.out.println(" =============");
		// Print item data for the menu
		for (IMenuItem item : m_menuItems) {
			System.out.println(item.getName());
			System.out.println("Price: " + item.getPrice());
			System.out.println("Calories: " + item.getCalories());
			System.out.println();
		}
	}

	/// @brief Stores the name of the franchise location.
	private String m_locationName;
	/// @brief ArrayList used to store menu items
	private ArrayList<IMenuItem> m_menuItems;
	/// @brief Custom delimiter to be used with `Scanner` objects.
	/// @details This delimiter causes the scanner to tokenize input
	/// 	data by lines, rather than by whitespace. Additionally,
	///		the regex also matches empty lines, causing the empty lines
	///		to be consumed and not returned by the scanner.
	private static Pattern DELIMITER = Pattern.compile("[\r\n]+");
}