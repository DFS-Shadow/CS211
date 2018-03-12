/******************************************************************************
* Operator.java
*	Defines a class used for implementing operators.
*
******************************************************************************/
public class Operator implements IToken, IEvaluable {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	public Operator(IBinaryFunctor functor, int precedence, 
		String operatorToken) {
		m_functor = functor;
		m_precedence = precedence;
		m_token = operatorToken;
	}

	/**************************************************************************
	*
	*	Public Member Functions
	*
	**************************************************************************/
	public void bindInputToken(int slot, IEvaluable token) {
		m_operands[slot] = token;
	}

	/// @brief Evaluates the token and returns its value.
	public float evaluate() {
		return m_functor.evaluate(m_operands[0].evaluate(),
			m_operands[1].evaluate());
	}

	/// @brief Returns the operator's precedence.
	public int getPrecedence() {
		return m_precedence;
	}

	/// @brief Returns the string representation of the token.
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		// Add the string representation of each operand
		stringbuilder.append(m_operands[0].toString() + " ");
		stringbuilder.append(m_operands[1].toString() + " ");
		// Add the string representation of this operator
		stringbuilder.append(m_token);

		return stringbuilder.toString();
	}
	
	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Stores the two operands to the operator.
	private IEvaluable m_operands[] = new IEvaluable[2];
	/// @brief Stores the string representation of the operator.
	private String m_token;
	/// @brief Stores the functor used to apply the operator.
	private IBinaryFunctor m_functor;
	/// @brief Stores the operator's precedence
	private int m_precedence;
}