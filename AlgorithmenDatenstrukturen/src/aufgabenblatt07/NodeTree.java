/**
 * 
 */
package aufgabenblatt07;


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
		
		if (item == null)
			throw new IllegalArgumentException("item must not be null");
		
		//first insert
		if(value==null){
			value = item;
			return true;
		}
		
		final int comp = -value.compareTo(item);
		
		if(comp==LESS){
			if( leftChild != null ) {
				return leftChild.insert(item);
			}
			else{
				leftChild = new NodeTree<T>(item, this);
				return true;
			}
		}
		else if(comp==GREATER){
			if( rightChild != null ) {
				return rightChild.insert(item);
			}
			else{
				rightChild = new NodeTree<T>(item, this);
				return true;
			}
		}
				
		return false;
	}
	
	@Override
	public T getLeftChild(T parent) {
		if (parent == null)
			throw new IllegalArgumentException("item must not be null");
		
		NodeTree<T> node = findNode(parent, getRootNode());
		return node!=null && node.leftChild!=null?node.leftChild.getValue():null;
	}
	
	@Override
	public T getRightChild(T parent) {
		if (parent == null)
			throw new IllegalArgumentException("item must not be null");
		
		NodeTree<T> node = findNode(parent, getRootNode());
		return node!=null && node.rightChild!=null?node.rightChild.getValue():null;
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
		
}
