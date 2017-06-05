package aufgabenblatt09;

public class DijkstraNode<T> extends Node<T>{
	public DijkstraNode<T> next;
	public int cost;

	public DijkstraNode(Node<T> node, DijkstraNode<T> next, int cost) {
		this(node.payload, next, cost);
	}
	
	public DijkstraNode(T payload, DijkstraNode<T> next, int cost) {
		super(payload);
		
		if(next == null) next = this;
		this.next = next;
		this.cost = cost;
	}

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null) return false;
		if(!(o instanceof DijkstraNode<?>)) return false;
		@SuppressWarnings("unchecked")
		DijkstraNode<T> on = (DijkstraNode<T>) o;
		return this.uid == on.uid;
	}

	@Override
	public int hashCode(){
		return this.uid;
	}
}
