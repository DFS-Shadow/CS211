/******************************************************************************
* Equation.java
*	This class encapsulates the logic required for solving equations.
*
******************************************************************************/
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equation {
	public Equation() {
		m_operationPatterns = new ArrayList<>();
		// The following patterns for operations are designed to break the
		// string up along the *last* operation to execute. Because the use
		// of recursion effectively creates a depth first algorithm, order
		// of operations must be followed backwards in order to correctly
		// calculate the answer.

		// Pattern for matching addition or subtraction
		m_operationPatterns.add(Pattern.compile("(.*)([\\+-])(.*?.*)"));
		// Pattern for matching multiplication or division
		m_operationPatterns.add(Pattern.compile("(.*)([\\*/])(.*?.*)"));
		// Pattern used for matching constants
		// This will match both integers as well as floats
		m_operationPatterns.add(Pattern.compile("[\\d\\.]+"));
	}

	/// @brief Returns the result of the equation.
	public float solveEquation(String equation) {
		IOperation operation = generateOperation(equation);
		return operation.getValue();
	}

	/// @brief Constructs an `IOperation` object from the equation.
	private IOperation generateOperation(String equation) {
		// Iterate through each pattern and attempt to find a match
		for (Pattern pattern : m_operationPatterns) {
			// Check for a match
			Matcher matcher = pattern.matcher(equation);
			if (!matcher.find()) {
				continue;
			}

			// A match was found
			// Check the number of groups that were found - if no groups
			// were found, then the current string should be interpreted
			// as a number.
			if (matcher.groupCount() == GROUP_COUNT_CONSTANT_ONLY) {
				return new Constant(Float.parseFloat(matcher.group(GROUP_INDEX_CONSTANT)));
			}
			// Multiple groups were found
			// Create an IOperation object for the left and right
			// operand
			String leftGroup = matcher.group(GROUP_INDEX_LEFT_OPERAND);
			String rightGroup = matcher.group(GROUP_INDEX_RIGHT_OPERAND);
			IOperation leftOperand = generateOperation(leftGroup);
			IOperation rightOperand = generateOperation(rightGroup);
			// Construct the correct `IOperation`-derived class
			// This is based on the operation located by the matcher
			String operation = matcher.group(GROUP_INDEX_OPERATION);
			switch (operation) {
			case "+":
				return new Addition(leftOperand, rightOperand);
			case "-":
				return new Subtraction(leftOperand, rightOperand);
			case "*":
				return new Multiplication(leftOperand, rightOperand);
			case "/":
				return new Division(leftOperand, rightOperand);
			}
		}

		assert false;
		return new Constant(0.0f);
	}

	/// @brief Group count that occurs when only a constant remains
	private static final int GROUP_COUNT_CONSTANT_ONLY = 0;
	/// @brief Group index for a constant value
	private static final int GROUP_INDEX_CONSTANT = 0;
	/// @brief Group index for the left operand
	private static final int GROUP_INDEX_LEFT_OPERAND = 1;
	/// @brief Group index for the operation itself
	private static final int GROUP_INDEX_OPERATION = 2;
	/// @brief Group index for the right operand
	private static final int GROUP_INDEX_RIGHT_OPERAND = 3;
	/// @brief Stores patterns used for identifying operations.
	private ArrayList<Pattern> m_operationPatterns;
}