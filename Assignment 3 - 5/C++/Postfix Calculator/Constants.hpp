/******************************************************************************
* Constants.hpp
*	Defines the various supported constants.
*
******************************************************************************/
#pragma once

#include <cmath>

#include "ConstantToken.hpp"

GENERATE_SPECIAL_CONSTANT(Pi, pi, std::atan(1.0f) * 4.0f)
GENERATE_SPECIAL_CONSTANT(E, e, std::exp(1.0f))