/******************************************************************************
* Main.java 
*	This class contains the entry point into the program.
*
******************************************************************************/
import java.io.File;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// ArrayList used to store all Franchise objects
		ArrayList<Franchise> franchises = new ArrayList<>();
		// Load the menu data file
		File menuData = new File("menu.txt");
		// Add each franchise to the arraylist
		franchises.add(new Franchise(
			"Seattle", menuData, new File("seattle.txt")));
		franchises.add(new Franchise(
			"Bellevue", menuData, new File("bellevue.txt")));
		franchises.add(new Franchise(
			"Everett", menuData, new File("everett.txt")));
		// Print the menu for each franchise
		for (Franchise franchise : franchises) {
			franchise.printMenu();
		}
	}
}