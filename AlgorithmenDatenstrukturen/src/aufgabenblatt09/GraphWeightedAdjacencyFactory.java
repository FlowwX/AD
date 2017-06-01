package aufgabenblatt09;

import java.util.ArrayList;

enum AdjacencyImplementation {MATRIX, LIST};
public class GraphWeightedAdjacencyFactory {
	static WeightedAdjacency getEmptyNodesAdjacency(int nodeCount, AdjacencyImplementation impl){
		WeightedAdjacency wa;
		if(impl == AdjacencyImplementation.MATRIX)
			wa = new WeightedAdjacencyMatrix(nodeCount);
		else
			wa = new WeightedAdjacencyList();
		
		@SuppressWarnings("unchecked")
		Node<Integer>[] nodes = (Node<Integer>[]) new Node[nodeCount];
		for(int i = 0; i < nodeCount; i++){
			nodes[i] = new Node<Integer>(null);
		}
		
		for(int i = 0; i < nodeCount-1; i++){
			wa.insert(new WeightedEdge<>(nodes[i], nodes[i+1], 1));
		}
		
		return wa;
	}
}
