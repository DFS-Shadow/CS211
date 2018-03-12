/******************************************************************************
* Main.java
*	This class acts as the entry point into the application.
*
*	Advanced Data Structures:
*	* Set - Used by the `DataElement` class to ensure that duplicate data
*		fields are not created.
*	* Map - Used by the `DataElement` class to map values to each data field
*		created for the data element.
*
******************************************************************************/
import java.io.File;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// Initialize the Database using the dataset
		File dataFile = new File("dataset.csv");
		Database database = new Database(dataFile);
		// Retrieve all elements around UW Seattle
		DatabasePredicate predicate = new ManhattanPredicate(
			UW_SEATTLE_LATITUDE, UW_SEATTLE_LONGITUDE, 0.05f);
		ArrayList<DataElement> elements = database.filter(predicate);
		// Construct a Marker object for each element
		ArrayList<Marker> markers = new ArrayList<>();
		for (DataElement element : elements) {
			String label = element.getData("Event Clearance Code");
			EMarkerColor color = EMarkerColor.Red;
			float latitude = Float.parseFloat(element.getData("Latitude"));
			float longitude = Float.parseFloat(element.getData("Longitude"));
			Marker marker = new Marker(label, color, latitude, longitude);
			markers.add(marker);
		}

		// Construct the map
		MapFactory factory = new MapFactory();
		factory.setTitle("Assignment 2");
		factory.setCenter(MAP_CENTER_LATITUDE, MAP_CENTER_LONGITUDE);
		factory.setZoom(11);
		// Add each marker to the map
		for (Marker marker : markers) {
			factory.addMarker(marker);
		}
		GoogleMap map = factory.constructMap();
		// Display the map
		map.display();
	}

	/// @brief Latitude at which Bellevue College is located.
	private static final float BC_LATITUDE = 47.610378f;
	/// @brief Longitude at which Bellevue College is located.
	private static final float BC_LONGITUDE = -122.200676f;
	/// @brief Latitude at which UW Seattle is located.
	private static final float UW_SEATTLE_LATITUDE = 47.6553f;
	/// @brief Longitude at which UW Seattle is located.
	private static final float UW_SEATTLE_LONGITUDE = -122.3035f;
	/// @brief Latitude to center the map at.
	private static final float MAP_CENTER_LATITUDE = BC_LATITUDE;
	/// @brief Longitude to center the map at.
	private static final float MAP_CENTER_LONGITUDE = BC_LONGITUDE;
}