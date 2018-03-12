#include "IFenceOperator.hpp"

IFenceOperator::IFenceOperator(std::string token, std::string closingFenceToken,
	int32_t precedence, uint32_t inputTokenCount) :
	IOperator(token, precedence, inputTokenCount),
	IFence(closingFenceToken)
{

}
