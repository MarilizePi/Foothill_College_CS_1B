
class Card {

	// public enum type
	public enum Suit {
		clubs, diamonds, hearts, spades
	}

	// private member data
	static final char DEFAULT_VAL = 'A';
	static final Suit DEFAULT_SUIT = Card.Suit.spades;
	private char value;
	private Suit suit;
	private boolean errorFlag;

	// default constructor
	public Card() {
		this(Card.DEFAULT_VAL, Card.DEFAULT_SUIT);
	}

	public Card(boolean errorFlag) {
		this();
		this.errorFlag = errorFlag;
	}

	// copy contructor
	Card(Card copyCard) {
		this(copyCard.value, copyCard.suit);
		this.errorFlag = copyCard.isErrorFlag();
	}

	// constructor that takes only the char value
	Card(char value) {
		this(value, DEFAULT_SUIT);
	}

	public Card(char value, Card.Suit suit) {
		this.errorFlag = false;
		if (!isValid(value, suit)) {
			this.errorFlag = true;
		}
		this.value = value;
		this.suit = suit;
	}

	// stringizer
	public String toString() {
		if (errorFlag) {
			return "[ invalid ]";
		}
		return value + " of " + suit;
	}

	boolean equals(Card card) {
		return this.errorFlag == card.errorFlag && this.suit == card.suit && this.value == card.value;
	}

	// mutator
	public boolean set(char value, Suit suit) {

		char myChar;
		this.suit = suit;
		myChar = Character.toUpperCase(value);

		if (isValid(value, suit)) {
			this.value = myChar;
			errorFlag = false;
		} else {
			this.value = myChar;
			errorFlag = true;
		}

		return !errorFlag;

	}

	// accessors
	public char getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	public boolean isErrorFlag() {
		return errorFlag;
	}

	static boolean isValid(char value, Suit suit) {
		if (value == 'A' || value == 'K' || value == 'Q' || value == 'J' || value == 'T'
				|| (value >= '2' && value <= '9'))
			return true;
		else
			return false;
	}

}