/******************************************************************************
* Heap.java
*	Defines a class that implements the Heap data structure.
*
******************************************************************************/
import java.util.ArrayList;

public class Heap {
	/**************************************************************************
	*
	*	Constructors
	*
	**************************************************************************/
	public Heap(ISortPredicate predicate) {
		m_predicate = predicate;
		m_rootNode = null;
		m_size = 0;
	}

	/**************************************************************************
	*
	*	Public Member Functions
	*
	**************************************************************************/
	/// @brief Adds data to the Heap.
	public void add(int data) {
		// Update the Heap's size
		++m_size;

		// If the root node is null, create the node and return
		if (m_rootNode == null) {
			m_rootNode = new Node(data);
			return;
		}

		foreachInternal(new AddNodePredicate(data));
	}

	/// @brief Checks whether the Heap is empty.
	public boolean isEmpty() {
		return m_size == 0;
	}

	/// @brief Allows for iteration over all nodes in the Heap.
	/// @details Iteration is done in breadth-first order.
	public void foreach(IForeachPredicate predicate) {
		// If the root node is null, return immediately
		if (m_rootNode == null) {
			return;
		}

		// Stores the list of nodes that still need to be visited.
		ArrayList<Node> openNodes = new ArrayList<>();
		openNodes.add(m_rootNode);

		while (!openNodes.isEmpty()) {
			// Pop the first node from the array
			Node currNode = openNodes.get(0);
			openNodes.remove(0);

			// Pass the data to the predicate
			predicate.process(currNode.value);

			// Add any child nodes of the current node to the list of nodes
			// to visit
			if (currNode.left != null) {
				openNodes.add(currNode.left);
			}
			if (currNode.right != null) {
				openNodes.add(currNode.right);
			}
		}
	}

	/// @brief Returns the element at the top of the Heap.
	public int top() {
		assert m_rootNode != null;
		return m_rootNode.value;
	}

	/// @brief Returns and removes the element at the top of the heap.
	public int pop() {
		--m_size;

		// If the size is now zero, then the only node remaining is the
		// root node
		if (m_size == 0) {
			int retVal = m_rootNode.value;
			m_rootNode = null;
			return retVal;
		}

		// Cache the value at the top of the Heap
		// Note: This function verifies that the Heap has at least one node.
		int retVal = top();
		// Get the last node in the heap.
		LastNodePredicate lastNodePredicate = new LastNodePredicate();
		foreachInternal(lastNodePredicate);
		Node lastNode = lastNodePredicate.getLastNode();
		// Move the last node's value into the root node
		m_rootNode.value = lastNode.value;
		// Remove the last node
		foreachInternal(new RemovePredicate(lastNode));
		// Re-sort the Heap
		foreachInternal(new SortPredicate(m_predicate));

		return retVal;
	}

	/// @brief Returns the size of the Heap.
	public int size() {
		return m_size;
	}

	/**************************************************************************
	*
	*	Private Nested Classes
	*
	**************************************************************************/
	/// @brief Class used to handle each node in the tree.
	private class Node {
		/// @brief Standard constructor.
		/// @param val Value to store in the node.
		public Node(int val) {
			value = val;
			left = null;
			right = null;
		}

		/// @brief Value stored by the node.
		int value;
		/// @brief Reference to the node's left child node (if it exists).
		Node left;
		/// @brief Reference to the node's right child node (if it exists).
		Node right;
	}

	/// @brief Interface used internally for iterating over the Heap.
	/// @details This predicate differs from the `IForeachPredicate` only by
	///		the argument passed to the `process()` function, which is the Node
	///		itself instead of the data held by the node.
	private interface IInternalPredicate {
		/// @brief Processes the node.
		void process(Node node);
	}

	/// @brief Predicate used for adding a node to the Heap.
	private class AddNodePredicate implements IInternalPredicate {
		/// @brief Standard constructor.
		/// @param data Data to store in the predicate.
		public AddNodePredicate(int data) {
			m_data = data;
			m_nodeAdded = false;
		}

		/// @brief Processes the node.
		public void process(Node node) {
			// If a node has already been added, return immediately.
			if (m_nodeAdded) {
				return;
			}

			// Compare the current value to the value stored by the node
			// to see if the two values need to be swapped
			if (m_predicate.compare(m_data, node.value)) {
				int temp = m_data;
				m_data = node.value;
				node.value = temp;
			}

			// If the node doesn't have a child node, add one (starting
			// with the left child node, if possible)
			if (node.left == null) {
				node.left = new Node(m_data);
				m_nodeAdded = true;
			} else if (node.right == null) {
				node.right = new Node(m_data);
				m_nodeAdded = true;
			}
		}

		/// @brief Stores the data to be added.
		private int m_data;
		/// @brief Tracks whether a node has been added.
		private boolean m_nodeAdded;
	}

	/// @brief Predicate used for getting the last node in the Heap.
	private class LastNodePredicate implements IInternalPredicate {
		/// @brief Standard constructor.
		public LastNodePredicate() {
			m_lastNode = null;
		}

		/// @brief Processes the node.
		public void process(Node node) {
			// Store the last node visited
			m_lastNode = node;
		}

		/// @brief Returns the last node in the Heap.
		public Node getLastNode() {
			return m_lastNode;
		}

		/// @brief Stores the last node visited.
		private Node m_lastNode;
	}

	/// @brief Predicate that iterates over the Heap and sorts it.
	private class SortPredicate implements IInternalPredicate{
		/// @brief Standard constructor.
		/// @param predicate Predicate to use for sorting.
		public SortPredicate(ISortPredicate predicate) {
			m_predicate = predicate;
		}

		/// @brief Processes the node.
		public void process(Node node) {
			// Compare the node's stored value to its child nodes
			if (node.left != null &&
				m_predicate.compare(node.left.value, node.value)) {
				// Swap the two values
				int temp = node.value;
				node.value = node.left.value;
				node.left.value = temp;
			}
			if (node.right != null &&
				m_predicate.compare(node.right.value, node.value)) {
				// Swap the two values
				int temp = node.value;
				node.value = node.right.value;
				node.right.value = temp;
			}
		}

		/// @brief Predicate used for sorting the Heap.
		private ISortPredicate m_predicate;
	}

	/// @brief Predicate used for removing a node from the Heap.
	private class RemovePredicate implements IInternalPredicate {
		/// @brief Standard constructor.
		/// @param node Reference to the node to remove. Must not have any
		///		child nodes.
		public RemovePredicate(Node node) {
			m_node = node;
		}

		public void process(Node node) {
			// Check if the node to remove is a child node of this node
			if (node.left != null && node.left == m_node) {
				node.left = null;
			}
			if (node.right != null && node.right == m_node) {
				node.right = null;
			}
		}

		/// @brief Reference to the node to remove.
		private Node m_node;
	}

	/**************************************************************************
	*
	*	Private Member Functions
	*
	**************************************************************************/
	/// @brief Internal foreach function.
	/// @details Iteration is done in breadth-first order.
	private void foreachInternal(IInternalPredicate predicate) {
		// If the root node is null, return immediately
		if (m_rootNode == null) {
			return;
		}

		// Stores the list of nodes that still need to be visited.
		ArrayList<Node> openNodes = new ArrayList<>();
		openNodes.add(m_rootNode);

		while (!openNodes.isEmpty()) {
			// Pop the first node from the array
			Node currNode = openNodes.get(0);
			openNodes.remove(0);

			// Pass the data to the predicate
			predicate.process(currNode);

			// Add any child nodes of the current node to the list of nodes
			// to visit
			if (currNode.left != null) {
				openNodes.add(currNode.left);
			}
			if (currNode.right != null) {
				openNodes.add(currNode.right);
			}
		}
	}

	/**************************************************************************
	*
	*	Private Member Variables
	*
	**************************************************************************/
	/// @brief Predicate to use for sorting the Heap.
	ISortPredicate m_predicate;
	/// @brief Reference to the root node.
	/// @details If the size of the Heap is zero, this will be null.
	Node m_rootNode;
	/// @brief Tracks the size of the Heap.
	int m_size;
}