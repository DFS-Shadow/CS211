/******************************************************************************
* ManhattanPredicate.java
*	This class implements the `DatabasePredicate` interface and filters
*	`DataElement` objects by Manhattan Distance.
*
******************************************************************************/
public class ManhattanPredicate implements DatabasePredicate {
	/// @brief Standard constructor.
	/// @param centerY Latitude of the center point to use.
	/// @param centerX Longitude of the center point to use.
	/// @param maxDistance Maximum distance that a `DataElement` can be located
	///		from the center point and still be marked by this predicate for
	///		retrieval.
	public ManhattanPredicate(float centerY, float centerX,
		float maxDistance) {
		m_minLongitude = centerX - maxDistance;
		m_maxLongitude = centerX + maxDistance;
		m_minLatitude = centerY - maxDistance;
		m_maxLatitude = centerY + maxDistance;
	}

	public boolean process(DataElement element) {
		// Get the latitude and longitude
		float latitude = Float.parseFloat(element.getData("Latitude"));
		float longitude = Float.parseFloat(element.getData("Longitude"));
		// Check if the element's location is within the specified distance
		return m_minLongitude <= longitude && longitude <= m_maxLongitude &&
			m_minLatitude <= latitude && latitude <= m_maxLatitude;
	}

	/// @brief Stores the minimum value for longitude.
	private float m_minLongitude;
	/// @brief Stores the maximum value for longitude.
	private float m_maxLongitude;
	/// @brief Stores the minimum value for latitude.
	private float m_minLatitude;
	/// @brief Stores the maximum value for latitude.
	private float m_maxLatitude;
}