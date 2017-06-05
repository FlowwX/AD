package aufgabenblatt09;

public class DijkstraNode<T> {
	public Node<T> node;
	public DijkstraNode<T> next;
	public int cost;
	public boolean finished;

	public DijkstraNode(Node<T> node, DijkstraNode<T> next, int cost) {
		if(next == null) next = this;
		this.node = node;
		this.next = next;
		this.cost = cost;
		this.finished = false;
	}

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null) return false;
		if(!(o instanceof DijkstraNode<?>)) return false;
		DijkstraNode<T> on = (DijkstraNode<T>) o;
		return this.node.equals(on.node);
	}

	@Override
	public int hashCode(){
		return this.node.uid;
	}
}
