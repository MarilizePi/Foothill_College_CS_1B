public class Stack {
	// pointer to first node in stack
	private StackNode top;

	// constructor
	public Stack() {
		top = null;
	}

	public void push(StackNode newNode) {
		if (newNode == null)
			return; // emergency return
		newNode.next = top;
		top = newNode;
	}

	public StackNode pop() {
		StackNode temp;

		temp = top;
		if (top != null) {
			top = top.next;
			temp.next = null; // don't give client access to stack!
		}
		return temp;
	}

	// console display
	public void showStack() {
		StackNode p;

		// Display all the nodes in the stack
		for (p = top; p != null; p = p.next)
			p.show();
	}

}

class StackNode {
	// data (protected allows only certain other classes to access "next" directly)
	protected StackNode next;

	// constructor
	public StackNode() {
		next = null;
	}

	// console display
	public void show() {
		System.out.print("(generic node) ");
	}
}

/*
 * derive IuNode and IuStack classes in the same manner as we derived FloatNode
 * and FloatStack in the modules, but making adjustments, as needed, to have the
 * IuStack work with InternetUsers. Change most methods named showXYZ() by
 * converting them to toString() so that the client, not the methods, does the
 * output. The new methods merely format the strings in preparation for output.
 */
class IuNode extends StackNode {

	// additional data for subclass
	private InternetUser data;

	// constructor
	public IuNode(InternetUser x) {
		super(); // constructor call to base class
		data = x;
	}

	// accessor
	public InternetUser getData() {
		return data;
	}

}

class IuStack extends Stack {

	public static final InternetUser STACK_EMPTY = null;

	public void pushIu(InternetUser x) {
		// don't allow pushing of STACK_EMPTY
		if (x == STACK_EMPTY)
			return;

		// create a new IuNode
		IuNode iup = new IuNode(x);

		// push the StackNode onto the stack (base class call)
		super.push(iup);
	}

	public InternetUser popIu() {
		// pop a node
		IuNode iup = (IuNode) pop();
		if (iup == null) {
			System.out.println("(STACK_EMPTY)");
			return STACK_EMPTY;
		} else
			return iup.getData();
	}
}