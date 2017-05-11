package aufgabenblatt07;

import java.util.ArrayList;

public class ArrayTree<T extends Comparable<T>> extends Tree<T> {
	private ArrayList<T> arr;

	public ArrayTree(int size) {
		arr = new ArrayList<T>(size + 1);
	}

	@Override
	public boolean insert(T item) {
		return false;
	}

	@Override
	public T getLeftChild(T parent) {
		int ind = getLeftChildInd(getIndexOf(parent));
		return ind > 0 ? arr.get(ind) : null;
	}

	@Override
	public T getRightChild(T parent) {
		int ind = getRightChildInd(getIndexOf(parent));
		return ind > 0 ? arr.get(ind) : null;
	}

	private int getLeftChildInd(int parentInd) {
		return parentInd > 0 ? 2 * parentInd : 0;
	}

	private int getRightChildInd(int parentInd) {
		return parentInd > 0 ? 2 * parentInd + 1 : 0;
	}

	private int getIndexOf(T item) {
		int i = 1;
		int cmp = item.compareTo(arr.get(i));
		while (cmp != 0 && i <= arr.size()) {
			if (cmp < 0)
				i = getLeftChildInd(i);
			else
				i = getRightChildInd(i);
		}
		return i <= arr.size() ? i : 0;
	}
}
