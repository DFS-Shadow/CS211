/******************************************************************************
* LatitudePredicate.java
*	This class implements the `DatabasePredicate` interface and filters
*	`DataElement` objects by their latitude.
*
******************************************************************************/
public class LatitudePredicate implements DatabasePredicate {
	/// @brief Standard constructor.
	/// @param centerLatitude Latitude to use as the center of the column.
	/// @param maxDistance Maximum distance that a `DataElement` can be located
	///		from the center point and still be marked by this predicate for
	///		retrieval. This predicate only takes into account latitudinal
	///		distance and not longitudinal distance.
	public LatitudePredicate(float centerLatitude, float maxDistance) {
		m_minLatitude = centerLatitude - maxDistance;
		m_maxLatitude = centerLatitude + maxDistance;
	}

	public boolean process(DataElement element) {
		// Get the element's latitude
		float latitude = Float.parseFloat(element.getData("Latitude"));
		// Check if the element's location is within the specified distance
		return m_minLatitude <= latitude && latitude <= m_maxLatitude;
	}

	/// @brief Stores the minimum value for latitude.
	private float m_minLatitude;
	/// @brief Stores the maximum value for latitude.
	private float m_maxLatitude;
}