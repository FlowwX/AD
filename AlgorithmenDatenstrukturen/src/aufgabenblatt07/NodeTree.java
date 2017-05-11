/**
 * 
 */
package aufgabenblatt07;

import java.util.Comparator;

/**
 * @author 
 *
 */
public class NodeTree<T extends Comparable<T>> extends Tree<T> {

	private T value;
	private NodeTree<T> leftChild;
	private NodeTree<T> rightChild;
	
	public final static int LESS    = -1;
	public final static int GREATER = 1;
	public final static int EQUAL   = 0;
	
	public NodeTree(T item) {
		value = item;
	}
	
	@Override
	public boolean insert(T item) {

		switch (-value.compareTo(item)) {
			case LESS:
				if( leftChild != null ) {
					leftChild.insert(item);
				}
				else{
					leftChild = new NodeTree<T>(item);
				}
			break;
			case GREATER:
				if( rightChild != null ) {
					rightChild.insert(item);
				}
				else{
					rightChild = new NodeTree<T>(item);
				}
			break;
			case EQUAL:
				if( leftChild != null ) {
					leftChild.insert(item);
				}
				else{
					leftChild = new NodeTree<T>(item);
				}
			break;
		}
		

		
		return false;
	}
	
	@Override
	public T getLeftChild(T parent) {
		return (T) leftChild.getValue();
	}
	
	@Override
	public T getRightChild(T parent) {
		return (T) rightChild.getValue();
	}

	public T getValue() {
		return value;
	}
	
	/**
	 *  Ugly testing
	 * @param args
	 */
	public static void main(String[] args) {
		NodeTree<Integer> btree = new NodeTree<Integer>(3);
		
		btree.insert(1);
		btree.insert(7);
		btree.insert(5);
		btree.insert(9);
		btree.insert(4);
		btree.insert(8);
		btree.insert(6);
		btree.insert(0);
		
		System.out.println("fertig");
	}
	
}
