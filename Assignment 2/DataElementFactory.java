/******************************************************************************
* DataElementFactory.java
* @brief This class is used to configure and create Data Elements.
* @details-This Factory class represents a hybrid between the Factory design
*	pattern and the Prototype design pattern. The Factory allows external code
*	to specify which data elements must be stored by each `DataElement`
*	object. External code is then able to generate `DataElement` objects based
*	on the configuration specified to the Factory. Once generated, the data
*	for each internal data element is set by the external code.
*
******************************************************************************/
import java.util.Set;
import java.util.TreeSet;

public class DataElementFactory {
	public DataElementFactory() {
		m_entries = new TreeSet<>();
	}

	/// @brief Registers an entry with the Factory.
	/// @details Once an entry is registered with the Factory, all
	///		`DataElement` objects constructed after registration by the
	///		Factory will have a data element allocated for the entry.
	public void registerEntry(String entryID) {
		m_entries.add(entryID);
	}

	/// @brief Constructs a `DataElement` object with data elements allocated
	///		for each registered data entry.
	public DataElement constructElement() {
		return new DataElement(m_entries);
	}

	/// @brief Set used to store all strings used for data entries.
	private Set<String> m_entries;
}