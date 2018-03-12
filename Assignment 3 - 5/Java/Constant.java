/******************************************************************************
* Constant.java
*	Defines an `IToken`-derived type used for implementing constant values.
*
******************************************************************************/
public class Constant implements IToken, IEvaluable {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	public Constant(float value) {
		m_value = value;
	}

	/**************************************************************************
	*
	*	Public Member Functions
	*
	**************************************************************************/
	/// @brief Evaluates the token and returns its value.
	public float evaluate() {
		return m_value;
	}

	/// @brief Returns the string representation of the token.
	public String toString() {
		return Float.toString(m_value);
	}

	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Stores the constant value assigned to the object.
	private float m_value;
}