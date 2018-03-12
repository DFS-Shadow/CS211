/******************************************************************************
* Zach Wilson, Student ID 950638055
* March 6, 2018
* Assignment #7, CS211 (Winter 2018)
* Description: This program implements a Min-Heap and tests it by adding and
* removing randomly generated numbers.
*
******************************************************************************/
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		// Min-Heap
		Heap heap = new Heap((l, r) -> l < r);
		// Alternative: Max-Heap
		// Heap heap = new Heap((l, r) -> l > r);

		Random rand = new Random();
		for (int i = 0; i < 30; ++i) {
			// Print the line number
			System.out.print(i + 1);
			System.out.print(' ');

			// Select an option randomly
			int option = rand.nextInt(2);
			// If the heap is empty, removing an element isn't an option
			if (heap.isEmpty()) {
				// Force the selected option to be "Add an element"
				option = 0;
			}

			switch (option) {
			case 0:
				// Add a random number to the heap
				// Numbers will always be in the range [1, 100]
				int newNum = rand.nextInt(100) + 1;
				heap.add(newNum);
				System.out.print("Add " + newNum + ": ");
				break;
			case 1:
				// Remove the top value from the heap
				int removedValue = heap.pop();
				System.out.print("Remove " + removedValue + ": ");
				break;
			default:
				// An unexpected number was generated
				assert false;
			}

			// Print the heap
			System.out.print('[');
			heap.foreach(new PrintPredicate());
			System.out.println(']');
		}

	}
}