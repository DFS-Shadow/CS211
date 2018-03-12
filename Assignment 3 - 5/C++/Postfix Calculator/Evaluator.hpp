/******************************************************************************
* Evaluator.hpp
*	The `Evaluator` class accepts a string expression and generates a series
*	of tokens to evaluate the expression.
*
******************************************************************************/
#pragma once

#include <memory>
#include <stack>
#include <string>
#include <vector>

#include "IEvaluable.hpp"
#include "IFence.hpp"
#include "IOperator.hpp"
#include "IPopFence.hpp"
#include "IToken.hpp"

class Evaluator
{
public:
	/// @brief Standard Constructor.
	/// @param expression A string containing an Infix-notation expression
	///		to be evaluated.
	Evaluator(std::string expression);
	/// @brief Processes and evaluates the expression provided to the class
	///		constructor.
	/// @returns Returns the value of the expression.
	float evaluate();
	/// @brief Returns the original expression passed to this Evaluator.
	const std::string& getInfixExpression() const;
	/// @brief Returns the postfix-notation equivalent of the provided infix
	///		notation expression.
	const std::string& getPostfixExpression() const;
	/// @brief Checks if the expression can be evaluated.
	bool isValid() const;

private:
	/// @brief Clears the operator stack by popping operator tokens from the
	///		stack.
	///	@details This function stops as soon as an `IFence`-derived token is
	///		encountered.
	void clearOperatorStack();
	/// @brief Clears the operator stack by popping operator tokens from the
	///		stack.
	/// @details This overload verifies that the fence token that stops this
	///		function expects the pop-fence token provided.
	///	@details This function stops as soon as an `IFence`-derived token is
	///		encountered.
	void clearOperatorStack(const IPopFence& popFenceToken);
	/// @brief Function used for processing Fence tokens.
	/// @param token Token to be processed.
	void processFence(std::unique_ptr<IFence>&& token);
	/// @brief Function used for processing Pop-Fence tokens.
	/// @param token Token to be processed.
	void processPopFence(std::unique_ptr<IPopFence>&& token);
	/// @brief Function used for processing Operator tokens.
	/// @param token Token to be processed.
	void processOperator(std::unique_ptr<IOperator>&& token);
	/// @brief Function used for processing Evaluable tokens.
	/// @param token Token to be processed.
	void processEvaluable(std::unique_ptr<IEvaluable>&& token);
	/// @brief Pops the necessary number of tokens from the output stack and
	///		links them with the given token.
	/// @param token Token to link input tokens to.
	void linkToken(IOperator& token);
	/// @brief Determines the current location within the original expression.
	///	@param partial String currently being processed. Because characters
	///		will be removed from the string as tokens are extracted, this
	///		string will be a partial string compared to the original string.
	/// @returns Returns the index within the original string corresponding
	///		to the start of the partial string.
	size_t getCurrentIndex(const std::string& partial);
	/// @brief Outputs an error message with debugging information.
	/// @param message Error message to output.
	///	@param location Index within the expression to print the error message
	///		at.
	void writeErrorMessage(const std::string& message);

	/// @brief Contains the original, infix-notation expression provided.
	const std::string m_infixExpression;
	/// @brief Contains the string representation of the expression after
	///		it's been transformed into postfix notation.
	std::string m_postfixExpression;
	/// @brief Contains the string expression currently being processed.
	/// @details Characters corresponding to extracted tokens are removed from
	///		this string.
	std::string m_expression;
	/// @brief Stack used for storing output tokens.
	/// @details Only `IEvaluable`-based tokens are ever placed on the output
	///		stack.
	std::stack<std::unique_ptr<IEvaluable>> m_outputStack;
	/// @brief Stack used for storing operator tokens.
	/// @details The only tokens that go on the operator stack are `IOperator`
	///		and `IFence`-derived tokens.
	std::stack<std::unique_ptr<IToken>> m_operatorStack;
	/// @brief Tracks whether an error was encountered.
	bool m_isValid = true;
};