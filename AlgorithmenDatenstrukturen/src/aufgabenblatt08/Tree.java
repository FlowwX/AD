/**
 * 
 */
package aufgabenblatt08;

import java.util.LinkedList;
import java.util.List;

public abstract class Tree<T extends Comparable<T>> {

	/**
	 * Inserts item T into the tree at the right position according to is value
	 * 
	 * @param item
	 *            Item to insert into the tree. Must not be null. There must not
	 *            already be another item with the same value (regarding its
	 *            comparisons) in the tree.
	 * @return True if item was inserted, false if item was already in the tree
	 * @exception IllegalArgumentException
	 *                if item is null
	 * 
	 */
	public abstract boolean insert(T item);

	/**
	 * Returns the left (smaller) child of a parent node
	 * @param parent
	 *            The parent node to get the child of
	 * @return Child item, if the parent has a left child. null, if the parent
	 *         is not in the tree. null, if the parent has no left child.
	 * @exception IllegalArgumentException
	 *                if parent is null
	 */
	public abstract T getLeftChild(T parent);

	/**
	 * Returns the right (greater) child of a parent node
	 * @param parent
	 *            The parent node to get the child of
	 * @return Child item, if the parent has a right child. null, if the parent
	 *         is not in the tree. null, if the parent has no right child.
	 * @exception IllegalArgumentException
	 *                if parent is null
	 */
	public abstract T getRightChild(T parent);

	/**
	 * Returns the root element of the tree
	 * @return Root item of the tree, null if the tree is empty
	 */
	protected abstract T getRoot();

	/**
	 * Gets the items in tree in preOrder ordering
	 * @return List of T with all the items currently in the tree
	 */
	public List<T> preOrder() {
		return preOrderRec(getRoot());
	}

	private List<T> preOrderRec(T root) {
		List<T> l = new LinkedList<T>();
		if (root != null) {
			l.add(root);
			l.addAll(preOrderRec(getLeftChild(root)));
			l.addAll(preOrderRec(getRightChild(root)));
		}
		return l;
	}

	/**
	 * Gets the items in tree in postOrder ordering
	 * @return List of T with all the items currently in the tree
	 */
	public List<T> postOrder() {
		return postOrderRec(getRoot());
	}

	private List<T> postOrderRec(T root) {
		List<T> l = new LinkedList<T>();
		if (root != null) {
			l.addAll(postOrderRec(getLeftChild(root)));
			l.addAll(postOrderRec(getRightChild(root)));
			l.add(root);
		}
		return l;
	}

	/**
	 * Gets the items in tree in inOrder ordering
	 * @return List of T with all the items currently in the tree
	 */
	public List<T> inOrder() {
		return inOrderRec(getRoot());
	}

	private List<T> inOrderRec(T root) {
		List<T> l = new LinkedList<T>();
		if (root != null) {
			l.addAll(inOrderRec(getLeftChild(root)));
			l.add(root);
			l.addAll(inOrderRec(getRightChild(root)));
		}
		return l;
	}

	public T find(T comparator){
		T node = getRoot();
		while(node != null){
			if(node.compareTo(comparator) > 0)
				node = getLeftChild(node);
			else if(node.compareTo(comparator) < 0)
				node = getRightChild(node);
			else break;
		}
		return node;
	}
}
