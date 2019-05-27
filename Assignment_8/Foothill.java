import java.util.*;

public class Foothill {
	public static void main(String[] args) {

		// create a linked list and its iterator
		LinkedList<Card> list = new LinkedList<Card>();
		ListIterator<Card> iter;

		// temporary variable
		Card tempCard;

		// insert the cards to the list
		for (int i = 0; i < 10; i++) {
			tempCard = generateRandomCard();
			insert(list, tempCard);
			insert(list, tempCard);
		}

		System.out.println("Inserted Cards:");
		for (iter = list.listIterator(); iter.hasNext();)
			System.out.println(iter.next().toString());

		try {
			for (int i = 0; i < 5; i++) {
				tempCard = list.peekFirst();
				System.out.println("\n" + "Remove " + tempCard.toString() + ":");
				for (boolean bool = true; bool == true;) {
					bool = remove(list, tempCard);
				}

				for (iter = list.listIterator(); iter.hasNext();)
					System.out.println(iter.next().toString());
			}
		} catch (Exception e) {
			// if error happens, it means there might be identical generated random
			// cards.
		}

		try {
			for (int i = 0; i < 5; i++) {
				tempCard = list.peekFirst();
				System.out.println("\n" + "RemoveAll " + tempCard.toString() + ":");
				removeAll(list, tempCard);
				for (iter = list.listIterator(); iter.hasNext();)
					System.out.println(iter.next().toString());
			}
		} catch (Exception e) {
		}
	}

	static Card generateRandomCard() {
		Card.Suit suit;
		char val;

		int suitSelector, valSelector;

		// get random suit and value
		suitSelector = (int) (Math.random() * 4);
		valSelector = (int) (Math.random() * 13);

		// pick suit
		suit = Card.Suit.values()[suitSelector];

		// pick value
		valSelector++; // put in range 1-13
		switch (valSelector) {
		case 1:
			val = 'A';
			break;
		case 10:
			val = 'T';
			break;
		case 11:
			val = 'J';
			break;
		case 12:
			val = 'Q';
			break;
		case 13:
			val = 'K';
			break;
		default:
			val = (char) ('0' + valSelector); // simple way to turn n into 'n'
		}
		return new Card(val, suit);
	}

	static void insert(LinkedList<Card> list, Card card) {
		ListIterator<Card> iter;
		Card tempCard;

		for (iter = list.listIterator(); iter.hasNext();) {
			tempCard = iter.next();
			if (card.compareTo(tempCard) <= 0) {
				iter.previous(); // back up one
				break;
			}
		}
		iter.add(card);
	}

	static boolean remove(LinkedList<Card> list, Card x) {
		ListIterator<Card> iter;

		for (iter = list.listIterator(); iter.hasNext();) {
			if (iter.next().compareTo(x) == 0) {
				iter.remove();
				return true;
			}
		}
		return false;
	}

	static boolean removeAll(LinkedList<Card> list, Card x) {
		ListIterator<Card> iter;
		boolean found = false;

		for (iter = list.listIterator(); iter.hasNext();) {
			if (iter.next().compareTo(x) == 0) {
				iter.remove();
				found = true; 
			}
		}
		return found;
	}

}