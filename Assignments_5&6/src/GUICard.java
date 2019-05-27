import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

class GUICard {

	private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K (+ joker optional)
	private static Icon iconBack;
	private static boolean iconsLoaded = false;

	public static final int NUM_SUITS = 4;
	public static final int NUM_VALUES = 14;

	private static String cardlValsConvertAssist = "23456789TJQKAX";
	private static String suitValsConvertAssist = "CDHS";
	private static Card.Suit suitConvertAssist[] = { Card.Suit.clubs, Card.Suit.diamonds, Card.Suit.hearts,
			Card.Suit.spades };

	// Method: store the Icons in a 2-D array
	public static void loadCardIcons() {
		// If Icons are already loaded (iconsLoaded == true), it does nothing.
		if (iconsLoaded)
			return;

		String imageFileName;
		int intSuit, intVal;

		for (intSuit = 0; intSuit < NUM_SUITS; intSuit++) {
			for (intVal = 0; intVal < NUM_VALUES; intVal++) {
				imageFileName = "images/" + turnIntIntoCardValueChar(intVal) + turnIntIntoCardSuitChar(intSuit)
						+ ".gif";

				iconCards[intVal][intSuit] = new ImageIcon(imageFileName); // icons in a 2-D array
			}
			imageFileName = "images/BK.gif"; // store card-back image
			iconCards[iconCards.length - 1][iconCards[0].length - 1] = new ImageIcon(imageFileName);
		}
		iconBack = iconCards[iconCards.length - 1][iconCards[0].length - 1]; // assign card-back to iconBack variable
		iconsLoaded = true;
	}

	// Method: turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
	static char turnIntIntoCardValueChar(int k) {
		if (k < 0 || k > 13)
			return '?';
		return cardlValsConvertAssist.charAt(k);
	}

	// Method: turns 0 - 3 into 'C', 'D', 'H', 'S'
	static char turnIntIntoCardSuitChar(int k) {
		if (k < 0 || k > 3)
			return '?';
		return suitValsConvertAssist.charAt(k);
	}

	// Method: turns 0 - 3 into 'clubs', 'diamonds', 'hearts', 'spades'
	public static Card.Suit turnIntIntoSuit(int k) {
		if (k < 0 || k > 3)
			return Card.Suit.spades;

		return suitConvertAssist[k];
	}

	// Method: take a Card object from the client, and return the Icon for that card
	static public Icon getIcon(Card card) {
		loadCardIcons(); // will not load twice, so no worries.
		return iconCards[valueAsInt(card)][suitAsInt(card)];
	}

	// Method: take a value(char) of Card and return the index (integer)
	static int valueAsInt(Card card) {
		for (int i = 0; i < NUM_VALUES; i++) {
			if (card.getValue() == cardlValsConvertAssist.charAt(i))
				return i;
		}
		return 0;
	}

	// Method: take a suit of Card and return the index (integer)
	static int suitAsInt(Card card) {
		for (int i = 0; i < NUM_SUITS; i++) {
			if (card.getSuit() == suitConvertAssist[i])
				return i;
		}
		return 0;
	}

	// Method: take a Card object from the client, and return card-back
	static public Icon getBackCardIcon() {
		return iconBack;
	}

}
