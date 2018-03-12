/******************************************************************************
* Division.java
*	This class implements the Division operation.
*
******************************************************************************/
public class Division extends BinaryOperation {
	public Division(IOperation leftOperand, IOperation rightOperand) {
		super(leftOperand, rightOperand);
	}

	/// @brief Returns the result of the Division operation.
	public float getValue() {
		return m_lOperand.getValue() / m_rOperand.getValue();
	}
}