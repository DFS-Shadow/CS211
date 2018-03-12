#include "ParserManager.hpp"

#include <cassert>
#include <cctype>

ParserManager & ParserManager::getSingleton()
{
	static ParserManager singleton;
	return singleton;
}

std::vector<std::unique_ptr<IToken>> ParserManager::parseExpression(
	std::string & expression)
{
	// Vector to return
	std::vector<std::unique_ptr<IToken>> retVal;
	// Remove any leading whitespace
	removeLeadingWhitespace(expression);

	// If the only remaining characters in the expression were whitespace
	// characters, return immediately.
	if (expression.size() == 0)
	{
		return retVal;
	}

	// Iterate over all parsers
	for (auto& it : m_parsers)
	{
		// Pass the expression to the parser
		retVal = it->parse(expression);
		// If the parser returned tokens, don't check any additional
		// parsers and just return the generated tokens
		if (!retVal.empty())
		{
			return retVal;
		}
	}

	// If this point is reached, no parser was able to parse the given
	// expression
	return retVal;
}

void ParserManager::removeLeadingWhitespace(std::string & str)
{
	// Variable used to track the last index to remove from the string
	int i = 0;
	for (; i < str.size(); ++i)
	{
		// Continue until a non-whitespace character is detected
		if (!std::isspace(str[i]))
		{
			break;
		}
	}

	// If leading whitespace was detected, remove the whitespace characters
	if (i != 0)
	{
		str = str.substr(i);
	}
}
