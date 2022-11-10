package Calculator;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.operate();

	}

	private void operate() {
		try (Scanner scan = new Scanner(System.in)) {
			Calculation calculation = new Calculation();
			System.out.println("Calculator opened");
			String problem="1";
			do {
				System.out.println("\n\n------------------------------------------------------------------------");
				System.out.println("| + | - | * | / | ^ | % | ! | pi | sqrt | cbrt | log | sin | cos | tan |");
				System.out.println("------------------------------------------------------------------------");
				System.out.println("Enter the problem..");
				try {
					problem = scan.nextLine();
					calculation.calculate(problem);
				} catch (NumberFormatException e) {
					System.out.print("Invalid problem.. try again");
				}
				catch (IllegalArgumentException e) {
					System.out.print("Invalid problem.. try again");
				}
				catch (StringIndexOutOfBoundsException e) {
					System.out.print("Problem has no terms.. try again");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.print("Invalid problem.. try again");
				}
				catch (ClassCastException e) {
					System.out.print("Invalid problem.. try again");
				}
			}while (!problem.equals("0"));
			System.out.print("\nCalculator closed");

		}
	}

}
