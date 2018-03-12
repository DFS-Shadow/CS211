/******************************************************************************
* PopFence.java 
*	The `PopFence` class is used to force the Parser to pop tokens from the
*	operator stack until a Fence-type token is reached.
*
******************************************************************************/
public class PopFence implements IToken {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	public PopFence(char fenceChar) {
		m_fenceChar = fenceChar;
	}

	/**************************************************************************
	*
	*	Public Member Variables
	*
	**************************************************************************/
	/// @brief Returns the fence character.
	public char getFenceChar() {
		return m_fenceChar;
	}
	
	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Fence char.
	char m_fenceChar;
}