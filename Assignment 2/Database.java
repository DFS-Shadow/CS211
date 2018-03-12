/******************************************************************************
* Database.java
*	This class is used to load and query data from a dataset.
*
******************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	/// @brief Loads in data from the data file.
	/// @param dataFile Must be a CSV file containing the data to be loaded.
	public Database(File dataFile) {
		m_elements = new ArrayList<>();

		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(dataFile);
		} catch (FileNotFoundException e) {
			System.out.println(
				"Error: Failed to load data from the data file.");
			return;
		}

		// Read in the first line
		// In CSV files, this defines what each column contains
		String header = fileScanner.nextLine();
		ArrayList<String> columns = new ArrayList<>();
		// Break up the header string into tokens representing each column
		{
			Scanner headerScanner = new Scanner(header);
			headerScanner.useDelimiter(",");
			// Add each column header into the ArrayList
			while (headerScanner.hasNext()) {
				columns.add(headerScanner.next());
			}
			headerScanner.close();
		}

		// Create a Factory object for creating the Data Elements
		DataElementFactory factory = new DataElementFactory();
		// Create a data entry for each column
		for (String column : columns) {
			factory.registerEntry(column);
		}

		// Read in the data from the data file
		// Stop when the maximum number of elements have been read or the
		// data file has been completely loaded
		for (int i = 0; i < MAX_ELEMENTS && fileScanner.hasNext(); ++i) {
			// Read in the line and create a scanner for it
			String line = fileScanner.nextLine();
			Scanner lineScanner = new Scanner(line);
			// Break on commas *if and only if* the comma isn't surrounded
			// by quotation marks. This is done by using a regex that matches
			// the comma if the comma has zero *or* an even number of quotation
			// marks ahead of it.
			lineScanner.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			// Create the `DataElement` object for the datda
			DataElement element = factory.constructElement();
			// Pull the various data from the line
			for (int j = 0; j < columns.size(); ++j) {
				// Get and store the data
				String data = new String();
				if (lineScanner.hasNext()) {
					data = lineScanner.next();
				}
				element.setData(columns.get(j), data);
			}
			m_elements.add(element);
			lineScanner.close();
		}

		fileScanner.close();
	}

	/// @brief Retrieves data elements from the database using the supplied
	///		predicate.
	/// @details This function will only return `DataElement` objects for
	///		which the predicate returned true.
	/// @param p Predicate to apply to each `DataElement` object.
	/// @returns Returns an `ArrayList` of all `DataElement` objects for which
	///		the predicate returned true.
	public ArrayList<DataElement> filter(DatabasePredicate p) {
		ArrayList<DataElement> retVal = new ArrayList<>();
		// Iterate over all data elements stored by the database
		// and pick elements using the predicate
		for (DataElement element : m_elements) {
			if (p.process(element)) {
				retVal.add(element);
			}
		}

		return retVal;
	}

	/// @brief Stores the maximum number of elements to read in from the data
	///		set.
	private static final int MAX_ELEMENTS = 1000;
	/// @brief Stores the data elements loaded from the file.
	private ArrayList<DataElement> m_elements;
}