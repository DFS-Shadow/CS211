/******************************************************************************
* Stack.java
*	A class that implements the stack data type.
*
******************************************************************************/
import java.util.ArrayList;

public class Stack<T> {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	public Stack() {
		m_data = new ArrayList<>();
	}

	/**************************************************************************
	*
	*	Public Member Functions
	*
	**************************************************************************/
	/// @brief Returns whether the stack is empty or not.
	boolean isEmpty() {
		return m_data.isEmpty();
	}

	/// @brief Pushes an object onto the stack.
	void push(T obj) {
		m_data.add(obj);
	}

	/// @brief Returns the topmost object on the stack without removing it.
	T peek() {
		return m_data.get(m_data.size() - 1);
	}

	/// @brief Pops the topmost object on the stack and returns it.
	/// @details This also removes the object from the stack.
	T pop() {
		T obj = m_data.get(m_data.size() - 1);
		m_data.remove(m_data.size() - 1);
		return obj;
	}

	/// @brief Returns the number of elements in the stack.
	int size() {
		return m_data.size();
	}

	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Stores all objects pushed to the stack.
	ArrayList<T> m_data;
}