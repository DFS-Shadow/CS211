/******************************************************************************
* Multiplication.java
*	This class implements the Multiplication operation.
*
******************************************************************************/
public class Multiplication extends BinaryOperation {
	public Multiplication(IOperation leftOperand, IOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	/// @brief Returns the result of the Multiplication operation.
	public float getValue() {
		return m_lOperand.getValue() * m_rOperand.getValue();
	}
}