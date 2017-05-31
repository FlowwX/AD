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
}
