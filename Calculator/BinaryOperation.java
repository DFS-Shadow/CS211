/******************************************************************************
* BinaryOperation.java
*	The BinaryOperation class is intended to be the base class for all
*	arithmetic operations that take two operands.
*
******************************************************************************/
public abstract class BinaryOperation implements IOperation {
	public BinaryOperation(IOperation leftOperand, IOperation rightOperand) {
		m_lOperand = leftOperand;
		m_rOperand = rightOperand;
	}

	/// @brief Stores the left operand.
	protected IOperation m_lOperand;
	/// @brief Stores the right operand.
	protected IOperation m_rOperand;
}