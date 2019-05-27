import java.text.*;
import java.util.*;

public class Foothill {
	// ------- main --------------
	public static void main(String[] args) throws Exception {
		FHsdTree<String> sceneTree = new FHsdTree<String>();
		FHsdTreeNode<String> tn;

		System.out.println("Starting tree empty? " + sceneTree.empty() + "\n");
		// create a scene in a room
		tn = sceneTree.addChild(null, "room");

		// add three objects to the scene tree
		sceneTree.addChild(tn, "Lily the canine");
		sceneTree.addChild(tn, "Miguel the human");
		sceneTree.addChild(tn, "table");
		// add some parts to Miguel
		tn = sceneTree.find("Miguel the human");

		// Miguel's left arm
		tn = sceneTree.addChild(tn, "torso");
		tn = sceneTree.addChild(tn, "left arm");
		tn = sceneTree.addChild(tn, "left hand");
		sceneTree.addChild(tn, "thumb");
		sceneTree.addChild(tn, "index finger");
		sceneTree.addChild(tn, "middle finger");
		sceneTree.addChild(tn, "ring finger");
		sceneTree.addChild(tn, "pinky");

		// Miguel's right arm
		tn = sceneTree.find("Miguel the human");
		tn = sceneTree.find(tn, "torso", 0);
		tn = sceneTree.addChild(tn, "right arm");
		tn = sceneTree.addChild(tn, "right hand");
		sceneTree.addChild(tn, "thumb");
		sceneTree.addChild(tn, "index finger");
		sceneTree.addChild(tn, "middle finger");
		sceneTree.addChild(tn, "ring finger");
		sceneTree.addChild(tn, "pinky");

		// add some parts to Lily
		tn = sceneTree.find("Lily the canine");
		tn = sceneTree.addChild(tn, "torso");
		sceneTree.addChild(tn, "right front paw");
		sceneTree.addChild(tn, "left front paw");
		sceneTree.addChild(tn, "right rear paw");
		sceneTree.addChild(tn, "left rear paw");
		sceneTree.addChild(tn, "spare mutant paw");
		sceneTree.addChild(tn, "wagging tail");

		// add some parts to table
		tn = sceneTree.find("table");
		sceneTree.addChild(tn, "north east leg");
		sceneTree.addChild(tn, "north west leg");
		sceneTree.addChild(tn, "south east leg");
		sceneTree.addChild(tn, "south west leg");

		System.out.println("\n------------ Loaded Tree ----------------- \n");
		sceneTree.display();

		sceneTree.remove("spare mutant paw");
		sceneTree.remove("Miguel the human");
		sceneTree.remove("an imagined higgs boson");

		System.out.println("\n------------ Virtual (soft) Tree ----------- \n");
		sceneTree.display();

		System.out.println("\n----------- Physical (hard) Display ---------- \n");
		sceneTree.displayPhysical();

		System.out.println("------- Testing Sizes (compare with above) ----- \n");
		System.out.println("virtual (soft) size: " + sceneTree.size());
		System.out.println("physiical (hard) size: " + sceneTree.sizePhysical());

		System.out.println("------------ Collecting Garbage -------------- \n");
		System.out.println("found soft-deleted nodes? " + sceneTree.collectGarbage());
		System.out.println("immediate collect again? " + sceneTree.collectGarbage());

		System.out.println("--------- Hard Display after garb col --------- \n");

		sceneTree.displayPhysical();

		System.out.println("Semi-deleted tree empty? " + sceneTree.empty() + "\n");
		sceneTree.remove("room");
		System.out.println("Completely-deleted tree empty? " + sceneTree.empty() + "\n");

		sceneTree.display();
		System.out.println("-------Main 2-------------");
		FHsdTree<Integer> numTree = new FHsdTree<Integer>();
		FHsdTreeNode<Integer> numNode;

		// create a double root
		numNode = numTree.addChild(null, 100);

		numTree.addChild(numNode, 33);
		numTree.addChild(numNode, 12);
		numTree.addChild(numNode, 45);
		numTree.addChild(numNode, 67);
		numTree.addChild(numNode, 96);

		numNode = numTree.find(67);

		numNode = numTree.addChild(numNode, 55);
		numNode = numTree.addChild(numNode, 44);
		numNode = numTree.addChild(numNode, 11);
		numTree.addChild(numNode, 1);
		numTree.addChild(numNode, 6);
		numTree.addChild(numNode, 9);

		numNode = numTree.find(12);
		numNode = numTree.addChild(numNode, 66);
		numNode = numTree.addChild(numNode, 77);
		numTree.addChild(numNode, -1);
		numTree.addChild(numNode, -6);
		numTree.addChild(numNode, -9);
		numTree.addChild(numNode, -3);

		numTree.display();
		System.out.println("\nVirtual Size: " + numTree.size());
		System.out.println("\nPhysical Size: " + numTree.sizePhysical() + "\n");

		// clone
		FHsdTree<Integer> ClonedTree2 = (FHsdTree<Integer>) numTree.clone();

		// remove some nodes
		numTree.remove(33);
		numTree.remove(6);
		numTree.remove(55);

		numTree.display();
		System.out.println("\nVirtual Size: " + numTree.size());

		numTree.displayPhysical();
		System.out.println("\nPhysical Size: " + numTree.sizePhysical() + "\n");

		numTree.collectGarbage();

		numTree.displayPhysical();

		System.out.println("\nVirtual Size: " + numTree.size());
		System.out.println("\nPhysical Size: " + numTree.sizePhysical() + "\n");

		// see if the clone worked
		System.out.println("Clone display");
		ClonedTree2.display();
		System.out.println("\nClone's Virtual Size: " + ClonedTree2.size() + "\n");

		// -----------------------------------------------------------------------
		System.out.println("-----------Main 3-------------");

		FHsdTree<Card> cardTree = new FHsdTree<Card>();

		FHsdTreeNode<Card> tn3;

		// create a double root
		tn3 = cardTree.addChild(null, new Card('K', Card.Suit.hearts));

		// add some objects to the tree
		cardTree.addChild(tn3, new Card('Q', Card.Suit.hearts));
		cardTree.addChild(tn3, new Card('Q', Card.Suit.diamonds));
		cardTree.addChild(tn3, new Card('Q', Card.Suit.spades));
		cardTree.addChild(tn3, new Card('Q', Card.Suit.clubs));

		tn3 = cardTree.find(new Card('Q', Card.Suit.hearts));

		tn3 = cardTree.addChild(tn3, new Card('J', Card.Suit.hearts));
		tn3 = cardTree.addChild(tn3, new Card('T', Card.Suit.hearts));
		tn3 = cardTree.addChild(tn3, new Card('9', Card.Suit.hearts));

		cardTree.addChild(tn3, new Card('3', Card.Suit.hearts));
		cardTree.addChild(tn3, new Card('4', Card.Suit.hearts));

		tn3 = cardTree.find(new Card('Q', Card.Suit.spades));

		tn3 = cardTree.addChild(tn3, new Card('3', Card.Suit.spades));
		cardTree.addChild(tn3, new Card('4', Card.Suit.spades));

		tn3 = cardTree.find(new Card('Q', Card.Suit.clubs));

		tn3 = cardTree.addChild(tn3, new Card('J', Card.Suit.clubs));
		tn3 = cardTree.addChild(tn3, new Card('T', Card.Suit.clubs));
		tn3 = cardTree.addChild(tn3, new Card('9', Card.Suit.clubs));
		cardTree.addChild(tn3, new Card('3', Card.Suit.clubs));
		cardTree.addChild(tn3, new Card('5', Card.Suit.clubs));
		cardTree.addChild(tn3, new Card('6', Card.Suit.clubs));
		cardTree.addChild(tn3, new Card('7', Card.Suit.clubs));
		cardTree.addChild(tn3, new Card('8', Card.Suit.clubs));

		cardTree.display();

		System.out.println("\nVirtual Size: " + cardTree.size());
		System.out.println("\nPhysical Size: " + cardTree.sizePhysical() + "\n");

		// clone
		FHsdTree<Card> ClonedTree3 = (FHsdTree<Card>) cardTree.clone();

		// remove some nodes
		cardTree.remove(new Card('Q', Card.Suit.hearts));
		cardTree.remove(new Card('Q', Card.Suit.spades));
		cardTree.remove(new Card('Q', Card.Suit.clubs));

		cardTree.display();
		System.out.println("\nVirtual Size: " + cardTree.size());

		cardTree.displayPhysical();
		System.out.println("\nPhysical Size: " + cardTree.sizePhysical() + "\n");

		cardTree.collectGarbage();

		cardTree.displayPhysical();

		System.out.println("\nVirtual Size: " + cardTree.size());
		System.out.println("\nPhysical Size: " + cardTree.sizePhysical() + "\n");

		// see if the clone worked
		System.out.println("Clone display");
		ClonedTree3.display();
		System.out.println("\nClone's Virtual Size: " + ClonedTree3.size() + "\n");

		return;
	}
}