#include "IOperator.hpp"

#include <cassert>
#include <sstream>

IOperator::IOperator(std::string token, int32_t precedence,
	uint32_t inputTokenCount) :
	IEvaluable(token),
	m_inputTokens(inputTokenCount),
	m_precedence(precedence),
	m_inputTokenCount(inputTokenCount)
{

}

void IOperator::bindInputToken(uint32_t slotID,
	std::unique_ptr<IEvaluable>&& token)
{
	assert(slotID < m_inputTokenCount && "Error: Invalid Slot ID.");
	m_inputTokens[slotID] = std::move(token);
}

uint32_t IOperator::getInputTokenCount() const
{
	return m_inputTokenCount;
}

int32_t IOperator::getOperatorPrecedence() const
{
	return m_precedence;
}

std::string IOperator::toString()
{
	// String stream used to construct the returned string
	std::stringstream stream;
	// Add the string representation of all input tokens
	for (auto& it : m_inputTokens)
	{
		stream << it->toString() << " ";
	}
	// Add the string representation of the current token
	stream << m_token;

	return stream.str();
}

IEvaluable & IOperator::getInputToken(uint32_t slotID) const
{
	assert(slotID < this->getInputTokenCount() &&
		"Error: Invalid slot ID.");
	auto& tokenPtr = m_inputTokens[slotID];
	assert(tokenPtr != nullptr &&
		"Error: No token bound in the specified slot.");
	return *tokenPtr;
}
