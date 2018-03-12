#include <iostream>
#include <string>

#include "Evaluator.hpp"
#include "Operators.hpp"
#include "Parsers.hpp"

int main()
{
	while (true)
	{
		system("cls");
		std::cout << "Please enter an expression:\n";
		std::string input;
		std::getline(std::cin, input);
		Evaluator evaluator(input);

		if (evaluator.isValid())
		{
			std::cout << "\nPostfix expression: " << evaluator.getPostfixExpression() << "\n";
			std::cout << "Evaluated value: " << evaluator.evaluate() << "\n";
		}
		system("pause");
	}
}