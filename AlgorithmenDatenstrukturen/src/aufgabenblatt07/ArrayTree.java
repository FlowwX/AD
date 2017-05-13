package aufgabenblatt07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTree<T extends Comparable<T>> extends Tree<T> {
	private class customArray<T>{
		private T[] arr;
		public customArray(){
			this(20);
		}
		public customArray(int size){
			arr = (T[])new Object[size];
		}
		public T get(int index){
			if(index >= 0 && index < arr.length){
				return arr[index];
			} else return null;
		}
		public void set(int index, T item){
			if(index < 0) return;
			if(index >= arr.length){
				Object[] tmpArr = new Object[index + 1];
				System.arraycopy(arr, 0, tmpArr, 0, arr.length);
				arr = (T[])tmpArr;
			}
			arr[index] = item;
		}
		public int size(){
			return arr.length;
		}
	}
	private customArray<T> arr;

	public ArrayTree(int size) {
		arr = new customArray<T>(size + 1);
		arr.set(0, null);
	}

	@Override
	public boolean insert(T item) {
		int ind = findInsertionInd(item);
		if (ind == 0)
			return false;
		insert(item, ind);
		return true;
	}

	private int findInsertionInd(T item) {
		int indParent = 1;
		int indLeft = getLeftChildInd(indParent);
		int indRight = getRightChildInd(indParent);

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
			} else return 0; // schon vorhanden
		}
		return indParent;
	}
	
	private boolean isBetween(T item, T lowerBound, T upperBound){
		return (lowerBound == null || item.compareTo(lowerBound) > 0) && (upperBound == null || item.compareTo(upperBound) < 0);
	}

	private void insert(T item, int ind) {
		if (arr.get(ind) != null) {
			if (item.compareTo(arr.get(ind)) < 0) {
				insert(arr.get(ind), getRightChildInd(ind));
			} else if (item.compareTo(arr.get(ind)) > 0) {
				insert(arr.get(ind), getLeftChildInd(ind));
			}
		}
		arr.set(ind, item);
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
		return parentInd < 1 ? 0 : 2 * parentInd;
	}

	private int getRightChildInd(int parentInd) {
		return parentInd < 1 ? 0 : 2 * parentInd + 1;
	}

	private int getIndexOf(T item) {
		int i = 1;

		T node = arr.get(i);
		while(node != null){
			int cmp = item.compareTo(node);
			if(cmp == 0)
				return i;
			else if(cmp < 0)
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
			str +=  tmp == null ? "null " : tmp.toString() + " ";
		}
		return str + "]";
	}

	@Override
	protected T getRoot(){
		return arr.get(1);
	}
}
