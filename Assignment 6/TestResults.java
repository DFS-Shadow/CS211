/******************************************************************************
* TestResults.java
*	Defines a struct used for storing test results.
*
******************************************************************************/
public class TestResults {
	// @brief Standard constructor.
	// @param name Name of the test.
	// @param arrayTime Amount of time taken by the array to finish the test,
	//		in milliseconds.
	// @param listTime Amount of time taken by the list to finish the test,
	//		in milliseconds.
	// @returns Returns a fully initialized `TestResults` object.
	public TestResults(String name, long arrayTime, long listTime) {
		// Add the test results
		testName = name;
		// Convert the times passed to the function to seconds
		arrayResult = arrayTime;
		listResult = listTime;
		// Normalize the results
		normalizedArrayResult = arrayTime /
			(float)((arrayTime > listTime) ? arrayTime : listTime) * 100;
		normalizedListResult = listTime /
			(float)((arrayTime > listTime) ? arrayTime : listTime) * 100;
	}

	// Stores the name of the test whose results are stored
	public String testName;
	// Stores the time in milliseconds that the array took to finish the test
	public long arrayResult;
	// Stores the time in milliseconds that the list took to finish the test
	public long listResult;
	// Stores the normalized value for the array's result
	// This will be a value between 0 and 100
	public float normalizedArrayResult;
	// Stores the normalized value for the list's result
	// This will be a value between 0 and 100
	public float normalizedListResult;
}