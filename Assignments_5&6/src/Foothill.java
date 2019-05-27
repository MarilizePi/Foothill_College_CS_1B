import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Foothill {
	static int NUM_CARDS_PER_HAND = 7;
	static int NUM_PLAYERS = 2;
	static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
	static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
	static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
	static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];

	
	//phase 2
	public static void main(String[] args) {
		int k;
		ImageIcon tempIcon;

		// establish main frame in which program will run
		CardTable myCardTable = new CardTable("CS 1B CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
		myCardTable.setName("CS 1B CardTable");
		myCardTable.setSize(800, 600);
		myCardTable.setLocationRelativeTo(null);
		myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create Labels and add labels to panels
		for (k = 0; k < NUM_CARDS_PER_HAND; k++) {
			// CREATE LABELS ----------------------------------------------------
			tempIcon = (ImageIcon) GUICard.getIcon(generateRandomCard());
			humanLabels[k] = new JLabel(tempIcon);
			computerLabels[k] = new JLabel(GUICard.getBackCardIcon()); // computer only uses one Icon (back-of-card)

			// ADD LABELS TO PANELS -----------------------------------------
			myCardTable.pnlComputerHand.add(computerLabels[k]);
			myCardTable.pnlHumanHand.add(humanLabels[k]);
		}

		// two random cards in the play region (simulating a computer/hum ply)
		for (k = 0; k < NUM_PLAYERS; k++) {
			// Create playLabels for you and computer in the region
			tempIcon = (ImageIcon) GUICard.getIcon(generateRandomCard());
			playedCardLabels[k] = new JLabel(tempIcon);

			// Add text directly below the icon
			if (k == 0)
				playLabelText[k] = new JLabel("Computer", JLabel.CENTER); // to center text in a label
			else
				playLabelText[k] = new JLabel("You", JLabel.CENTER);
		}

		// Add played Card labels for each
		for (k = 0; k < NUM_PLAYERS; k++) {
			myCardTable.pnlPlayArea.add(playedCardLabels[k]);
		}
		// Add play labels text for each
		for (k = 0; k < NUM_PLAYERS; k++) {
			myCardTable.pnlPlayArea.add(playLabelText[k]);
		}

		// show everything to the user
		myCardTable.setVisible(true);
	}

	// Method: generate a random card
	public static Card generateRandomCard() {
		Card card = new Card();
		int cardVal = (int) (Math.random() * (10000) % 14); // number should be from 0 to 13
		int suitVal = (int) (Math.random() * (10000) % 4); // number should be from 0 to 3
		card.set(GUICard.turnIntIntoCardValueChar(cardVal), GUICard.turnIntIntoSuit(suitVal));

		return card;
	}
}
