// Marilize Pires
// CS-1B / A3.

import java.util.Scanner;

public class Foothill_A3 {
	public static void main(String[] args) {
		int rule, k;
		String userInput;

		Scanner input = new Scanner(System.in);
		Automaton aut;

		do {
			System.out.print("Enter Rule (0 - 255): ");

			userInput = input.nextLine();

			rule = Integer.parseInt(userInput);
		} while (rule < 0 || rule > 255);

		aut = new Automaton(rule);

		System.out.println("   start");
		for (k = 0; k < 37; k++) {
			System.out.println(aut.toStringCurrentGen());
			aut.propagateNewGeneration();
		}
		System.out.println("   end");
		input.close();
	}
}
