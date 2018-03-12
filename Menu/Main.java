import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] string) {
		// Read in the menu file
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("menu.txt"));
			// Set the scanner to use the newline delimiter
			// Note: If running on Linux, this would need to be changed to just
			// "\n" and not "\r\n" (Windows newline characters).
			// scanner.useDelimiter("\r\n");
		} catch (FileNotFoundException e) {
			System.out.println("Failed to find menu.txt.");
			return;
		}
		
		// Menu object used to store the various items
		Menu menu = new Menu();

		// Each menu item starts with a line that identifies
		// the type of the menu item, followed by the item-specific
		// data.
		while (scanner.hasNext()) {
			// Read in the type of the item
			String itemType = scanner.nextLine();
			// Construct the correct type of item
			switch (itemType) {
			case ITEM_TYPE_FOOD:
				menu.addItem(new Food(scanner));
				break;
			case ITEM_TYPE_DRINK:
				menu.addItem(new Drink(scanner));
				break;
			case ITEM_TYPE_DESSERT:
				menu.addItem(new Dessert(scanner));
				break;
			}
		}

		// Display the menu
		menu.printMenu();
	}

	private static final String ITEM_TYPE_FOOD = "Food";
	private static final String ITEM_TYPE_DRINK = "Drink";
	private static final String ITEM_TYPE_DESSERT = "Dessert";
}