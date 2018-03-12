/******************************************************************************
* Marker.java
*	This struct stores information required to place a marker on a map.
*
******************************************************************************/
public class Marker {
	/// @brief Default constructor for Markers.
	public Marker() {
		this(" ", EMarkerColor.Red, 0.0f, 0.0f);
	}
	/// @brief Standard constructor for Markers.
	/// @param label Specifies the label to use for the marker.
	/// @param color Specifies the color to use for the marker.
	/// @param latitude Specifies the latitude at which the marker should be
	///		placed.
	/// @param longitude Specifies the longitude at which the marker should
	///		be placed.
	public Marker(String label, EMarkerColor color,
		float latitude, float longitude) {
		m_label = label;
		m_color = color;
		m_latitude = latitude;
		m_longitude = longitude;
	}

	/// @brief Returns the string representation of the marker.
	/// @details The string returned by this function is formatted in the
	///		format expected by the Google Maps API.
	public String toString() {
		String retVal = new String();
		// Add the "header" for the marker
		retVal += "&markers=color:";
		// Add the color data
		switch (m_color) {
			case Red:
				retVal += "red";
			break;
			case Green:
				retVal += "green";
			break;
			case Blue:
				retVal += "blue";
			break;
			default:
				retVal += "red";
		}

		// Add the label of the marker
		retVal += "%7Clabel:" + m_label + "%7C";
		// Add the position of the marker
		retVal += m_latitude + "," + m_longitude;

		return retVal;
	}

	/// @brief Stores the label to use for the marker.
	private String m_label;
	/// @brief Stores the color to use for the marker.
	private EMarkerColor m_color;
	/// @brief Stores the latitude at which the marker is positioned.
	private float m_latitude;
	/// @brief Stores the longitude at which the marker is positioned.
	private float m_longitude;
}