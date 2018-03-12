/******************************************************************************
* PredefinedExpressions.java
*	`IExpressionSource`-derived class that provides predefined expressions.
*
******************************************************************************/
import java.util.ArrayList;

public class PredefinedExpressions implements IExpressionStream {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	public PredefinedExpressions() {
		m_expressions = new ArrayList<>();
		m_expressions.add("(1 + 3 * {2 - 1)}");
		m_expressions.add("(1 + 3 * 2) + (2 - 1)");
		m_expressions.add("(1 + 3 * {2 - 1)}");
		m_expressions.add("(1 + 3 * (2 - 1) (");
		m_expressions.add("((1 + 3) * (2 - 1)");
	}

	/**************************************************************************
	*
	*	Public Member Functions
	*
	**************************************************************************/
	/// @brief Returns whether additional expressions remain.
	public boolean hasNext() {
		return m_index < m_expressions.size();
	}

	/// @brief Returns the next expression and advances the stream to the
	///		next expression.
	public String get() {
		String expression = m_expressions.get(m_index);
		++m_index;
		return expression;
	}
	
	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Stores all expressions that can be returned.
	private ArrayList<String> m_expressions;
	/// @brief Stores the current index within the expressions ArrayList.
	private int m_index;
}