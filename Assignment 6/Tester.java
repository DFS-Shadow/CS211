/******************************************************************************
* Tester.java
*	Defines a class used for executing performance tests.
*
******************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Tester {
	public Tester() {

	}

	// Runs the tests and returns the results
	public ArrayList<TestResults> runTests() {
		ArrayList<TestResults> retVal = new ArrayList<>();
		retVal.add(runAddTest());
		retVal.add(runGetTest());
		retVal.add(runRemoveTest());
		retVal.add(runSortTest());
		return retVal;
	}

	// Runs the Add Elements test
	private TestResults runAddTest() {
		// Create the containers
		ArrayList<Integer> array = new ArrayList<>();
		LinkedList<Integer> list = new LinkedList<>();

		// Add the elements to the array
		long arrayStartTime = System.currentTimeMillis();
		for (int i = 0; i < ELEMENT_COUNT; ++i) {
			array.add(i);
		}
		long arrayEndTime = System.currentTimeMillis();

		// Add the elements to the list
		long listStartTime = System.currentTimeMillis();
		for (int i = 0; i < ELEMENT_COUNT; ++i) {
			list.add(i);
		}
		long listEndTime = System.currentTimeMillis();

		// Return the results
		return new TestResults("Add",
		 arrayEndTime - arrayStartTime, listEndTime - listStartTime);
	}

	// Runs the Element Retrieval test
	private TestResults runGetTest() {
		// Initialize the containers
		ArrayList<Integer> array = new ArrayList<>();
		LinkedList<Integer> list = new LinkedList<>();
		// Add elements to the containers
		for (int i = 0; i < ELEMENT_COUNT; ++i) {
			array.add(i);
		}
		for (int i = 0; i < ELEMENT_COUNT; ++i) {
			list.add(i);
		}
		// Create an array containing the indices to retrieve
		// from each container
		ArrayList<Integer> elements = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < TEST_COUNT; ++i) {
			elements.add(rand.nextInt(ELEMENT_COUNT));
		}

		// Run the tests
		long arrayStartTime = System.currentTimeMillis();
		for (int i = 0; i < elements.size(); ++i) {
			array.get(elements.get(i));
		}
		long arrayEndTime = System.currentTimeMillis();

		long listStartTime = System.currentTimeMillis();
		for (int i = 0; i < elements.size(); ++i) {
			list.get(elements.get(i));
		}
		long listEndTime = System.currentTimeMillis();

		return new TestResults("Get",
		 arrayEndTime - arrayStartTime, listEndTime - listStartTime);
	}

	// Runs the Element Removal test
	private TestResults runRemoveTest() {
		// Initialize the containers
		ArrayList<Integer> array = new ArrayList<>();
		LinkedList<Integer> list = new LinkedList<>();
		// Add elements to the containers
		for (int i = 0; i < ELEMENT_COUNT; ++i) {
			array.add(i);
		}
		for (int i = 0; i < ELEMENT_COUNT; ++i) {
			list.add(i);
		}
		// Create an array containing the indices to retrieve
		// from each container
		ArrayList<Integer> elements = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < TEST_COUNT; ++i) {
			// The indices generated here are in the range
			// [0, ELEMENT_COUNT - TEST_COUNT) to ensure that all generated
			// indices remain valid even as elements are removed
			elements.add(rand.nextInt(ELEMENT_COUNT - TEST_COUNT));
		}

		// Run the tests
		long arrayStartTime = System.currentTimeMillis();
		for (int i = 0; i < elements.size(); ++i) {
			array.remove(elements.get(i));
		}
		long arrayEndTime = System.currentTimeMillis();

		long listStartTime = System.currentTimeMillis();
		for (int i = 0; i < elements.size(); ++i) {
			list.remove(elements.get(i));
		}
		long listEndTime = System.currentTimeMillis();

		return new TestResults("Remove",
		 arrayEndTime - arrayStartTime, listEndTime - listStartTime);
	}

	// Runs the Sort Test
	private TestResults runSortTest() {
		// Initialize the containers
		ArrayList<Integer> array = new ArrayList<>();
		LinkedList<Integer> list = new LinkedList<>();
		// Add elements to the containers
		Random rand = new Random();
		for (int i = 0; i < ELEMENT_COUNT; ++i) {
			int num = rand.nextInt(10000);
			array.add(num);
			list.add(num);
		}

		// Run the tests
		long arrayStartTime = System.currentTimeMillis();
		Collections.sort(array);
		long arrayEndTime = System.currentTimeMillis();

		long listStartTime = System.currentTimeMillis();
		Collections.sort(list);
		long listEndTime = System.currentTimeMillis();

		return new TestResults("Sort",
		 arrayEndTime - arrayStartTime, listEndTime - listStartTime);
	}

	private static final int ELEMENT_COUNT = 100000;
	private static final int TEST_COUNT = 10000;
}