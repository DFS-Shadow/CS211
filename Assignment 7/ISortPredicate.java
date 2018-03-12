/******************************************************************************
* ISortPredicate.java
*	Defines the interface for predicates used for sorting a Heap.
*
******************************************************************************/
public interface ISortPredicate {
	/// @brief Compares the two elements.
	boolean compare(int left, int right);
}