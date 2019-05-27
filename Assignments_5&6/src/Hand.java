
class Hand {
	public final static int MAX_CARDS = 50;

	private Card[] myCards;
	private int numCards;

	// default constructor
	Hand() {
		this.myCards = new Card[MAX_CARDS];
		this.numCards = 0;
	}

	void resetHand() {
		numCards = 0;
	}

	boolean takeCard(Card card) {
		if (this.numCards == MAX_CARDS) {
			return false;
		}
		this.myCards[this.numCards] = card;
		this.numCards++;
		return true;
	}

	public Card playCard() {
		if (this.numCards == 0) {
			return new Card('u', Card.Suit.clubs);
		}
		Card card = this.myCards[this.numCards - 1];
		this.myCards[this.numCards - 1] = null;
		this.numCards--;
		return card;
	}

	// create a bad card and return bad card

	public String toString() {
		String myStr = "Hand = ( ";

		int k = 0;

		if (k < numCards) {
			while (k < numCards - 1) {
				myStr += myCards[k].toString() + ", ";
				k++;
			}
			myStr += myCards[k].toString() + " ";
		}

		myStr += ")";

		return myStr;
	}

	public int getNumCards() {
		return this.numCards;
	}

	Card inspectCard(int k) {
		{
			if (k >= this.numCards || k < 0) {
				return new Card(true);
			}
			return myCards[k];
		}
	}

}