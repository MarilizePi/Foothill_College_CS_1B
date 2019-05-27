class Deck {

	// private static class constants
	private static final int NUM_CARDS_PER_PACK = 52;
	private static final int MAX_PACKS = 6;
	private static final int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;
	private static final int DEFAULT_NUMPACK = 1;

	// private member data
	private Card[] cards;
	private int topCard;
	private int numPacks;

	// private static member data
	private static Card[] masterPack;

	// Method: allocate masterPack, which that will be called by the constructor.
	private static void allocateMasterPack() {
		if (masterPack == null) {
			masterPack = new Card[NUM_CARDS_PER_PACK];
			int k, j;
			Card.Suit st;
			char val;
			for (k = 0; k < masterPack.length; k++)
				masterPack[k] = new Card();

			for (k = 0; k < 4; k++) {
				switch (k) {
				case 0:
					st = Card.Suit.clubs;
					break;
				case 1:
					st = Card.Suit.diamonds;
					break;
				case 2:
					st = Card.Suit.hearts;
					break;
				case 3:
					st = Card.Suit.spades;
					break;
				default:
					st = Card.Suit.spades;
					break;
				}

				for (val = '2', j = 0; val <= '9'; val++, j++)
					masterPack[13 * k + j].set(val, st);
				masterPack[13 * k + 8].set('T', st);
				masterPack[13 * k + 9].set('J', st);
				masterPack[13 * k + 10].set('Q', st);
				masterPack[13 * k + 11].set('K', st);
				masterPack[13 * k + 12].set('A', st);

			}
		}
	}

	// Method: constructor
	public Deck(int numPacks) {
		allocateMasterPack();
		topCard = NUM_CARDS_PER_PACK * numPacks;
		if (numPacks <= MAX_PACKS) {
			cards = new Card[NUM_CARDS_PER_PACK * numPacks];
			init(numPacks);
		}
	}

	// Method: default constructor
	public Deck() {
		numPacks = DEFAULT_NUMPACK;
		allocateMasterPack();
		topCard = NUM_CARDS_PER_PACK * numPacks;
		if (numPacks <= MAX_PACKS) {
			cards = new Card[NUM_CARDS_PER_PACK * numPacks];
			init(numPacks);
		}
	}

	// Method: re-populate cards[] with 52 × numPacks cards.
	public boolean init(int numPacks) {
		if (numPacks <= 0 || numPacks > MAX_PACKS)
			return false;

		if (cards.length < numPacks * NUM_CARDS_PER_PACK) {
			cards = new Card[numPacks * NUM_CARDS_PER_PACK];
		}
		for (int i = 0; i < numPacks; i++) {
			for (int j = 0; j < NUM_CARDS_PER_PACK; j++) {
				cards[j + (NUM_CARDS_PER_PACK * i)] = masterPack[j];
			}
		}
		this.numPacks = numPacks;
		this.topCard = numPacks * NUM_CARDS_PER_PACK;
		return true;

	}

	public String toString() {
		String deckString = "";
		for (int i = 0; i < topCard; i++) {
			deckString += cards[i].toString();
			deckString += " / ";

		}

		if (this.topCard == 0) {

			deckString = "[deck is empty]";
		}

		return deckString;
	}

	// Method: returns and removes the card in the top occupied position of cards[].
	public Card dealCard() {
		Card copyCard;
		if (topCard <= 0)
			copyCard = noCard();
		else {
			copyCard = new Card(cards[topCard - 1]);
			cards[topCard - 1] = null;
			topCard--;
		}
		return copyCard;
	}

	// Method: mixes up the cards with the random number generator.
	public void shuffle() {
		for (int i = 0; i < 1000000; i++) {
			int firstSlot = (int) (Math.random() * (topCard));
			int secondSlot = (int) (Math.random() * (topCard));

			Card temp = new Card(cards[firstSlot]);
			cards[firstSlot] = cards[secondSlot];
			cards[secondSlot] = temp;
		}

	}

	// Method: accessor
	public int getNumCards() {
		return this.topCard;
	}

	// Method: makes bad card.
	private static final Card noCard() {
		return new Card('?');
	}

	// Method: accessor for an individual card
	public Card inspectCard(int k) {
		if (topCard > 0) {
			if (k > topCard - 1 || k < 0)
				return noCard();
			else
				return cards[k];
		} else {
			return noCard();
		}
	}
}
