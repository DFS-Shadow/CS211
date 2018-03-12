/******************************************************************************
* IOperator.hpp
*	Operators are tokens that require other tokens as input in order to be
*	evaluated.
*
******************************************************************************/
#pragma once

#include <cstdint>
#include <memory>
#include <string>
#include <vector>

#include "IEvaluable.hpp"

class IOperator : public IEvaluable
{
public:
	/// @brief Standard constructor.
	/// @param token Specifies the string representation of the token.
	/// @param precedence Specifies the operator's precedence. Operators
	///		with a higher precedence value are evaluated before operators
	///		with lower operator precedence values.
	/// @param inputTokenCount Specifies the number of input tokens required
	///		by this operator.
	IOperator(std::string token, int32_t precedence = 0,
		uint32_t inputTokenCount = 0);
	virtual ~IOperator() = default;
	
	/// @brief Binds a token to the input token slot specified.
	/// @param slotID Slot ID to bind the token to. Must be in the range
	///		[0, inputTokenCount).
	void bindInputToken(uint32_t slotID, std::unique_ptr<IEvaluable>&& token);
	/// @brief Returns the number of input tokens required by this operator.
	/// @returns Returns the number of input tokens required by this operator.
	uint32_t getInputTokenCount() const;
	/// @brief Returns the operator's precedence value.
	/// @details Higher precedence operators are executed before lower
	///		precedence operators.
	/// @returns Returns the operator's precedence value.
	int32_t getOperatorPrecedence() const;
	/// @brief Converts the token to a string.
	/// @details The default `toString()` implementation is overridden to
	///		also print the string representations of operands.
	/// @returns Returns the string representation of the token.
	virtual std::string toString() override;

protected:
	/// @brief Retrieves an input token from the set of saved tokens.
	IEvaluable& getInputToken(uint32_t slotID) const;

private:
	/// @brief Stores input tokens for the operator.
	std::vector<std::unique_ptr<IEvaluable>> m_inputTokens;
	/// @brief Stores the operator's precedence value.
	const int32_t m_precedence;
	/// @brief Stores the required number of input tokens.
	const uint32_t m_inputTokenCount;
};

/// @brief Macro used for generating an `IOperator`-derived class for an unary
///		operator.
/// @param Name Name of the operator. "Operator" will be appended to the
///		provided name to generate the class name.
/// @param token Token to be stringized for the string representation of the
///		token.
/// @param Functor Functor to use for evaluating the operation.
/// @param Precedence Operator precedence value.
#define GENERATE_UNARY_OPERATOR(Name, Token, Functor, Precedence)			\
class Name##Operator : public IOperator										\
{																			\
public:																		\
	Name##Operator(const std::string&) :									\
		IOperator(#Token, Precedence, 1)									\
	{																		\
																			\
	}																		\
	virtual float evaluate() const override									\
	{																		\
		return Functor(getInputToken(0).evaluate());						\
	}																		\
};

/// @brief Macro used for generating an `IOperator`-derived class for a binary
///		operator.
/// @param Name Name of the operator. "Operator" will be appended to the
///		provided name to generate the class name.
/// @param Token Token to use for the operator. This is used internally for
///		the evaluation function, while the stringized token is used as the
///		`IToken` constructor argument.
/// @param Precedence Operator precedence value.
#define GENERATE_BINARY_OPERATOR(Name, Token, Precedence)					\
class Name##Operator : public IOperator										\
{																			\
public:																		\
	Name##Operator(const std::string&) :									\
		IOperator(#Token, Precedence, 2)									\
	{																		\
																			\
	}																		\
																			\
	virtual float evaluate() const override									\
	{																		\
		return getInputToken(0).evaluate() Token							\
			getInputToken(1).evaluate();									\
	}																		\
};