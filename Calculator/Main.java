/******************************************************************************
* Main.java
*	This class handles program initialization and execution.
*
******************************************************************************/
import java.util.Scanner;

public class Main {
	public static void main(String[] string) {
		// Objects used by the main loop
		Equation equation = new Equation();
		Scanner scanner = new Scanner(System.in);

		// Loop until the user signals to quit
		while (true) {
			System.out.println("Enter an equation or type 'quit' to exit.");
			String input = scanner.nextLine();
			// Check if the user typed in 'quit'
			if (input.contentEquals("quit")) {
				break;
			}
			// Solve the equation that was provided
			System.out.println("Your equation evaluates to " +
				equation.solveEquation(input));
			System.out.println();
		}

		scanner.close();
	}
}