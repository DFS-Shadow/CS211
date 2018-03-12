/******************************************************************************
* UnaryOperation.java
*	The UnaryOperation class is intended to be the base class for all
*	arithmetic operations that take a single operand.
*
******************************************************************************/
public abstract class UnaryOperation implements IOperation {
	public UnaryOperation(IOperation operand) {
		m_operand = operand;
	}

	/// @brief Stores the single operand.
	protected IOperation m_operand;
}