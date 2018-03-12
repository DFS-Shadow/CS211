/******************************************************************************
* PassThroughPredicate.java
*	This class implements the `DatabasePredicate` interface and simply flags
*	all data elements for retrieval.
*
******************************************************************************/
public class PassThroughPredicate implements DatabasePredicate {
	/// @brief Standard constructor.
	public PassThroughPredicate() {

	}

	public boolean process(DataElement element) {
		return true;
	}
}