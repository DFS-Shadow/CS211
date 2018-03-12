/******************************************************************************
* Map.java
*	This class is used to display an image using the Google Maps API.
*
******************************************************************************/
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GoogleMap {
	public GoogleMap(String title, float latitude, float longitude,
		int zoom, int scale, int width, int height,
		EMapType type, ArrayList<Marker> markers) {
		// Initialize the JFrame
		m_map = new JFrame(title);
		m_map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_map.setSize(width, height);
		m_map.setLocationRelativeTo(null);

		try {
			// Construct the URL used to retrieve the map image using the
			// parameters passed to the constructor
			String imageUrl =
				"https://maps.googleapis.com/maps/api/staticmap?center=";
			imageUrl += latitude + "," + longitude + "&zoom=" + zoom +
				"&size=" + width + "x" + height + "&scale=" + zoom +
				"&maptype=" + type;
			// Add the data for each marker to the URL
			for (int i = 0; i < markers.size() && i < MAX_MARKER_COUNT; ++i) {
				imageUrl += markers.get(i).toString();
			}

			// Path to save the map image to
			String destinationFile = "map.jpg";

			// Read in the image from the URL
			URL url = new URL(imageUrl);
			InputStream inputStream = url.openStream();
			OutputStream outputStream = new FileOutputStream(destinationFile);
			byte[] bytes = new byte[2048];

			int length;
			while ((length = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, length);
			}
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Create the image and add it to the JFrame for display
		ImageIcon imageIcon = new ImageIcon((new ImageIcon("map.jpg"))
			.getImage().getScaledInstance(
				width, height, java.awt.Image.SCALE_SMOOTH));
		m_map.add(new JLabel(imageIcon));
	}

	/// @brief Displays the map.
	public void display() {
		m_map.setVisible(true);
		m_map.pack();
	}

	/// @brief Stores the maximum number of markers allowed to be displayed.
	private static int MAX_MARKER_COUNT = 150;
	/// @brief JFrame used to display the map.
	private JFrame m_map;
}