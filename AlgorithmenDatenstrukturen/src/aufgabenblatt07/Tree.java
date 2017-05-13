/**
 * 
 */
package aufgabenblatt07;

/**
 * @author
 *
 */
public abstract class Tree<T extends Comparable<T>> {

	/**
	 * @brief Inserts item T into the tree at the right position according to is
	 *        value
	 * @param item:
	 *            Item to insert into the tree. Must not be null. There must not
	 *            already be another item with the same value (regarding its
	 *            comparisons) in the tree.
	 * @return True if item was inserted, false if item was already in the tree
	 * @exception InvalidArgumentException
	 *                if item is null
	 * 
	 */
	public abstract boolean insert(T item);

	/**
	 * @brief Returns the left (smaller) child of a parent node
	 * @param parent:
	 *            The parent node to get the child of
	 * @return Child item, if the parent has a left child. null, if the parent
	 *         is not in the tree. null, if the parent has no left child.
	 * @exception InvalidArgumentException
	 *                if parent is null
	 */
	public abstract T getLeftChild(T parent);

	/**
	 * @brief Returns the right (greater) child of a parent node
	 * @param parent:
	 *            The parent node to get the child of
	 * @return Child item, if the parent has a right child. null, if the parent
	 *         is not in the tree. null, if the parent has no right child.
	 * @exception InvalidArgumentException
	 *                if parent is null
	 */
	public abstract T getRightChild(T parent);

	/**
	 * @brief Returns the root element of the tree
	 * @return Root item of the tree, null if the tree is empty 
	 */
	protected abstract T getRoot();

	/**
	 * @brief Gets the items in tree in preOrder ordering
	 * @return Array of T with all the items currently in the tree
	 */
	public T[] preOrder() {
		return null;
	}

	/**
	 * @brief Gets the items in tree in postOrder ordering
	 * @return Array of T with all the items currently in the tree
	 */
	public T[] postOrder() {
		return null;
	}

	/**
	 * @brief Gets the items in tree in inOrder ordering
	 * @return Array of T with all the items currently in the tree
	 */
	public T[] inOrder() {
		return null;
	}

}
