/**
 * 
 */
package aufgabenblatt07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 
 *
 */
public class NodeTree<T extends Comparable<T>> extends Tree<T> {

	
	private T value;
	
	private NodeTree<T> parent;


	private NodeTree<T> leftChild;
	private NodeTree<T> rightChild;
	
	public final static int LESS    = -1;
	public final static int GREATER = 1;
	public final static int EQUAL   = 0;
	
	public NodeTree(T item, NodeTree<T> parent) {
		this.parent = parent;
		value = item;
	}
	
	@Override
	public boolean insert(T item) {
		
		final int comp = -value.compareTo(item);
		
		if(comp==LESS||comp==EQUAL){
			if( leftChild != null ) {
				leftChild.insert(item);
			}
			else{
				leftChild = new NodeTree<T>(item, this);
			}
		}
		else if(comp==GREATER){
			if( rightChild != null ) {
				rightChild.insert(item);
			}
			else{
				rightChild = new NodeTree<T>(item, this);
			}
		}
				
		return false;
	}
	
	@Override
	public T getLeftChild(T parent) {
		NodeTree<T> node = findNode(parent, getRootNode());
		return node.leftChild!=null?node.leftChild.getValue():null;
	}
	
	@Override
	public T getRightChild(T parent) {
		NodeTree<T> node = findNode(parent, getRootNode());
		return node.rightChild!=null?node.rightChild.getValue():null;
	}

	public T getValue() {
		return value;
	}
	
	public NodeTree<T> getParent() {
		return parent;
	}
	
	
	private NodeTree<T> getRootNode() {
		NodeTree<T> current = this;
		while(current.parent!=null){
			current = current.getParent();
		}
		return current;
	}
	
	@Override
	protected T getRoot() {
		return getRootNode().getValue();
	}
	
	private NodeTree<T> findNode(T value, NodeTree<T> root){
		
		if(root==null){
			return  null;
		}
		
		if( root.getValue().equals(value)){
			return root;
		}
		
		NodeTree<T> l = findNode(value, root.leftChild);
		NodeTree<T> r = findNode(value, root.rightChild);
		
		if(l!=null){
			return l;
		}
		
		return  r;
	}
	
	/**
	 *  Ugly testing
	 * @param args
	 */
	public static void main(String[] args) {
		NodeTree<Integer> btree = new NodeTree<Integer>(3, null);
		
		btree.insert(1);
		btree.insert(7);
		btree.insert(5);
		btree.insert(9);
		btree.insert(4);
		btree.insert(8);
		btree.insert(6);
		btree.insert(0);
		
		List<Integer> list = btree.inOrder();
System.out.println(list);
		
		System.out.println("fertig");
		
		
	}


	
}
