/******************************************************************************
* IExpressionStream.java
*	Defines an interface for classes that provide an expression to be
*	evaluated.
*
******************************************************************************/
public interface IExpressionStream {
	/// @brief Returns whether additional expressions remain.
	public boolean hasNext();
	/// @brief Returns the next expression and advances the stream to the
	///		next expression.
	public String get();
}