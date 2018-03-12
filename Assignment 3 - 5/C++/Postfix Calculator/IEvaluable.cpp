#include "IEvaluable.hpp"

IEvaluable::IEvaluable(std::string token) :
	m_token(token)
{

}

std::string IEvaluable::toString()
{
	return m_token;
}
