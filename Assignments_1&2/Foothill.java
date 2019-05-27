import java.util.Scanner;

public class Foothill {

	public static void main(String[] args) {
		testCard();
		testHand();
		// testDeck();

	}

	public static void testCard() {

		Card[] result = { new Card(), new Card('J', Card.Suit.diamonds) };
		for (Card card : result) {
			System.out.println(card);
		}
		result[0].set('M', Card.Suit.hearts);
		result[1].set('J', Card.Suit.clubs);

		for (Card card : result) {
			System.out.println(card);
		}

	}

	public static void testHand() {

		Hand hand = new Hand();
		System.out.println("\n" + "Hand full" + "\n" + "After deal");
		for (int i = 0; i < 10; i++) {
			hand.takeCard(new Card('3', Card.Suit.clubs));
			hand.takeCard(new Card('T', Card.Suit.clubs));
			hand.takeCard(new Card('9', Card.Suit.hearts));
		}

		System.out.println(hand + "\n");

		// testing inspect card
		int numCards = hand.getNumCards();
		System.out.println("Testing inspectCard()");
		System.out.println(hand.inspectCard(hand.getNumCards() - 9));
		System.out.println(hand.inspectCard(31).toString());

		for (int i = 0; i < numCards; i++) {
			System.out.println("Playing " + hand.playCard());
		}
		System.out.println("\n" + "After playing all cards");
		System.out.println("Hand = " + "(" + hand.getNumCards() + ")");
	}

	public static void testDeck() {
		// Phase 1:

		Deck deck = new Deck(2);

		// deal 2 deck of cards
		while (deck.getNumCards() != -1) {
			System.out.print(deck.dealCard().toString() + " / ");
		}
		// reset
		deck.init(2);

		deck.shuffle();
		// deal shuffled cards
		System.out.println("\n");
		while (deck.getNumCards() != -1) {
			System.out.print(deck.dealCard().toString() + " / ");
		}

		Deck deck1 = new Deck(1);
		System.out.println("\n");

		while (deck1.getNumCards() != -1) {
			System.out.print(deck1.dealCard().toString() + " / ");
		}

		deck1.init(1);

		deck1.shuffle();

		System.out.println("\n");
		while (deck1.getNumCards() != -1) {
			System.out.print(deck1.dealCard().toString() + " / ");
		}
		System.out.println("\n");

		// Phase 2:

		int user;
		Scanner keyboard = new Scanner(System.in);

		System.out.print("\n" + "How many hands? ");

		do {
			System.out.print("(1 - 10, please): ");
			user = keyboard.nextInt();
		} while (user < 1 || user > 10);

		Deck deck2 = new Deck(1);

		Hand[] hand = new Hand[user];
		for (int i = 0; i < user; i++) {
			hand[i] = new Hand();
		}

		while (deck2.getNumCards() != -1) {
			for (int i = 0; i < user; i++) {
				if (deck2.getNumCards() == -1)
					break;
				hand[i].takeCard(deck2.dealCard());
			}
		}

		System.out.println("Here are our hands, from unshuffled deck:");
		for (int i = 0; i < user; i++) {
			System.out.println(hand[i].toString() + "\n");
		}

		deck2.init(1);
		deck2.shuffle();

		for (int i = 0; i < user; i++)
			hand[i].resetHand();

		// after shuffle
		while (deck2.getNumCards() != -1) {
			for (int i = 0; i < user; i++) {
				if (deck2.getNumCards() == -1)
					break;
				hand[i].takeCard(deck2.dealCard());

			}
		}

		// print hands
		System.out.println("\n");
		System.out.println("Here are our hands, from SHUFFLED deck: ");
		for (int i = 0; i < user; i++) {
			System.out.println(hand[i].toString() + "\n");
		}

		keyboard.close();
	}
}
