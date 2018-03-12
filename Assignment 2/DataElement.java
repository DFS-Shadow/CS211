/******************************************************************************
* DataElement.java
*	This class stores all data contained within a single data element in the
*	data set.
*
******************************************************************************/
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DataElement {
	/// @brief Constructs the element and allocates storage for each piece of
	///		data.
	/// @details This constructor creates key-value pairs for each data entry
	///		listed in the `entries` set passed to the constructor. If code
	///		attempts to bind data to an entry not specified when the
	///		`DataElement` object was constructed, an exception will be thrown.
	/// @param entries A set storing strings that identify the various
	///		data elements stored by this object.
	public DataElement(final Set<String> entries) {
		m_data = new TreeMap<>();
		for (String key : entries) {
			m_data.put(key, null);
		}
	}

	/// @brief Sets the data stored at a given data entry slot.
	/// @param entry String ID used for the data entry slot ID.
	/// @param data `Object`-derived object that will be inserted into the
	///		`DataElement` object.
	/// @throws Throws `RuntimeException` if `entry` is not registered with
	///		the object.
	public void setData(String entry, String data) {
		if (m_data.containsKey(entry)) {
			m_data.put(entry, data);
		} else {
			throw new RuntimeException("Data Entry ID not registered.");
		}
	}

	/// @brief Returns an object stored within the `DataElement`.
	/// @param entry String ID used for the data entry slot ID.
	/// @returns Returns the data object associated with the given entry
	///		slot ID.
	/// @throws Throws `RuntimeException` if `entry` is not registered with
	///		the object.
	public String getData(String entry) {
		if (m_data.containsKey(entry)) {
			return m_data.get(entry);
		} else {
			throw new RuntimeException("Data Entry ID not found.");
		}
	}

	/// @brief Map used to store all data stored within the element.
	/// @details The key for the map is the name of the column within the
	///		dataset. The value stored is the string representation of the
	///		data associated with the given element in the dataset.
	public Map<String, String> m_data;
}