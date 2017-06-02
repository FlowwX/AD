package aufgabenblatt09;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra {
	public static <T> List<DijkstraNode<T>> calculate(WeightedGraph<T> graph, Node<T> destination) {
		LinkedList<DijkstraNode<T>> nodes = new LinkedList<DijkstraNode<T>>();
		LinkedList<Node<T>> nodes_ = new LinkedList<Node<T>>();
		HashSet<DijkstraNode<T>> randliste = new HashSet<DijkstraNode<T>>();
		
		DijkstraNode<T> cDest = new DijkstraNode(destination, null, 0);
		nodes.add(cDest);
		nodes_.add(destination);

		do{
			for(Node<T> randlistenKandidat : graph.getNeighbours(cDest.node)){
				if(!nodes_.contains(randlistenKandidat))
					randliste.add(new DijkstraNode<>(randlistenKandidat, cDest, cDest.cost + graph.getWeight(randlistenKandidat, cDest.node)));
			}
			
			for(DijkstraNode<T> n : randliste){
				if(graph.existsEdge(n.node, cDest.node)){
					if(n.cost > cDest.cost + graph.getWeight(n.node, cDest.node)){
						n.cost = cDest.cost + graph.getWeight(n.node, cDest.node);
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
		WeightedGraph<Integer> wg = new WeightedGraph<>(
				GraphWeightedAdjacencyFactory.getSpecialEmptyNodesAdjacency(10, AdjacencyImplementation.MATRIX));
		System.out.println(wg);

		List<DijkstraNode<Integer>> dijkstraResult = calculate(wg, (Node<Integer>) GraphWeightedAdjacencyFactory.dest);
		System.out.println("To node no. " + GraphWeightedAdjacencyFactory.dest.uid);
		for (DijkstraNode<Integer> n : dijkstraResult) {
			System.out.println("Node no. " + n.node.uid + ": costs " + n.cost + " over " + n.next.node.uid);
		}
	}
}
