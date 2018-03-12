/******************************************************************************
* PrintPredicate.java
*	Predicate class used for printing out a comma-separated list of values.
*
******************************************************************************/
public class PrintPredicate implements IForeachPredicate {
	/// @brief Standard constructor.
	public PrintPredicate() {
		m_isFirst = true;
	}

	/// @brief Prints the value and adds commas as necessary.
	public void process(int data) {
		if (!m_isFirst) {
			System.out.print(", ");
		}
		System.out.print(data);
		m_isFirst = false;
	}

	/// @brief Tracks whether the current element is the first value.
	private boolean m_isFirst;
}