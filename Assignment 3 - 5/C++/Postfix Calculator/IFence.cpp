#include "IFence.hpp"

IFence::IFence(std::string closingFenceToken) :
	m_closingFenceToken(closingFenceToken)
{

}

const std::string & IFence::getClosingFenceToken() const
{
	return m_closingFenceToken;
}
