class FHsdTree<E> extends FHtree<E> implements Cloneable {
	protected FHsdTreeNode<E> mRoot;
	final static String blankString = "                                    ";

	public int sizePhysical() {
		return super.size();
	}

	public boolean empty() {
		return size() == 0;
	}

	// remove
	public boolean remove(E x) {
		return remove((FHsdTreeNode<E>) (mRoot), x);
	}

	// find
	public FHsdTreeNode<E> find(E x) {
		return find((FHsdTreeNode<E>) mRoot, x, 0);
	}

	// size
	public int size(FHsdTreeNode<Card> root) {
		return checkSize(mRoot);
	}

	// display
	public void display() {
		display(mRoot, 0);
	}

	public boolean collectGarbage() {
		return collectGarbage(mRoot);
	}

	// find
	public FHsdTreeNode<E> find(FHsdTreeNode<E> root, E x, int level) {
		FHsdTreeNode<E> retval;

		if (mSize == 0 || root == null)
			return null;

		if (root.data.equals(x))
			return root;

		// otherwise, recurse. don't process sibs if this was the original call
		if (level > 0 && (retval = (FHsdTreeNode<E>) find(root.sib, x, level)) != null)
			return retval;

		if (root.deleted)
			return null;

		return (FHsdTreeNode<E>) find(root.firstChild, x, ++level);
	}

	// remove
	public boolean remove(FHsdTreeNode<E> root, E x) {
		FHsdTreeNode<E> tn = null;

		if (mSize == 0 || root == null || root.isDeleted())
			return false;

		if ((tn = find(root, x, 0)) != null) {
			tn.setDeleted(true);
			return true;
		}
		return false;
	}

	// addChild
	public FHsdTreeNode<E> addChild(FHsdTreeNode<E> treeNode, E x) {
		// empty tree? - create a root node if user passes in NULL
		if (mSize == 0) {
			if (treeNode != null)
				return null; // silent error something's fishy.treeNode can't write
			mRoot = new FHsdTreeNode<E>(x, null, null, null, false);
			((FHsdTreeNode<E>) mRoot).setRoot(mRoot);
			mSize = 1;
			return (FHsdTreeNode<E>) mRoot;
		}

		if (treeNode == null)
			return null;
		if (treeNode.getRoot() != mRoot)
			return null; // silent error, node does not belong to this tree

		// push this node into the head of the sibling list; adjust prev pointers
		FHsdTreeNode<E> newNode = new FHsdTreeNode<E>(x, treeNode.getFirstChild(), null, treeNode, false);
		newNode.setRoot(mRoot);
		treeNode.setFirstChild(newNode);
		if (newNode.getSibling() != null)
			newNode.getSibling().setPrevious(newNode);
		++mSize;
		return (FHsdTreeNode<E>) newNode;
	}

	// size
	private int checkSize(FHsdTreeNode<E> x) {
		if (x == null)
			return 0;

		if (x.deleted)
			return checkSize(x.sib);

		return 1 + checkSize(x.firstChild) + checkSize(x.sib);
	}

	void displayPhysical() {
		displayPhysical((FHsdTreeNode<E>) (mRoot), 0);
	}

	void displayPhysical(FHsdTreeNode<E> treeNode, int level) {
		String indent;

		// stop runaway indentation/recursion
		if (level > (int) blankString.length() - 1) {
			System.out.println(blankString + " ... ");
			return;
		}

		if (treeNode == null)
			return;

		indent = blankString.substring(0, level);

		System.out.print(indent + treeNode.getData());
		if (treeNode.isDeleted())
			System.out.print(" (D)");
		System.out.println();
		displayPhysical(treeNode.getFirstChild(), level + 1);
		if (level > 0)
			displayPhysical(treeNode.getSibling(), level);
	}

	// display
	// let be public so client can call on subtree
	public void display(FHtreeNode<E> treeNode, int branch) {
		String indent;

		// stop runaway indentation/recursion
		if (branch > (int) blankString.length() - 1) {
			System.out.println(blankString + " ... ");
			return;
		}

		if (treeNode == null)
			return;

		indent = blankString.substring(0, branch);

		// pre-order processing done here ("visit")
		System.out.println(indent + treeNode.data);

		// recursive step done here
		display(treeNode.firstChild, branch + 1);
		if (branch > 0)
			display(treeNode.sib, branch);
	}

	protected boolean collectGarbage(FHsdTreeNode<E> root) {
		while (root != null) {
			if (root.deleted)
				removeNode(root);
			else {
				collectGarbage(root.firstChild);
				collectGarbage(root.sib);
			}
			return true;
		}
		return false;

	}

	public Object clone() throws CloneNotSupportedException {
		FHsdTree<E> newObject = (FHsdTree<E>) super.clone();
		newObject.clear(); // can't point to other's data

		newObject.mRoot = clone((FHsdTreeNode<E>) mRoot);
		newObject.mSize = mSize;
		newObject.setMyRoots((FHsdTreeNode<E>) newObject.mRoot);

		return newObject;
	}

	private FHsdTreeNode<E> clone(FHsdTreeNode<E> root) {
		FHsdTreeNode<E> newNode;
		if (root == null)
			return null;

		// does not set myRoot which must be done by caller
		newNode = new FHsdTreeNode<E>(root.data, clone((FHsdTreeNode<E>) root.sib),
				clone((FHsdTreeNode<E>) root.firstChild), null, root.deleted);

		// the prev pointer is set by parent recursive call ... this is the code:
		if (newNode.sib != null)
			newNode.sib.prev = newNode;
		if (newNode.firstChild != null)
			newNode.firstChild.prev = newNode;
		return newNode;
	}

	protected FHsdTreeNode<E> cloneSubtree(FHsdTreeNode<E> root) {
		FHsdTreeNode<E> newNode;
		if (root == null)
			return null;

		// does not set myRoot which must be done by caller
		newNode = new FHsdTreeNode<E>(root.data, cloneSubtree(root.sib), cloneSubtree(root.firstChild), null,
				root.deleted);

		// the prev pointer is set by parent recursive call ... this is the code:
		if (newNode.sib != null)
			newNode.sib.prev = newNode;
		if (newNode.firstChild != null)
			newNode.firstChild.prev = newNode;
		return newNode;
	}

	// recursively sets all myRoots to mRoot
	private void setMyRoots(FHsdTreeNode<E> treeNode) {
		if (treeNode == null)
			return;

		treeNode.myRoot = mRoot;
		setMyRoots(treeNode.sib);
		setMyRoots(treeNode.firstChild);
	}

	public <T extends Traverser<? super E>> void traverse(T func) {
		traverse(func, (FHsdTreeNode<E>) mRoot, 0);
	}

	public <T extends Traverser<? super E>> void traverse(T func, FHsdTreeNode<E> treeNode, int branch) {
		if (treeNode == null || treeNode.isDeleted()) {
			return;
		}
		func.visit(treeNode.data);

		// increase branch
		traverse(func, treeNode.getFirstChild(), branch++);
		if (branch > 0) {
			traverse(func, treeNode.getSibling(), branch);
		}
	}
}
