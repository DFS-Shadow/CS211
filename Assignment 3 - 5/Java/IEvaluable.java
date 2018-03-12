/******************************************************************************
* IEvaluable.java
*	The `IEvaluable` interface is used for any token that can be evaluated
*	and printed.
*
******************************************************************************/
public interface IEvaluable {
	/**************************************************************************
	*
	*	Public Member Functions
	*
	**************************************************************************/
	/// @brief Evaluates the token and returns its value.
	public float evaluate();
	/// @brief Returns the string representation of the token.
	public String toString();
}