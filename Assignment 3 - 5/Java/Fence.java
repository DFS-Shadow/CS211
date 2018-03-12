/******************************************************************************
* Fence.java
*	The `Fence` class is used for Fence-type tokens.
*
******************************************************************************/
public class Fence implements IToken {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	/// @brief Standard Constructor.
	/// @param closingFenceChar Fence character that can be used to close off
	///		this fence.
	public Fence(char closingFenceChar) {
		m_closingChar = closingFenceChar;
	}

	/**************************************************************************
	*
	*	Public Member Variables
	*
	**************************************************************************/
	/// @brief Checks if the fence object can close off this fence object.
	public boolean isValidClosingFence(PopFence fence) {
		return m_closingChar == fence.getFenceChar();
	}

	/// @brief Returns the expected closing character.
	public char getClosingChar() {
		return m_closingChar;
	}

	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Closing Fence character.
	char m_closingChar;
}