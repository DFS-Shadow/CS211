/******************************************************************************
* Addition.java
*	This class implements the addition operation.
*
******************************************************************************/
public class Addition extends BinaryOperation {
	public Addition(IOperation leftOperand, IOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	/// @brief Returns the result of the addition operation.
	public float getValue() {
		return m_lOperand.getValue() + m_rOperand.getValue();
	}
}