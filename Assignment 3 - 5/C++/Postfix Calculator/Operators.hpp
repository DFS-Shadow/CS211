/******************************************************************************
* Operators.hpp
*	Defines the various supported operators.
*
******************************************************************************/
#pragma once

#include <cmath>

#include "IOperator.hpp"

GENERATE_BINARY_OPERATOR(Addition, +, 1)
GENERATE_BINARY_OPERATOR(Subtraction, -, 1)
GENERATE_BINARY_OPERATOR(Multiplication, *, 2)
GENERATE_BINARY_OPERATOR(Division, / , 2)
GENERATE_UNARY_OPERATOR(Sine, sin, std::sin, 3)
GENERATE_UNARY_OPERATOR(Cosine, cos, std::cos, 3)
GENERATE_UNARY_OPERATOR(Tangent, tan, std::tan, 3)
GENERATE_UNARY_OPERATOR(Log, log, std::log10, 3)
GENERATE_UNARY_OPERATOR(Ln, ln, std::log, 3)