/******************************************************************************
* GUI.java
*	This class contains the code for displaying the results GUI.
*
******************************************************************************/
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUI {
	public GUI(ArrayList<TestResults> results) {
		m_results = results;
		m_frame = new JFrame("Assignment 6");
		m_frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		m_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.LIGHT_GRAY);

				// Draw a line to use as the bottom of the graph
				g.setColor(Color.RED);
				g.drawLine(100, 700, 800, 700);

				// Width of each bar
				final int width = 50;
				// (Maximum) height of each bar
				// The actual height is dependent on the test results
				final int height = 400;
				// Starting X location
				final int startingX = 100;
				// Offset between two bars in the same group
				final int intergroupOffsetX = 50;
				// Offset between two sets of test result bars
				final int offsetX = 200;
				// Y coordinate to use for the bars
				final int y = 700;
				// Starting X coordinate for text
				final int startingTextX = 120;
				// Y coordinate to use for text
				final int textY = 280;
				// Get the graphics object ready for font rendering
				g.setFont(new Font("Serif", Font.PLAIN, 30));
				// Draw the results
				for (int i = 0; i < m_results.size(); ++i) {
					TestResults result = m_results.get(i);
					// Draw the test name
					g.setColor(Color.BLACK);
					g.drawString(result.testName,
						startingTextX + offsetX * i, textY);
					// Calculate the height of the two bars
					int arrayBarHeight = (int)(height *
						(result.normalizedArrayResult / 100.0f));
					int listBarHeight = (int)(height *
						(result.normalizedListResult / 100.0f));
					// If either bar has a height of zero, set it to 1
					// (This is done so that the bar at least appears)
					arrayBarHeight = (arrayBarHeight > 0) ? arrayBarHeight : 1;
					listBarHeight = (listBarHeight > 0) ? listBarHeight : 1;

					// Set the color to use for the array's bar
					g.setColor(Color.BLUE);
					g.fillRect(startingX + i * offsetX, y - arrayBarHeight,
						width, arrayBarHeight);
					// Set the color to use for the list's bar
					g.setColor(Color.RED);
					g.fillRect(startingX + intergroupOffsetX + i * offsetX,
						y - listBarHeight, width, listBarHeight);
				}

				// Details panel variables
				final int DETAILS_PANEL_X = 820;
				final int DETAILS_PANEL_Y = 200;
				final int DETAILS_PANEL_TEXT_HEIGHT = 20;
				final int DETAILS_PANEL_LINE_OFFSET = (int)(DETAILS_PANEL_TEXT_HEIGHT * 1.25f);
				// Draw the details panel
				g.setColor(Color.BLACK);
				g.setFont(new Font("Serif", Font.PLAIN, DETAILS_PANEL_TEXT_HEIGHT));
				// Create the array of strings to output
				ArrayList<String> detailsPanelStrings = new ArrayList<>();
				detailsPanelStrings.add("Blue = ArrayList Results");
				detailsPanelStrings.add("Red = LinkedList Results");
				detailsPanelStrings.add("Note: Results have been normalized");
				detailsPanelStrings.add("for display.");
				detailsPanelStrings.add("");
				for (int i = 0; i < m_results.size(); ++i) {
					TestResults results = m_results.get(i);
					detailsPanelStrings.add("Test: " + results.testName);
					detailsPanelStrings.add("ArrayList Time: " + results.arrayResult + "ms");
					detailsPanelStrings.add("LinkedList Time: " + results.listResult + "ms");
					detailsPanelStrings.add("");
				}

				// Draw the details panel
				for (int i = 0; i < detailsPanelStrings.size(); ++i) {
					g.drawString(detailsPanelStrings.get(i), DETAILS_PANEL_X,
						DETAILS_PANEL_Y + DETAILS_PANEL_LINE_OFFSET * i);
				}
			}
		};
		m_panel.setLayout(null);

		m_mainTitle = new JLabel(WINDOW_TITLE);
		m_mainTitle.setBounds(WINDOW_WIDTH / 5, 40,
			WINDOW_WIDTH - 20, WINDOW_HEIGHT / 6);
		m_mainTitle.setFont(new Font("Serif", Font.PLAIN, 60));

		m_frame.add(m_panel);
		m_panel.add(m_mainTitle);
	}

	public void display() {
		m_frame.setVisible(true);
	}

	// JFrame window properties
	private static final String WINDOW_TITLE = "Assignment 6";
	// Height and width to use for the JFrame window
	private static final int WINDOW_WIDTH = 1200;
	private static final int WINDOW_HEIGHT = 800;

	// Standard member variables
	private ArrayList<TestResults> m_results;

	// JFrame variables
	private JFrame m_frame;
	private JPanel m_panel;
	private JLabel m_mainTitle;
}