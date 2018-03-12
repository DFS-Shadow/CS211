# Assignment #3-5 - CS211, Bellevue College, Winter 2018
The purpose of this assignment was to parse a string containing a mathematical
expression and evaluate it. The input expression uses the traditional
[Infix notation](https://en.wikipedia.org/wiki/Infix_notation) and is
transformed to [Postfix notation](https://en.wikipedia.org/wiki/Reverse_Polish_notation)
before being evaluated.

# Algorithm Overview
This program reads in an infix-notation expression and generates tokens from
the expression. In the C++ version, regexes are used to identify which tokens
should be generated from the input. In the Java version, a more simple
character comparison approach is used to identify the type of token to
generate.

Once tokens have been generated, the tokens are passed to the parser for
processing. The parser implements infix-to-postfix conversion using Edsger
Dijkstra's [Shunting-Yard Algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm).
In the Java version of the program, four types of tokens exist - Constants,
Operators, Fences, and Pop-Fences. The C++ version also supports special
mathematical functions such as sine, cosine, and ln, and therefore has
an extra token type known as a Fence-Operator.

Fences and Pop-Fences are used to control operator evaluation order beyond
simple mathematical precedence. When a fence token is encountered, the
token is pushed onto the operators stack, regardless of what operators are
currently on the stack. When a pop-fence token is encountered, the parser
immediately begins popping operators off of the operators stack until it
encounters a fence token (or runs out of operators). Once a fence token is
encountered, the parser removes the fence token and stops popping tokens
from the operators stack.

When an operator is popped from the operators stack, it is linked with
the top X tokens on the output stack, where X is the number of arguments
the operator requires. Linked tokens are removed from the output stack
and are stored by the operator token instead.

Because linked tokens are removed from the output stack, valid expressions
will always result in exactly one token remaining on the output stack once
parsing is complete. This token links to any required input tokens, which
in turn link to other tokens. This design generates a tree used for expression
evaluation, eliminating the need for an array and copy-movement of tokens
during evaluation. This tree structure also makes evaluation and printing
of tokens extremely easy, via recursive function calls.