class FHsdTreeNode<E> extends FHtreeNode<E> {
	protected boolean deleted;
	protected FHsdTreeNode<E> firstChild, sib, prev;

	public FHsdTreeNode() {
		super();
		deleted = false;
	}

	public FHsdTreeNode(E d, FHtreeNode<E> sb, FHtreeNode<E> chld, FHtreeNode<E> prv, boolean dltd) {
		//super(d, sb, chld, prv);
		
		firstChild = (FHsdTreeNode<E>) chld;
		sib = (FHsdTreeNode<E>) sb;
		prev = (FHsdTreeNode<E>) prv;
		data = d;
		deleted = dltd;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public FHsdTreeNode<E> getSibling() {
		return (FHsdTreeNode<E>) this.sib;
	}

	public FHsdTreeNode<E> getFirstChild() {
		return (FHsdTreeNode<E>) firstChild;
	}

	public FHsdTreeNode<E> getPrevious() {
		return (FHsdTreeNode<E>) prev;
	}

	public FHsdTreeNode<E> getRoot() {
		return (FHsdTreeNode<E>) myRoot;
	}

	public void setDeleted(boolean dltd) {
		this.deleted = dltd;
	}

	public boolean setSibling(FHtreeNode<E> sibling) {
		if (sibling == null)
			return false;

		this.sib = (FHsdTreeNode<E>) sibling;
		return true;
	}

	public boolean setFirstChild(FHtreeNode<E> child) {
		if (child == null)
			return false;

		this.firstChild = (FHsdTreeNode<E>) child;
		return true;
	}

	public boolean setPrevious(FHtreeNode<E> previous) {
		if (previous == null)
			return false;

		this.prev = (FHsdTreeNode<E>) previous;
		return true;
	}

	public boolean setRoot(FHtreeNode<E> root) {
		if (root == null)
			return false;

		this.myRoot = root;
		return true;
	}

}