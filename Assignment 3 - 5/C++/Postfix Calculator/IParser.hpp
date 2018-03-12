/******************************************************************************
* IParser.hpp
*	The `IParser` class defines the interface used by Parser classes. Parser
*	classes are classes that iterate over and tokenize a string expression
*	into `IToken`-based objects that can be evaluated by the `Evalutator`
*	class.
*
******************************************************************************/
#pragma once

#include <cstdint>
#include <memory>
#include <string>
#include <vector>

#include "IToken.hpp"

class IParser
{
public:
	/// @brief Standard constructor.
	/// @param priority Priority level of the Parser. Higher priority parsers
	///		are tested first.
	IParser(int32_t priority);
	/// @brief Returns the priority level of the parser.
	int32_t getPriority() const;
	/// @brief Parses the string and extracts tokens from the expression, if
	///		possible.
	/// @details If the parser is unable to extract tokens from the expression,
	///		the expression string will remain unmodified. If the parser is
	///		able to extract tokens, the parser will remove the associated
	///		characters from the string.
	/// @returns Returns a vector containing any extracted tokens.
	virtual std::vector<std::unique_ptr<IToken>> parse(
		std::string& expression) = 0;

private:
	/// @brief Stores the priority level of the parser.
	int32_t m_priority;
};