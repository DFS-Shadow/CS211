#include "ConstantToken.hpp"

ConstantToken::ConstantToken(std::string token) :
	ConstantToken(token, std::stof(token))
{

}

ConstantToken::ConstantToken(std::string token, float value) :
	IEvaluable(token),
	m_value(value)
{

}

float ConstantToken::evaluate() const
{
	return m_value;
}
