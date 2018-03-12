/******************************************************************************
* Main.java
*	Implements the main function for the program.
*
******************************************************************************/
public class Main {
	public static void main(String[] args) {
		IExpressionStream stream = new PredefinedExpressions();
		while (stream.hasNext()) {
			// Get the current expression
			String expression = stream.get();
			// Print and evaluate the expression
			System.out.println("Original Expression:\n" + expression + "\n");
			Parser parser = new Parser(expression);
			if (parser.isValid()) {
				System.out.println("Postfix Expression:\n" +
					parser.getPostfixExpression());
				System.out.println("Evaluated Result:\n" + parser.evaluate());
			} else {
				System.out.println("Errors: " + parser.getErrorCount());
				if (parser.getErrorCount() > 1) {
					System.out.println(
						"(Note: Only one error message is displayed at a time.)");
				}
			}
			System.out.println();
			System.out.println("=================================================================");
		}
	}
}