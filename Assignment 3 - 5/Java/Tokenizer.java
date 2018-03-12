/******************************************************************************
* Tokenizer.java
*	The `Tokenizer` class generates `IToken`-derived objects from a character
*	stream.
*
******************************************************************************/
public class Tokenizer {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	public Tokenizer(String expression) {
		m_stream = new StringStream(expression);
	}

	/**************************************************************************
	*
	*	Public Member Functions
	*
	**************************************************************************/
	/// @brief Checks whether any characters have yet to be tokenized.
	public boolean hasNext() {
		return !m_stream.atEnd();
	}

	public int getErrorCount() {
		return m_errorCount;
	}

	/// @brief Retrieves the next token.
	public IToken getNextToken() {
		// If the current character is whitespace, ignore it
		while (m_stream.peek() == ' ') {
			m_stream.get();
		}

		if (isDigit(m_stream.peek())) {
			return readNumber();
		} else if (isOperator(m_stream.peek())) {
			return readOperator();
		} else if (isFence(m_stream.peek())) {
			return readFence();
		} else {
			// Print the Unknown Token error message
			printErrorMessage(5, "Token: " + m_stream.get());
			return null;
		}
	}

	/// @brief Prints the specified error message at the current position.
	/// @param errorID ID of the error to print.
	public void printErrorMessage(int errorID) {
		printErrorMessage(errorID, "");
	}

	/// @brief Prints the specified error message at the current position.
	/// @param errorID ID of the error to print.
	/// @param extraInfo Additional information to print.
	public void printErrorMessage(int errorID, String extraInfo) {
		++m_errorCount;
		// If an error has already been displayed, don't display another;
		// just log it
		if (m_errorCount > 1) {
			return;
		}

		// Calculate where the expression string needs to be placed
		StringBuilder errorString = new StringBuilder();
		// 1 is subtracted from the stream's index in order to place
		// the marker underneath the character at which the error was
		// detected instead of one character after
		for (int i = 0; i < m_stream.getIndex() - 1; ++i) {
			errorString.append(' ');
		}
		errorString.append("^~~ ");
		errorString.append(Errors.getErrorMessage(errorID));
		// Print the expression string and error message
		System.out.println(m_stream.getString());
		System.out.println(errorString.toString());
		// If extra information was provided, print that too
		if (!extraInfo.isEmpty()) {
			System.out.println(extraInfo);
		}
		System.out.println();
	}

	/**************************************************************************
	*
	*	Private Member Functions
	*
	**************************************************************************/
	/// @brief Checks if the current character is a digit.
	private boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}

	/// @brief Checks if the current character is an operator.
	private boolean isOperator(char c) {
		return c == '+' || c == '-' ||
			c == '*' || c == '/';
	}

	/// @brief Checks if the current character is a fence character.
	private boolean isFence(char c) {
		return c == '(' || c == ')' ||
			c == '{' || c == '}';
	}

	private IToken readNumber() {
		// String used to store all digits
		String number = new String();
		while (!m_stream.atEnd() && isDigit(m_stream.peek())) {
			number += m_stream.get();
		}

		// Create a constant token to store the number
		return new Constant(Float.parseFloat(number));
	}

	private IToken readOperator() {
		// Get the operator character
		char operatorChar = m_stream.get();
		// Create the correct Operator object from the operator character
		Operator operator = null;
		switch (operatorChar) {
		case '+':
			operator = new Operator((l, r) -> l + r, 1, "+");
			break;
		case '-':
			operator = new Operator((l, r) -> l - r, 1, "-");
			break;
		case '*':
			operator = new Operator((l, r) -> l * r, 2, "*");
			break;
		case '/':
			operator = new Operator((l, r) -> l / r, 2, "/");
			break;
		default:
			// Print the Unknown Token error message
			printErrorMessage(5, "Token: " + operatorChar);
		}

		return operator;
	}

	private IToken readFence() {
		// Read the current character
		char fenceChar = m_stream.get();
		// Create the correct Fence object from the fence character
		IToken fence = null;
		switch (fenceChar) {
		case '(':
			fence = new Fence(')');
		break;
		case ')':
			fence = new PopFence(')');
		break;
		case '{':
			fence = new Fence('}');
		break;
		case '}':
			fence = new PopFence('}');
		break;
		default:
			// Print the Unknown Token error message
			printErrorMessage(5, "Token: " + fenceChar);
		}

		return fence;
	}

	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Stream used by the Tokenizer to read the string.
	private StringStream m_stream;
	/// @brief Tracks the number of errors encountered.
	private int m_errorCount = 0;
}