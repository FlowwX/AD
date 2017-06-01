package aufgabenblatt09;

/**
 * Edge between to nodes in a graph.
 */
public class Edge<T> {
	protected Node<T> origin;
	protected Node<T> destination;
	
	public Node<T> getOrigin() {
		return origin;
	}
	public void setOrigin(Node<T> origin) {
		this.origin = origin;
	}
	
	public Node<T> getDestination() {
		return destination;
	}
	public void setDestination(Node<T> destination) {
		this.destination = destination;
	}
}
