package aufgabenblatt09;

/**
 * Generic Node with unique integer UID for a Graph.
 * The node holds a data payload of the generic type T.
 * It only holds the payload, and does not use it in any way.
 */
public class Node<T> {
	public T payload;
	public final int uid;

	/**
	 * uid of the next instance of a Node.
	 * Must be used and then incremented in the constructor.
	 */
	private static int nextUid = 0;

	public Node(){
		this(null);
	}

	public Node(T payload){
		this.payload = payload;
		this.uid = nextUid++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null) return false;
		if(!(o instanceof Node<?>)) return false;
		Node<T> on = (Node<T>) o;
		return this.uid == on.uid;
	}

	@Override
	public int hashCode(){
		return uid;
	}
}
