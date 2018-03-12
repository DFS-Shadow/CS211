/******************************************************************************
* LongitudePredicate.java
*	This class implements the `DatabasePredicate` interface and filters
*	`DataElement` objects by their longitude.
*
******************************************************************************/
public class LongitudePredicate implements DatabasePredicate {
	/// @brief Standard constructor.
	/// @param centerLongitude Longitude to use as the center of the column.
	/// @param maxDistance Maximum distance that a `DataElement` can be located
	///		from the center point and still be marked by this predicate for
	///		retrieval. This predicate only takes into account longitudinal
	///		distance and not latitudinal distance.
	public LongitudePredicate(float centerLongitude, float maxDistance) {
		m_minLongitude = centerLongitude - maxDistance;
		m_maxLongitude = centerLongitude + maxDistance;
	}

	public boolean process(DataElement element) {
		// Get the element's longitude
		float longitude = Float.parseFloat(element.getData("Longitude"));
		// Check if the element's location is within the specified distance
		return m_minLongitude <= longitude && longitude <= m_maxLongitude;
	}

	/// @brief Stores the minimum value for longitude.
	private float m_minLongitude;
	/// @brief Stores the maximum value for longitude.
	private float m_maxLongitude;
}