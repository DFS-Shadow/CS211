/******************************************************************************
* IToken.hpp
*	Base class for all token classes. Tokens are any set of characters in an
*	expression that make up a singular component of the expression, such as
*	a constant, an operator, or a mathematical operation.
*
******************************************************************************/
#pragma once

#include <string>

class IToken
{
public:
	IToken() = default;
	virtual ~IToken() = default;
};