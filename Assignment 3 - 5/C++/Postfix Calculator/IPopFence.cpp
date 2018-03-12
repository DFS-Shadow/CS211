#include "IPopFence.hpp"

IPopFence::IPopFence(std::string fenceToken) :
	m_fenceToken(fenceToken)
{

}

const std::string & IPopFence::getFenceToken() const
{
	return m_fenceToken;
}
