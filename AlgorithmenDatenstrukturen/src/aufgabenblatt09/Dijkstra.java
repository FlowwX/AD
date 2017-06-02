package aufgabenblatt09;

import java.util.List;

public class Dijkstra {
	public static <T> List<DijkstraNode> calculate(WeightedGraph graph, T destination) {
		return null;
	}
	
	public static void main(String[] args) {
		WeightedGraph<?> wg = new WeightedGraph<>(GraphWeightedAdjacencyFactory.getEmptyNodesAdjacency(10, AdjacencyImplementation.LIST));
		System.out.println(wg);
	}
}
