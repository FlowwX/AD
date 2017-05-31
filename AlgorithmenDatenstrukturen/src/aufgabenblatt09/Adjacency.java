package aufgabenblatt09;

import java.util.List;

public interface Adjacency {
	void insert(Edge<?> newEdge);
	void delete(Edge<?> edge);
	List<Node<?>> getNeighbours(Node<?> node);
	boolean existsEdge(Node<?> node1, Node<?> node2);
	int getWeight(Node<?> node1, Node<?> node2);
}
