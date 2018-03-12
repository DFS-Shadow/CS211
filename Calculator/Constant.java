/******************************************************************************
* Constant.java
*	This class is used for handling constant values.
*
******************************************************************************/
public class Constant implements IOperation {
	public Constant(float value) {
		m_value = value;
	}

	public float getValue() {
		return m_value;
	}

	/// @brief Stores the value held by this object.
	private final float m_value;
}