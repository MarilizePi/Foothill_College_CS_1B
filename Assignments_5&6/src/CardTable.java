import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

class CardTable extends JFrame {

	static int MAX_CARDS_PER_HAND = 57;
	static int MAX_PLAYERS = 2; // for now, we only allow 2 person games

	private int numCardsPerHand;
	private int numPlayers;
	public JPanel pnlHumanHand, pnlPlayArea, pnlComputerHand;

	// Constructor
	public CardTable(String title, int numCardsPerHand, int numPlayers) {
		super(title);

		// filters inputs
		if (numCardsPerHand < 0 || numCardsPerHand > MAX_CARDS_PER_HAND)
			this.numCardsPerHand = MAX_CARDS_PER_HAND;
		else
			this.numCardsPerHand = numCardsPerHand;
		if (numPlayers < 0 || numPlayers > MAX_PLAYERS)
			this.numPlayers = MAX_PLAYERS;
		else
			this.numPlayers = numPlayers;

		// establish three members using GridLayout for the JPanels
		pnlHumanHand = new JPanel(new GridLayout(1, 7, 10, 10));
		pnlPlayArea = new JPanel(new GridLayout(2, 2, 10, 10));
		pnlComputerHand = new JPanel(new GridLayout(1, 7, 10, 10));

		// add three panels and set them using the BorderLayout
		setLayout(new BorderLayout(20, 10));
		add(pnlHumanHand, BorderLayout.NORTH);
		add(pnlPlayArea, BorderLayout.CENTER);
		add(pnlComputerHand, BorderLayout.SOUTH);

		// set titles using setBorder
		pnlHumanHand.setBorder(new TitledBorder("Player Hand"));
		pnlPlayArea.setBorder(new TitledBorder("Playing area"));
		pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
	}

	// Two Accessors
	public int getNumCardsPerHand() {
		return numCardsPerHand;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

}
