package aufgabenblatt09;

/**
 * Generic Node with unique integer UID for a Graph. 
 */
public class Node<T> {
	public T payload;
	public final int uid;

	private static int nextUid = 0;

	public Node(){
		this(null);
	}

	public Node(T payload){
		this.payload = payload;
		this.uid = nextUid++;
	}

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
