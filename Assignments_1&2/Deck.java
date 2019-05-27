import java.util.Random;

class Deck {

	private final int MAX_PACKS = 6;
	private final int NUM_CARDS_PER_PACK = 52;
	private final int MAX_CARDS_PER_DECK = 6 * 52;
	private static Card[] masterPack;
	private Card[] cards;
	private int topCard;
	private int numPacks;
	private static boolean allocatedMasterPack = false;

	Deck(int numPacks) {
		this.numPacks = numPacks;
		Deck.masterPack = new Card[NUM_CARDS_PER_PACK];
		allocateMasterPack();
		cards = new Card[numPacks * NUM_CARDS_PER_PACK];

		int masterPackIndex = 0;

		for (int i = 0; i < NUM_CARDS_PER_PACK * numPacks; i++) {

			cards[i] = Deck.masterPack[masterPackIndex];
			masterPackIndex++;

			if (masterPackIndex > 51) {
				masterPackIndex = 0;
			}

		}

		topCard = cards.length - 1;
	}

	// default constructor
	Deck() {
		this(1);
	}

	// reset the deck
	public boolean init(int numPacks) {

		if (numPacks <= 0 || numPacks > MAX_PACKS)
			return false;
		cards = new Card[numPacks * NUM_CARDS_PER_PACK];
		for (int i = 0; i < numPacks; i++) {
			for (int j = 0; j < NUM_CARDS_PER_PACK; j++) {
				cards[i * NUM_CARDS_PER_PACK + j] = masterPack[j];
			}
		}
		topCard = numPacks * NUM_CARDS_PER_PACK;
		return true;
	}

	void shuffle() {

		Random randG = new Random();
		for (int i = topCard; i > 0; i--) {
			int rand = randG.nextInt(i + 1);
			Card c = cards[rand];
			cards[rand] = cards[i];
			cards[i] = c;
		}

	}

	public Card dealCard() {
		Card c;
		Card errorReturn = new Card('E', Card.Suit.spades);
		if (topCard <= 0)
			c = errorReturn;
		else {
			c = new Card(cards[topCard - 1]);
			cards[topCard - 1] = null;
			--topCard;
		}
		return c;

	}

	public int getNumCards() {
		return cards.length;
	}

	
	
	
	Card inspectCard(int k) {
		Card retError = new Card('Y', Card.Suit.hearts);

		if (k < 0 || k >= topCard)
			return retError;
		else
			return cards[k];
	}

	static void allocateMasterPack() {

		char[] values = { 'T', 'J', 'Q', 'K', 'A' };
		int generalIndex = 0;
		for (int i = 0; i < 4; i++) {

			Card.Suit suit = Card.Suit.values()[i];

			for (int a = 0; a < 13; a++) {
				char value;

				if (a <= 7) {
					value = Character.forDigit(a + 2, 10);
				} else {
					value = values[a - 8];
				}

				Card card = new Card(value, suit);
				masterPack[generalIndex] = card;
				generalIndex++;

			}

		}

		allocatedMasterPack = true;
	}

}
