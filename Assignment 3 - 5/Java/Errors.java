/******************************************************************************
* Errors.java
*	Defines a class that stores all error messages used by the program.
*
******************************************************************************/
public class Errors {
	/**************************************************************************
	*
	*	Public Functions
	*
	**************************************************************************/
	public static String getErrorMessage(int index) {
		return m_errorMessages[index];
	}

	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	private static String m_errorMessages[] = {
		"Error: Ill-formed expression.",
		"Error: Fence mismatch detected - no closing fence.",
		"Error: Fence mismatch detected - no opening fence.",
		"Error: Fence mismatch detected - closing fence does not match " +
			"opening fence.",
		"Error: Not enough input tokens for the operator.",
		"Error: Unknown token."
	};
}