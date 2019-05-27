import java.util.Stack;

//derived class CardWOrderStk
class CardWOrderStk extends Card {
	// static member
	static Stack<OrderObject> objStk = new Stack<OrderObject>();

	// inner class OrderObject
	private static class OrderObject {
		char[] valueRanks = { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X' };
		Suit[] suitRanks = { Suit.clubs, Suit.diamonds, Suit.hearts, Suit.spades };
	}// end OrderObject

	public static void pushOrdering() {
		OrderObject obj = new OrderObject();
		objStk.push(obj);
	}

	public static boolean popOrdering() {
		OrderObject obj = objStk.pop();
		if (obj == null)
			return false;
		valueRanks = obj.valueRanks;
		suitRanks = obj.suitRanks;
		return true;
	}

}// end CardWOrderStk