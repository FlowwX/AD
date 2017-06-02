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
	static public Node<?> dest;
	@SuppressWarnings({ "unchecked" })
	static <T> WeightedAdjacency getSpecialEmptyNodesAdjacency(int nodeCount, AdjacencyImplementation impl){
		WeightedAdjacency wa;
		if(impl == AdjacencyImplementation.MATRIX)
			wa = new WeightedAdjacencyMatrix(nodeCount);
		else
			wa = new WeightedAdjacencyList();
		
		Node<T>[] nodes = (Node<T>[]) new Node[nodeCount];
		for(int i = 0; i < 6; i++){
			nodes[i] = new Node<T>(null);
		}
		
		dest = nodes[5];
		
		wa.insert(new WeightedEdge<T>(nodes[0], nodes[1], 1)); // A->B
		wa.insert(new WeightedEdge<T>(nodes[0], nodes[2], 6)); // A->C
		wa.insert(new WeightedEdge<T>(nodes[0], nodes[3], 7)); // A->D
		wa.insert(new WeightedEdge<T>(nodes[1], nodes[3], 1)); // B->D
		wa.insert(new WeightedEdge<T>(nodes[1], nodes[4], 5)); // B->E
		wa.insert(new WeightedEdge<T>(nodes[2], nodes[5], 3)); // C->F
		wa.insert(new WeightedEdge<T>(nodes[3], nodes[5], 1)); // D->F

		return wa;
	}
}
