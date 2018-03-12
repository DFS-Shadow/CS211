#include "Evaluator.hpp"

#include <cassert>
#include <cmath>
#include <iostream>

#include "IEvaluable.hpp"
#include "IFence.hpp"
#include "IFenceOperator.hpp"
#include "IOperator.hpp"
#include "IPopFence.hpp"
#include "ParserManager.hpp"

Evaluator::Evaluator(std::string expression) :
	m_infixExpression(expression),
	m_expression(expression)
{
	auto& parserManager = ParserManager::getSingleton();
	// Continually apply parsers to the expression until
	// the expression has been completely decomposed into
	// tokens
	while (m_expression.size() > 0)
	{
		std::vector<std::unique_ptr<IToken>> tokens =
			parserManager.parseExpression(m_expression);

		// Check if any tokens were generated
		if (tokens.size() == 0)
		{
			// If the expression is empty, then only whitespace remained prior
			// to the call to `parseExpression()`
			if (m_expression.empty())
			{
				break;
			}

			writeErrorMessage("Unknown token.");
			return;
		}

		// Process the parsed tokens
		for (auto& it : tokens)
		{
			// Get the pointer from the unique pointer
			IToken* tokenPtr = it.release();
			// Determine the type of the pointer, then call the appropriate
			// function for processing the token
			if (IPopFence* ptr = dynamic_cast<IPopFence*>(tokenPtr))
			{
				processPopFence(std::unique_ptr<IPopFence>(ptr));
			}
			else if (IFence* ptr = dynamic_cast<IFence*>(tokenPtr))
			{
				processFence(std::unique_ptr<IFence>(ptr));
			}
			else if (IOperator* ptr = dynamic_cast<IOperator*>(tokenPtr))
			{
				processOperator(std::unique_ptr<IOperator>(ptr));
			}
			else if (IEvaluable* ptr = dynamic_cast<IEvaluable*>(tokenPtr))
			{
				processEvaluable(std::unique_ptr<IEvaluable>(ptr));
			}
			else
			{
				// This should only be hit if a new token type is introduced
				assert(false && "Unknown token type.");
			}
		}
	}

	// Once all tokens have been processed, pop any operators remaining
	// on the operator stack and link them
	clearOperatorStack();

	// Only one token should remain on the output stack
	if (m_outputStack.size() != 1)
	{
		writeErrorMessage("Ill-formed expression detected.\n"
			"Did you forget an operator?");
		return;
	}
	// Check if an error was encountered
	if (!m_isValid)
	{
		return;
	}
	// Get a string representation of the postfix-notation expression
	auto& token = m_outputStack.top();
	m_postfixExpression = token->toString();
}

float Evaluator::evaluate()
{
	// A properly formed expression should result in only one token remaining
	// on the output stack
	assert(m_outputStack.size() == 1 &&
		"Error: Expected only one token in the output stack.");
	// Get a reference to the top token on the stack
	auto& token = m_outputStack.top();
	// Round the value to the four decimal points of precision
	return std::round(token->evaluate() * 10000.0f) / 10000.0f;
}

const std::string & Evaluator::getInfixExpression() const
{
	return m_infixExpression;
}

const std::string & Evaluator::getPostfixExpression() const
{
	return m_postfixExpression;
}

bool Evaluator::isValid() const
{
	// If the expression is valid, then exactly one token should
	// be stored in the 
	return m_isValid;
}

void Evaluator::clearOperatorStack()
{
	// Pop fence tokens are processed by popping tokens from the operator stack
	// unti an `IFence`-derived token is encountered
	while (!m_operatorStack.empty())
	{
		// Get the first operator
		IToken* operatorPtr = m_operatorStack.top().release();
		m_operatorStack.pop();
		// If the token derives from the `IOperator`-class, link input tokens
		// to the token
		if (auto* ptr = dynamic_cast<IOperator*>(operatorPtr))
		{
			// Link tokens to the operator, then place the operator on the
			// output stack
			linkToken(*ptr);
			m_outputStack.emplace(ptr);
		}

		// If the token is a Fence-derived token, a fence mismatch occurred.
		if (auto* ptr = dynamic_cast<IFence*>(operatorPtr))
		{
			writeErrorMessage("Expected a " +
				ptr->getClosingFenceToken() + " token.");
		}
	}
}

void Evaluator::clearOperatorStack(const IPopFence & popFenceToken)
{
	// Pop fence tokens are processed by popping tokens from the operator stack
	// unti an `IFence`-derived token is encountered
	while (!m_operatorStack.empty())
	{
		// Get the first operator
		IToken* operatorPtr = m_operatorStack.top().release();
		m_operatorStack.pop();
		// If the token derives from the `IOperator`-class, link input tokens
		// to the token
		if (auto* ptr = dynamic_cast<IOperator*>(operatorPtr))
		{
			// Link tokens to the operator, then place the operator on the
			// output stack
			linkToken(*ptr);
			m_outputStack.emplace(ptr);
		}

		// If the token is a Fence-derived token, verify that the fence expects
		// the providied pop fence token, then break out of the loop
		if (auto* ptr = dynamic_cast<IFence*>(operatorPtr))
		{
			if (ptr->getClosingFenceToken() != popFenceToken.getFenceToken())
			{
				writeErrorMessage("Mismatched closing " +
					popFenceToken.getFenceToken() + " token.");
				return;
			}
			break;
		}
	}
}

void Evaluator::processFence(std::unique_ptr<IFence>&& token)
{
	// Fence tokens are simply immediately placed on the operator stack
	
	// Convert the token object to a std::unique_ptr<IToken>
	std::unique_ptr<IToken> ptr(token.release());
	m_operatorStack.push(std::move(ptr));
}

void Evaluator::processPopFence(std::unique_ptr<IPopFence>&& token)
{
	clearOperatorStack(*token);
}

void Evaluator::processOperator(std::unique_ptr<IOperator>&& token)
{
	if (!m_operatorStack.empty())
	{
		// Get the operator on the top of the operator stack
		IToken* topToken = m_operatorStack.top().get();
		// If the token is an `IOperator`-derived type, check its precedence
		if (auto* ptr = dynamic_cast<IOperator*>(topToken))
		{
			// If the top operator's precedence is greater than the current
			// operator's precedence, the stack should be cleared
			if (ptr->getOperatorPrecedence() > token->getOperatorPrecedence())
			{
				clearOperatorStack();
			}
		}
	}
	// Add the operator to the operators stack
	m_operatorStack.emplace(std::move(token));
}

void Evaluator::processEvaluable(std::unique_ptr<IEvaluable>&& token)
{
	// Evaluables are just immediately placed on the output stack
	m_outputStack.emplace(std::move(token));
}

void Evaluator::linkToken(IOperator & token)
{
	// Pull the required number of input tokens off of the output stack
	for (int i = token.getInputTokenCount(); i > 0; --i)
	{
		if (m_outputStack.empty())
		{
			writeErrorMessage("Not enough input tokens.");
			return;
		}
		// The input token slot to use is one less than `i` because inpupt
		// token slots are zero-indexed.
		int inputTokenIndex = i - 1;
		// Get the first token on the output stack
		std::unique_ptr<IEvaluable> inputToken(std::move(m_outputStack.top()));
		m_outputStack.pop();
		// Bind the token as an input token
		token.bindInputToken(inputTokenIndex, std::move(inputToken));
	}
}

size_t Evaluator::getCurrentIndex(const std::string & partial)
{
	// If the partial string is empty, the index location will be the size
	// of the initial string. Otherwise, the index location is the index at
	// which the partial string starts. One is subtracted from the index
	// location to ensure that the carat character is placed underneath the
	// error location.
	return ((partial.empty()) ? m_infixExpression.size() :
		m_infixExpression.find(partial)) - 1;
}

void Evaluator::writeErrorMessage(const std::string & message)
{
	// Get the current location within the original expression
	size_t location = getCurrentIndex(m_expression);
	// Flag the expression as invalid
	m_isValid = false;
	// Print up to 80 characters at a time
	const int MAX_CHARS_PER_LINE = 80;
	for (int i = 0; i < m_infixExpression.size(); i += MAX_CHARS_PER_LINE)
	{
		std::cout << m_infixExpression.substr(i, MAX_CHARS_PER_LINE) << "\n";
		// Check if the error occurred on the line currently printed
		if (i <= location && location < i + MAX_CHARS_PER_LINE)
		{
			// Calculate the offset between the first character and
			// the error location
			size_t offset = location - i;
			// Construct the string to output
			std::string errorMessage(offset, ' ');
			errorMessage += "^~~ " + message;
			std::cout << errorMessage << "\n";
		}
	}
	std::cout << "\n";
}
