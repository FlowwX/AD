package aufgabenblatt09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class WeightedAdjacencyMatrix implements WeightedAdjacency {
	private HashMap<Node<?>, Integer> nodeIndMap;
	private int[][] mat;
	private int[] conCount;
	private int nextInd;

	public boolean directional = false;

	public WeightedAdjacencyMatrix(int nodes) {
		mat = new int[nodes][nodes];
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < nodes; j++) {
				mat[i][j] = -1;
			}
		}
		conCount = new int[nodes];
		nodeIndMap = new HashMap<Node<?>, Integer>();
		nextInd = 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void insert(WeightedEdge<?> newEdge) {
		Integer originInd = nodeIndMap.putIfAbsent(newEdge.getOrigin(), nextInd);
		if (originInd == null) {
			originInd = nextInd;
			mat[originInd][originInd] = 0;
			calcNextInd();
		}
		Integer destinationInd = nodeIndMap.putIfAbsent(newEdge.getDestination(), nextInd);
		if (destinationInd == null) {
			destinationInd = nextInd;
			mat[destinationInd][destinationInd] = 0;
			calcNextInd();
		}

		mat[originInd][destinationInd] = newEdge.getWeight();
		conCount[originInd]++;
		conCount[destinationInd]++;

		if (directional == false && !existsEdge(newEdge.getDestination(), newEdge.getOrigin()))
			insert(new WeightedEdge(newEdge.getDestination(), newEdge.getOrigin(), newEdge.getWeight()));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void delete(Node<?> node) {
		for (Node<?> other : getNeighbours(node)) {
			delete(new WeightedEdge(node, other, 0));
		}
	}

	@Override
	public void delete(WeightedEdge<?> edge) {
		Integer originInd = nodeIndMap.get(edge.getOrigin());
		Integer destinationInd = nodeIndMap.get(edge.getDestination());
		if (originInd != null && destinationInd != null) {
			mat[originInd][destinationInd] = -1;
			conCount[originInd]--;
			if (conCount[originInd] == 0) {
				nodeIndMap.remove(edge.getOrigin());
				mat[originInd][originInd] = -1;
				calcNextInd();
			}
			conCount[destinationInd]--;
			if (conCount[destinationInd] == 0) {
				nodeIndMap.remove(edge.getDestination());
				mat[destinationInd][destinationInd] = -1;
				calcNextInd();
			}
		}
	}

	@Override
	public List<Node<?>> getNeighbours(Node<?> node) {
		ArrayList<Node<?>> neighbours = new ArrayList<Node<?>>(mat.length);
		Integer nodeInd = nodeIndMap.get(node);
		if (nodeInd != null) {
			for (Node<?> n2 : nodeIndMap.keySet()) {
				if (n2 != node && existsEdge(node, n2))
					neighbours.add(n2);
			}
		}
		neighbours.trimToSize();
		return neighbours;
	}

	@Override
	public boolean existsEdge(Node<?> node1, Node<?> node2) {
		Integer node1Ind = nodeIndMap.get(node1);
		Integer node2Ind = nodeIndMap.get(node2);
		if (node1Ind != null && node2Ind != null && mat[node1Ind][node2Ind] != -1)
			return true;
		return false;
	}

	@Override
	public int getWeight(Node<?> node1, Node<?> node2) {
		Integer node1Ind = nodeIndMap.get(node1);
		Integer node2Ind = nodeIndMap.get(node2);
		if (node1Ind != null && node2Ind != null && mat[node1Ind][node2Ind] != -1)
			return mat[node1Ind][node2Ind];
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("[ ");
		for (int i = 0; i < mat.length; i++) {
			output.append("[ ");
			for (int j = 0; j < mat.length; j++) {
				output.append(String.format("%2d", mat[i][j]) + " ");
			}
			output.append("]\n  ");
		}
		output.insert(output.length() - 3, " ]");

		return output.toString();
	}

	private void calcNextInd() {
		for (int i = 0; i < mat.length; i++) {
			int ind = (nextInd + i) % mat.length;
			if (mat[ind][ind] == -1) {
				nextInd = ind;
			}
		}
	}

	public static void main(String[] args) {
		WeightedAdjacencyMatrix wam = new WeightedAdjacencyMatrix(10);
		Node<String> n1 = new Node<String>("N1");
		Node<String> n2 = new Node<String>("N2");
		Node<String> n3 = new Node<String>("N3");
		wam.insert(new WeightedEdge<String>(n1, n2, 42));
		wam.insert(new WeightedEdge<String>(n1, n3, 41));
		System.out.println(wam);
	}

	@Override
	public Collection<Node<?>> getNodes() {
		return nodeIndMap.keySet();
	}

	/*
	 * TODO: gerichtes/ungerichtes Einfuegen?
	 * testen
	 */
}
