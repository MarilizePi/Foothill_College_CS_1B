import java.text.*;
import java.util.*;

//------------------------------------------------------
public class Foothill
{ 
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      FHtree<String> sceneTree = new FHtree<String>();
      FHtreeNode<String> tn;
      
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
      tn =  sceneTree.addChild(tn, "left hand");
      sceneTree.addChild(tn, "thumb");
      sceneTree.addChild(tn, "index finger");
      sceneTree.addChild(tn, "middle finger");
      sceneTree.addChild(tn, "ring finger");
      sceneTree.addChild(tn, "pinky");

      // Miguel's right arm
      tn = sceneTree.find("Miguel the human");
      tn = sceneTree.find(tn, "torso", 0);
      tn = sceneTree.addChild(tn, "right arm");
      tn =  sceneTree.addChild(tn, "right hand");
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

      sceneTree.display();
      
      sceneTree.remove("spare mutant paw");
      sceneTree.remove("Miguel the human");
      sceneTree.remove("an imagined higgs boson");
      
      sceneTree.display();

/* ------------------ RUN -------------
room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Miguel the human
  torso
   right arm
    right hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
   left arm
    left hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
 Lily the canine
  torso
   wagging tail
   spare mutant paw
   left rear paw
   right rear paw
   left front paw
   right front paw
room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw

-------------------------------------------- */
		// --------MAIN PART TWO: WITH ints--------------------
		FHsdTree<Integer> numTree = new FHsdTree<Integer>();
		FHtreeNode<Integer> numNode;

		// create a double root
		numNode = numTree.addChild(null, 6);

		// add some objects to the tree
		numTree.addChild(numNode, 33);
		numTree.addChild(numNode, 12);
		numTree.addChild(numNode, -30);

		// add parts to some nodes
		numNode = numTree.find(12);
		numTree.addChild(numNode, 542);
		numTree.addChild(numNode, 2232);
		numTree.addChild(numNode, 41);

		numNode = numTree.find(41);
		numTree.addChild(numNode, 34);

		numNode = numTree.find(-30);
		numTree.addChild(numNode, 314);
		numTree.addChild(numNode, -314);
		numTree.addChild(numNode, 2141);

		System.out.print("\n------------ Loaded Tree ----------------- \n");
		numTree.display();

		numTree.remove(314);
		numTree.remove(41);
		numTree.remove(2232);
		numTree.remove(67);

		System.out.print("\n------------ Virtual (soft) Tree --------------\n");
		numTree.display();

		System.out.print("\n------------ Physical (hard) Display --------- \n");
		numTree.displayPhysical();

		System.out.print("------- Testing Sizes (compare with above)------ \n");
		System.out.println("virtual (soft) size: " + numTree.size());
		System.out.println("physical (hard) size: " + numTree.sizePhysical());

		System.out.println("--------- Collecting Garbage -------------- \n");
		System.out.println("found soft-deleted nodes? " + numTree.collectGarbage());
		System.out.println("immediate collect again? " + numTree.collectGarbage());
		System.out.print("--------- Hard Display after garb col --------- \n");
		numTree.displayPhysical();

		System.out.println("Semi-deleted tree empty? " + numTree.empty() + "\n");
		numTree.remove(6);
		System.out.println("Completely-deleted tree empty? " + numTree.empty() + "\n\n");
		// -----------------------End Main Run 2--------------------

		// ------Main Run #3: SoftDel tree with type Card------------------
		FHsdTree<Card> cardTree = new FHsdTree<Card>();

		FHtreeNode<Card> cNode;

		// create a double root
		cNode = cardTree.addChild(null, new Card('A', Card.Suit.hearts));

		// add some objects to the tree
		cardTree.addChild(cNode, new Card());
		cardTree.addChild(cNode, new Card('K', Card.Suit.hearts));
		cardTree.addChild(cNode, new Card('X', Card.Suit.diamonds));

		// add parts to some nodes
		cNode = cardTree.find(new Card('K', Card.Suit.hearts));
		cardTree.addChild(cNode, new Card('J', Card.Suit.hearts));
		cardTree.addChild(cNode, new Card('Q', Card.Suit.hearts));
		cardTree.addChild(cNode, new Card('A', Card.Suit.hearts));

		cNode = cardTree.find(new Card('X', Card.Suit.diamonds));
		cardTree.addChild(cNode, new Card('T', Card.Suit.clubs));

		cNode = cardTree.find(new Card('T', Card.Suit.clubs));
		cardTree.addChild(cNode, new Card('T', Card.Suit.spades));
		cardTree.addChild(cNode, new Card('T', Card.Suit.hearts));
		cardTree.addChild(cNode, new Card('T', Card.Suit.diamonds));

		cNode = cardTree.find(new Card('Q', Card.Suit.hearts));
		cardTree.addChild(cNode, new Card('5', Card.Suit.spades));
		cardTree.addChild(cNode, new Card('6', Card.Suit.spades));
		cardTree.addChild(cNode, new Card('3', Card.Suit.spades));
		cardTree.addChild(cNode, new Card('9', Card.Suit.spades));

		System.out.print("\n------------ Loaded Tree ----------------- \n");
		cardTree.display();
		FHsdTree<Card> cardTreeClone = cardTree;

		System.out.print("\n------Loaded Clone Tree ----------------\n");
		cardTreeClone.display();

		cardTree.remove(new Card('T', Card.Suit.hearts));
		cardTree.remove(new Card('Q', Card.Suit.hearts));

		System.out.print("\n------------ Virtual (soft) Tree + clone ------- \n");
		cardTree.display();

		System.out.print("\n---clone---\n");
		cardTreeClone.display();

		System.out.print("\n------------ Physical tree, real then clone------ \n");
		cardTree.displayPhysical();
		System.out.print("\n" + "------\n");
		cardTreeClone.displayPhysical();

		System.out.print("------- Testing Sizes (compare with above)------- \n");
		System.out.println("virtual (soft) size: " + cardTree.size());
		System.out.println("physical (hard) size: " + cardTree.sizePhysical());
		System.out.println("clone (hard) size: " + cardTreeClone.sizePhysical());

		System.out.print("------------ Collecting Garbage ----------------- \n");
		System.out.println("found soft-deleted nodes? " + cardTree.collectGarbage());
		System.out.println("immediate collect again? " + cardTree.collectGarbage());

		System.out.print("--------- Hard Display after garb col ------------ \n");
		cardTree.displayPhysical();

		System.out.println("Semi-deleted tree empty? " + cardTree.empty());
		cardTree.remove(new Card('A', Card.Suit.hearts));
		System.out.println("Completely-deleted tree empty? " + cardTree.empty());

		System.out.println("\n---final tree----");
		cardTree.displayPhysical();

		// -----End Main Run 3-------------------------------------

		return;
	}
}