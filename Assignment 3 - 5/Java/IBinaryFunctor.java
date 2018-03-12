/******************************************************************************
* BinaryFunctor.java
*	Defines an interface used for binary functors.
*
******************************************************************************/
public interface IBinaryFunctor {
	/// @brief Applies the functor and returns the result of the operation.
	public float evaluate(float l, float r);
}