/******************************************************************************
* Zach Wilson - Student ID 950638055
* Assignment #5, CS211 (Winter 2018)
* March 2, 2018
* Description: This program is designed to measure the performance of the
*	ArrayList and LinkedList classes in various scenarios.
*
******************************************************************************/
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// Run the tests
		Tester tester = new Tester();
		ArrayList<TestResults> results = tester.runTests();
		// Display the results in the GUI
		GUI gui = new GUI(results);
		gui.display();
	}

}