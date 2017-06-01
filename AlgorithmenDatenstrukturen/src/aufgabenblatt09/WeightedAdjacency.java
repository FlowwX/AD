package aufgabenblatt09;

import java.util.List;

public interface WeightedAdjacency {
	void insert(WeightedEdge<?> newEdge);
	void delete(WeightedEdge<?> edge);
	List<Node<?>> getNeighbours(Node<?> node);
	boolean existsEdge(Node<?> node1, Node<?> node2);
	int getWeight(Node<?> node1, Node<?> node2);
}
