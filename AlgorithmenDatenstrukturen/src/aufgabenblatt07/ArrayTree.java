package aufgabenblatt07;

import java.util.ArrayList;

public class ArrayTree<T extends Comparable<T>> extends Tree<T>{
	private ArrayList<T> arr;
	
	public ArrayTree(int size){
		arr = new ArrayList<T>(size);
	}

	@Override
	public boolean insert(T item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T getLeftChild(T parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getRightChild(T parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
