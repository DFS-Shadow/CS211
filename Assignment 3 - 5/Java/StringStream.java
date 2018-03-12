/******************************************************************************
* StringStream.java
*	The `StringStream` class implements a stream-like iterface for iterating
*	over a string.
*
******************************************************************************/
public class StringStream {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	public StringStream(String str) {
		m_str = str;
		m_index = 0;
	}

	/**************************************************************************
	*
	*	Public Member Functions
	*
	**************************************************************************/
	/// @brief Checks whether the iterator is at the start of the string.
	public boolean atStart() {
		return m_index == 0;
	}

	/// @brief Checks whether the integer is at the end of the string.
	public boolean atEnd() {
		return m_index == m_str.length();
	}

	/// @brief Retrieves the current character and advances the stream by
	///		one character.
	public char get() {
		char c = m_str.charAt(m_index);
		++m_index;
		return c;
	}

	/// @brief Returns the index at which the stream is currently at.
	public int getIndex() {
		return m_index;
	}

	/// @brief Returns the underlying string.
	public String getString() {
		return m_str;
	}

	/// @brief Checks whether the next index is valid.
	public boolean hasNext() {
		return m_index + 1 < m_str.length();
	}

	/// @brief Returns the next character in the string.
	public char peek() {
		return peek(0);
	}

	/// @brief Returns the character at the offset specified.
	public char peek(int offset) {
		return m_str.charAt(m_index + offset);
	}

	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Stores the string that this iterator iterates over.
	private String m_str;
	/// @brief Stores the current index that the iterator is at.
	private int m_index;
}