/******************************************************************************
* DatabasePredicate.java
*	This interface is used by the Database class to filter objects to return.
*
******************************************************************************/
public interface DatabasePredicate {
	/** Returns true if the element should be retrieved. */
	boolean process(DataElement element);
}