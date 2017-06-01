package aufgabenblatt09;

import java.util.Iterator;
import java.util.List;


public class WeightedGraph<T> implements IWeightedGraph<T> {
	private WeightedAdjacency adjacency;
	
	public WeightedGraph(WeightedAdjacency adjacency) {
		this.adjacency = adjacency;
	}

	@Override
	public void insert(WeightedEdge<T> newEdge) {
		adjacency.insert(newEdge);
	}

	@Override
	public void delete(WeightedEdge<T> edge) {
		adjacency.delete(edge);
	}

	@Override
	public void delete(Node<T> node) {
		adjacency.delete(node);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Node<T>> getNeighbours(Node<T> node) {
		return (List<Node<T>>)(Object)adjacency.getNeighbours(node);
	}

	@Override
	public boolean existsEdge(Node<T> node1, Node<T> node2) {
		return adjacency.existsEdge(node1, node2);
	}

	@Override
	public int getWeight(Node<T> node1, Node<T> node2) {
		return adjacency.getWeight(node1, node2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Node<T>> iterator() {
		return (Iterator<Node<T>>)(Object)adjacency.getNodes().iterator();
	}

}
