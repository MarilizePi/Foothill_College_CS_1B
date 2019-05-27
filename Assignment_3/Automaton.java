// Marilize Pires
// CS-1B / A3.


import java.util.Scanner;

public class Automaton {

	// class constants
	public final static int MAX_DISPLAY_WIDTH = 121;

	// private members
	private boolean rules[]; // allocate rules[8] in constructor!
	private String thisGen; // same here
	private String extremeBit; // bit, "*" or " ", implied everywhere "outside"
	private int displayWidth; // an odd number so it can be perfectly centered

	// public constructors, mutators, etc. (need to be written)
	public Automaton(int new_rule) {
		setDisplayWidth(79);
		resetFirstGen();
		setRule(new_rule);
	}

	public void resetFirstGen() {
		thisGen = "*";
		extremeBit = " ";
	}

	public String toStringCurrentGen() {
		String returnString = new String(thisGen);
		int distance = 0;
		int end = 0;
		while (returnString.length() != displayWidth) {

			if (returnString.length() < displayWidth) {
				returnString += extremeBit;
				returnString = extremeBit + returnString;
			}

			if (returnString.length() > displayWidth) {
				distance = (returnString.length() - displayWidth) / 2;
				end = returnString.length() - distance;
				returnString = returnString.substring(distance, end);
			}
		}
		return returnString;
	}

	public boolean setRule(int new_rule) {
		if (new_rule < 0 || new_rule > 255)
			return false;

		rules = new boolean[8];
		for (int i = 0; i < 8; i++) {
			rules[i] = (new_rule % 2 != 0);
			new_rule /= 2;
		}

		return true;
	}

	public boolean setDisplayWidth(int width) {
		if (width > MAX_DISPLAY_WIDTH || (width % 2 == 0) || width < 0)
			return false;
		displayWidth = width;
		return true;
	}

	public void propagateNewGeneration() {
		String nextGen = "";
		thisGen = extremeBit + extremeBit + thisGen + extremeBit + extremeBit;

		for (int i = 1; i < thisGen.length() - 1; i++) {
			String num = "";
			for (int j = -1; j < 2; j++) {
				if (thisGen.charAt(j + i) == '*')
					num += 1;
				else
					num += 0;
			}
			int k = Integer.parseInt(num, 2);
			if (rules[k])
				nextGen += "*";
			else
				nextGen += " ";
		}

		if (rules[0])
			extremeBit = "*";

		thisGen = nextGen;
	}
}
