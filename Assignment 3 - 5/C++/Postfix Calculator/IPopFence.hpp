/******************************************************************************
* IPopFence.hpp
*	The `IPopFence` class is the base class for all tokens that force
*	Evaluators to pop operators from the output stack until an `IFence`-derived
*	operator is reached. `IPopFence`-derived tokens are never added to the
*	output stack or the operators stack.
*
******************************************************************************/
#pragma once

#include <string>

#include "IToken.hpp"

class IPopFence : public IToken
{
public:
	/// @brief Standard constructor.
	/// @param fenceToken String representation of the fence token. This must
	///		match the closing fence token string returned by the first fence
	///		within the operator stack; otherwise, the expression being
	///		evaluated is illformed.
	IPopFence(std::string fenceToken);
	virtual ~IPopFence() = default;
	/// @brief Returns the string representation of the token.
	const std::string& getFenceToken() const;

private:
	/// @brief Stores the fence token.
	const std::string m_fenceToken;
};

