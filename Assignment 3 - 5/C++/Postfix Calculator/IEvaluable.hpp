/******************************************************************************
* IEvaluable.hpp
*	The `IEvaluable` class is the base class for all tokens that can be
*	evaluated to produce a floating point value.
*
******************************************************************************/
#pragma once

#include <string>

#include "IToken.hpp"

class IEvaluable : public IToken
{
public:
	/// @brief Standard constructor.
	/// @param token Specifies the string representation of the token.
	IEvaluable(std::string token);
	virtual ~IEvaluable() = default;

	/// @brief Evaluates the token.
	/// @returns Returns the floating point value of the token.
	virtual float evaluate() const = 0;

	/// @brief Converts the token to a string.
	/// @details By default, this function simply returns the string
	///		representation of the token. Tokens that require other tokens
	///		will need to override this function to return the string
	///		representation of the required tokens in addition to the string
	///		representation of the token itself.
	/// @returns Returns the string representation of the token.
	virtual std::string toString();

protected:
	/// @brief Stores the string representation of the token.
	const std::string m_token;
};

