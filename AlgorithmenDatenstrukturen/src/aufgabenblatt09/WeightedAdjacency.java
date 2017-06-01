package aufgabenblatt09;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface WeightedAdjacency extends Serializable {
	void insert(WeightedEdge<?> newEdge);
	void delete(WeightedEdge<?> edge);
	void delete(Node<?> node);
	List<Node<?>> getNeighbours(Node<?> node);
	boolean existsEdge(Node<?> node1, Node<?> node2);
	int getWeight(Node<?> node1, Node<?> node2);
	Collection<Node<?>> getNodes();
}
