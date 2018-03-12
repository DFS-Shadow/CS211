/******************************************************************************
* Subtraction.java
*	This class implements the Subtraction operation.
*
******************************************************************************/
public class Subtraction extends BinaryOperation {
	public Subtraction(IOperation leftOperand, IOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	/// @brief Returns the result of the Subtraction operation.
	public float getValue() {
		return m_lOperand.getValue() - m_rOperand.getValue();
	}
}