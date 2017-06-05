package aufgabenblatt09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra {
	public static <T> List<DijkstraNode<T>> calculate(IWeightedGraph<T> graph, Node<T> destination) {
		
		ArrayList<DijkstraNode<T>> nodes = new ArrayList<DijkstraNode<T>>();
		LinkedList<Node<T>> nodes_ = new LinkedList<Node<T>>();
		HashSet<DijkstraNode<T>> randliste = new HashSet<DijkstraNode<T>>();
		
		DijkstraNode<T> cDest = new DijkstraNode<T>(destination, null, 0);
		nodes.add(cDest);
		nodes_.add(destination);

		do{
			List<Node<T>> neighbours = graph.getNeighbours(cDest.node);
			for(Node<T> randlistenKandidat : neighbours){
				if(!nodes_.contains(randlistenKandidat)) {
					int weight = graph.getWeight(randlistenKandidat, cDest.node);
					randliste.add(new DijkstraNode<>(randlistenKandidat, cDest, cDest.cost + weight ));
				}
			}
			
			for(DijkstraNode<T> n : randliste){
				if(graph.existsEdge(n.node, cDest.node)){
					int weight = graph.getWeight(n.node, cDest.node);
					if(n.cost > cDest.cost + weight){
						n.cost = cDest.cost + weight;
						n.next = cDest;
					}
				}
			}
			
			int minCost = Integer.MAX_VALUE;
			DijkstraNode<T> minNode = null;
			for(DijkstraNode<T> n : randliste){
				if(graph.existsEdge(n.node, cDest.node)){
					if(n.cost < minCost){
						minNode = n;
						minCost = n.cost;
					}
				}
			}
			if(minNode == null) return nodes;
			cDest = minNode;
			nodes.add(cDest);
			nodes_.add(cDest.node);
			randliste.remove(cDest);
		} while(randliste.size() > 0);

		return nodes;
	}

	public static void main(String[] args) {
		IWeightedGraph<Integer> wg = WeightedGraphFactory.getSpecialEmptyNodesAdjacency(10, AdjacencyImplementation.LIST);
		System.out.println(wg);

		@SuppressWarnings("unchecked")
		List<DijkstraNode<Integer>> dijkstraResult = calculate(wg, (Node<Integer>) WeightedGraphFactory.dest);
		System.out.println("To node no. " + WeightedGraphFactory.dest.uid);
		for (DijkstraNode<Integer> n : dijkstraResult) {
			System.out.println("Node no. " + n.node.uid + ": costs " + n.cost + " over " + n.next.node.uid);
		}
	}
}
