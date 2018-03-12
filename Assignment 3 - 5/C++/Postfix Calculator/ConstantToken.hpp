/******************************************************************************
* ConstantToken.hpp
*	The `ConstantToken` class is used to store constant values.
*
******************************************************************************/
#pragma once

#include <string>

#include "IEvaluable.hpp"

class ConstantToken : public IEvaluable
{
public:
	/// @brief Standard constructor.
	/// @param token String representation of the token. This must be a string
	///		that can be converted to a float.
	ConstantToken(std::string token);
	/// @brief Secondary constructor.
	/// @param token Specifies the string representation of the token.
	/// @param value Value to be stored by the token.
	ConstantToken(std::string token, float value);

	/// @brief Evaluates the token.
	/// @returns Returns the floating point value of the token.
	virtual float evaluate() const override;

private:
	/// @brief Value stored by the token.
	float m_value;
};

/// @brief Macro used for generating a special (named) constant.
/// @param Name Name to use for the class name. "Constant" will be appended
///		to the provided name.
/// @param Token Token to stringize and use to display the constant.
/// @param Value Floating point value to use for the constant.
#define GENERATE_SPECIAL_CONSTANT(Name, Token, Value)						\
class Name##Constant : public ConstantToken									\
{																			\
public:																		\
	template <typename... Ts>												\
	Name##Constant(const Ts&...) :											\
		ConstantToken(#Token, Value)										\
	{																		\
																			\
	}																		\
};