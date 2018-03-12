/******************************************************************************
* MapFactory.java
*	This class is used for configuring and constructing `Map` objects.
*
******************************************************************************/
import java.util.ArrayList;

public class MapFactory {
	public MapFactory() {
		m_markers = new ArrayList<>();
	}

	/// @brief Adds a marker to the map.
	public void addMarker(Marker marker) {
		m_markers.add(marker);
	}

	/// @brief Constructs a map with the properties that this Factory has been
	///		configured with.
	public GoogleMap constructMap() {
		return new GoogleMap(m_title, m_latitude, m_longitude,
			m_zoom, m_scale, m_width, m_height, m_type,
			m_markers);
	}

	/// @brief Sets the title of the map.
	public void setTitle(String title) {
		m_title = title;
	}

	/// @brief Sets the dimensions of the map, in pixels.
	public void setDimensions(int width, int height) {
		m_width = width;
		m_height = height;
	}

	/// @brief Sets the point at which the map should be centered.
	public void setCenter(float latitude, float longitude) {
		m_latitude = latitude;
		m_longitude = longitude;
	}

	/// @brief Sets the zoom level of the map.
	/// @details The higher the number, the greater the zoom.
	public void setZoom(int zoom) {
		m_zoom = zoom;
	}

	/// @brief Sets the scale of the map.
	public void setScale(int scale) {
		m_scale = scale;
	}

	/// @brief Sets the map's type.
	public void setType(EMapType type) {
		m_type = type;
	}

	/// @brief Stores the title of the map.
	private String m_title = "";
	/// @brief Stores the height of the map in pixels.
	private int m_height = 600;
	/// @brief Stores the width of the map in pixels.
	private int m_width = 800;
	/// @brief Stores the latitude at which the map should be centered at.
	private float m_latitude = 0;
	/// @brief Store the longitude at which the map should be centered at.
	private float m_longitude = 0;
	/// @brief Stores the level of zoom for the map.
	/// @details The higher the number, the more zoomed in the map.
	private int m_zoom = 1;
	/// @brief Stores the scale of the map.
	private int m_scale = 1;
	/// @brief Stores the map type.
	private EMapType m_type = EMapType.Roadmap;
	/// @brief Stores all markers to be added to the map.
	private ArrayList<Marker> m_markers;
}