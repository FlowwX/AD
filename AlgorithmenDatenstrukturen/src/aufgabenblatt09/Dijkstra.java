package aufgabenblatt09;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Dijkstra {
	public static <T> Collection<DijkstraNode<T>> calculate(IWeightedGraph<T> graph, Node<T> destination) {
		//// Initialize data structures
		// set of known nodes to evaluate
		HashSet<DijkstraNode<T>> boundary = new HashSet<DijkstraNode<T>>();
		// mapping from Node (from to graph) to DijkstraNode with extra information
		HashMap<Node<T>,DijkstraNode<T>> nodeToDijkstraNode = new HashMap<Node<T>,DijkstraNode<T>>();
		for(Node<T> n : graph.getNodes()){
			nodeToDijkstraNode.put(n, new DijkstraNode<>(n, null, Integer.MAX_VALUE-1)); // -1 wegen Vergleich unten
		}

		// the node that is the next step in order to get to the final destination
		// start at destination node
		DijkstraNode<T> nextNode = nodeToDijkstraNode.get(destination);
		nextNode.cost = graph.getWeight(destination, destination);

		do{
			nextNode.finished = true;
			boundary.remove(nextNode);

			// add neighbours to boundary,
			// if a neighbour is already in boundary, check if the route usuing the new 'nextNode' is better
			List<Node<T>> neighbours = graph.getNeighbours(nextNode.node);
			for(Node<T> boundaryCandidate : neighbours){
				DijkstraNode<T> n = nodeToDijkstraNode.get(boundaryCandidate);
				if(n.finished == false){
					if(boundary.add(n)){
						int weight = graph.getWeight(boundaryCandidate, nextNode.node);
						n.cost = nextNode.cost + weight;
						n.next = nextNode;
					} else{
						int weight = graph.getWeight(n.node, nextNode.node);
						if(n.cost > nextNode.cost + weight){
							n.cost = nextNode.cost + weight;
							n.next = nextNode;
						}
					}
				}
			}

			// remove node with minimum cost from boundary and set 'nextNode' to it
			int minCost = Integer.MAX_VALUE;
			DijkstraNode<T> minNode = null;
			for(DijkstraNode<T> n : boundary){
				if(n.cost < minCost){
					minNode = n;
					minCost = n.cost;
				}
			}
			if(minNode == null) break; // boundary is empty, work done
			nextNode = minNode;
		} while(boundary.size() > 0);

		return nodeToDijkstraNode.values();
	}

	public static void main(String[] args) {
		int nodeCount = 5;
		IWeightedGraph<Integer> wg = WeightedGraphBuilder.getEmptyNodes(nodeCount, AdjacencyImplementation.LIST);
		System.out.println(wg);

		@SuppressWarnings("unchecked")
		Node<Integer> destination = (wg.getNodes().toArray(new Node[0]))[new Random().nextInt(nodeCount)];
		Collection<DijkstraNode<Integer>> dijkstraResult = calculate(wg, destination);
		System.out.println("To node no. " + destination.uid);
		for (DijkstraNode<Integer> n : dijkstraResult) {
			System.out.println("Node no. " + n.node.uid + ": costs " + n.cost + " over " + n.next.node.uid);
		}
	}
}
