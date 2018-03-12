/******************************************************************************
* IFenceOperator.hpp
*	The `IFenceOperator` class is used for operators that require input
*	arguments surrounded by parentheses (ie sin, cos, log). Fence Operators
*	combine the functionality of an `IFence` with the functionality of an
*	`IOperator`.
*
*	This class can be used as a drop-in replacement to the `IOperator` class
*	whenever `IFence`-like functionality is required.
*
******************************************************************************/
#pragma once

#include <string>

#include "IFence.hpp"
#include "IOperator.hpp"

class IFenceOperator : public IFence, public IOperator
{
public:
	/// @brief Standard constructor.
	/// @param token Specifies the string representation of the token.
	/// @param closingFenceToken Specifies the associated pop-fence token.
	/// @param precedence Specifies the operator's precedence. Operators
	///		with a higher precedence value are evaluated before operators
	///		with lower operator precedence values.
	/// @param inputTokenCount Specifies the number of input tokens required
	///		by this operator.
	IFenceOperator(std::string token, std::string closingFenceToken,
		int32_t precedence = 0,	uint32_t inputTokenCount = 0);
	virtual ~IFenceOperator() = default;
};

