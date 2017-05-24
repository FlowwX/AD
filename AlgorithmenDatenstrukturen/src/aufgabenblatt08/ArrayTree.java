package aufgabenblatt08;

/**
 * Implementation of interface "Tree" using an array as data storage.
 */
public class ArrayTree<T extends Comparable<T>> extends Tree<T> {
	/**
	 * Helper class: Generic array with automatically increasing size.
	 */
	private class customArray<AT> {
		private AT[] arr;

		public customArray() {
			this(20);
		}

		@SuppressWarnings("unchecked")
		public customArray(int size) {
			arr = (AT[]) new Object[size];
		}

		/**
		 * Gets an item from the array.
		 * 
		 * @param index
		 *            Should be positive, will return null for all indices < 0
		 * @return Element at index, if present. null elsewise.
		 */
		public AT get(int index) {
			if (index >= 0 && index < arr.length) {
				return arr[index];
			} else
				return null;
		}

		/**
		 * Sets an element of the array to given item. The element is specified
		 * by the index. If the index is < 0, the array remains unchanged. If
		 * the index is outside the array range, the array is extended to the
		 * required size. Newly generated elements are set to null. Any
		 * pre-existing item at the index will be overriden.
		 * 
		 * @param index
		 *            Position in the array to set. Must be >= 0.
		 * @param item
		 *            Item to set at the index.
		 */
		@SuppressWarnings("unchecked")
		public void set(int index, AT item) {
			if (index < 0)
				return;
			if (index >= arr.length) {
				Object[] tmpArr = new Object[index + 1];
				System.arraycopy(arr, 0, tmpArr, 0, arr.length);
				arr = (AT[]) tmpArr;
			}
			arr[index] = item;
		}

		/**
		 * Returns the physical length of the array, regardless of how many
		 * actual items are stored in it.
		 *
		 * @return Size of the array
		 */
		public int size() {
			return arr.length;
		}
	}

	private customArray<T> arr;

	/**
	 * Default Constructor. Creates ArrayTree without specifying initial array
	 * length.
	 */
	public ArrayTree() {
		arr = new customArray<T>();
		arr.set(0, null);
	}

	/**
	 * Constructor
	 *
	 * @param size
	 *            Initial size of the array
	 */
	public ArrayTree(int size) {
		arr = new customArray<T>(size + 1);
		arr.set(0, null);
	}

	@Override
	public boolean insert(T item) {
		if (item == null)
			throw new IllegalArgumentException("item must not be null");

		int ind = findInsertionInd(item);
		if (ind == 0)
			return false;
		arr.set(ind, item);
		return true;
	}

	@Override
	public T getLeftChild(T parent) {
		if (parent == null)
			throw new IllegalArgumentException("parent must not be null");

		int ind = getLeftChildInd(getIndexOf(parent));
		return ind > 0 ? arr.get(ind) : null;
	}

	@Override
	public T getRightChild(T parent) {
		if (parent == null)
			throw new IllegalArgumentException("parent must not be null");

		int ind = getRightChildInd(getIndexOf(parent));
		return ind > 0 ? arr.get(ind) : null;
	}

	@Override
	protected T getRoot() {
		return arr.get(1);
	}

	/**
	 * Retrieves the index for insertion of an item.
	 *
	 * @param item
	 * @return 0 if the item is already present in the tree, the correct index
	 *         to insert elsewise
	 */
	private int findInsertionInd(T item) {
		int indParent = 1;
		int indLeft = getLeftChildInd(indParent);
		int indRight = getRightChildInd(indParent);

		// Binary search through the tree; if item is found, return 0;
		// return the a position of a null element
		while (arr.get(indParent) != null) {
			indLeft = getLeftChildInd(indParent);
			indRight = getRightChildInd(indParent);

			int cmpParent = item.compareTo(arr.get(indParent));
			if (cmpParent < 0) {
				if (arr.get(indLeft) == null) {
					return indLeft;
				} else {
					indParent = indLeft;
				}
			} else if (cmpParent > 0) {
				if (arr.get(indRight) == null) {
					return indRight;
				} else {
					indParent = indRight;
				}
			} else
				return 0;
		}
		return indParent;
	}

	private int getLeftChildInd(int parentInd) {
		return parentInd < 1 ? 0 : 2 * parentInd;
	}

	private int getRightChildInd(int parentInd) {
		return parentInd < 1 ? 0 : 2 * parentInd + 1;
	}

	/*
	 * Binary search
	 */
	private int getIndexOf(T item) {
		int i = 1;

		T node = arr.get(i);
		while (node != null) {
			int cmp = item.compareTo(node);
			if (cmp == 0)
				return i;
			else if (cmp < 0)
				i = getLeftChildInd(i);
			else
				i = getRightChildInd(i);
			node = arr.get(i);
		}
		return 0;
	}

	@Override
	public String toString() {
		String str = "[ ";
		for (int i = 0; i < arr.size(); i++) {
			T tmp = arr.get(i);
			str += tmp == null ? "null " : tmp.toString() + " ";
		}
		return str + "]";
	}
}
