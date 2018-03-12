#include "IParser.hpp"

IParser::IParser(int32_t priority) :
	m_priority(priority)
{

}

int32_t IParser::getPriority() const
{
	return m_priority;
}
