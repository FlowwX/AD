package aufgabenblatt09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class WeightedGraphMatrix<T> implements IWeightedGraph<T> {
	private HashMap<Node<T>, Integer> nodeIndMap;
	private int[][] mat;
	private int[] conCount;
	private int nextInd;

	public boolean directional = false;

	public WeightedGraphMatrix(int nodes) {
		mat = new int[nodes][nodes];
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < nodes; j++) {
				mat[i][j] = -1;
			}
		}
		conCount = new int[nodes];
		nodeIndMap = new HashMap<Node<T>, Integer>();
		nextInd = 0;
	}

	@Override
	public void insert(WeightedEdge<T> newEdge) {
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
			insert(new WeightedEdge<T>(newEdge.getDestination(), newEdge.getOrigin(), newEdge.getWeight()));
	}

	@Override
	public void delete(Node<T> node) {
		for (Node<T> other : getNeighbours(node)) {
			delete(new WeightedEdge<T>(node, other, 0));
		}
	}

	@Override
	public void delete(WeightedEdge<T> edge) {
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
	public List<Node<T>> getNeighbours(Node<T> node) {
		ArrayList<Node<T>> neighbours = new ArrayList<Node<T>>(mat.length);
		Integer nodeInd = nodeIndMap.get(node);
		if (nodeInd != null) {
			for (Node<T> n2 : nodeIndMap.keySet()) {
				if (n2 != node && existsEdge(node, n2))
					neighbours.add(n2);
			}
		}
		neighbours.trimToSize();
		return neighbours;
	}

	@Override
	public boolean existsEdge(Node<T> node1, Node<T> node2) {
		Integer node1Ind = nodeIndMap.get(node1);
		Integer node2Ind = nodeIndMap.get(node2);
		if (node1Ind != null && node2Ind != null && mat[node1Ind][node2Ind] != -1)
			return true;
		return false;
	}

	@Override
	public int getWeight(Node<T> node1, Node<T> node2) {
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

	public Collection<Node<T>> getNodes() {
		return nodeIndMap.keySet();
	}
	
	@Override
	public Iterator<Node<T>> iterator() {
		return nodeIndMap.keySet().iterator();
	}
}