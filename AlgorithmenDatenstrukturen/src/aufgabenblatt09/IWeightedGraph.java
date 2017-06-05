package aufgabenblatt09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Interface for a weighted graph.
 */
public interface IWeightedGraph<T> {
	void insert(WeightedEdge<T> newEdge);
	void delete(WeightedEdge<T> edge);
	void delete(Node<T> node);
	List<Node<T>> getNeighbours(Node<T> node);
	boolean existsEdge(Node<T> node1, Node<T> node2);
	int getWeight(Node<T> node1, Node<T> node2);
	Iterator<Node<T>> iterator();
	Collection<Node<T>> getNodes();
}
