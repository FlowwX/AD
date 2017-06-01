package aufgabenblatt09;

import java.util.Iterator;
import java.util.List;


public class WeightedGraph<T> implements IWeightedGraph<T> {
	private WeightedAdjacency adjacency;
	
	public WeightedGraph(WeightedAdjacency adjacency) {
		this.adjacency = adjacency;
	}

	@Override
	public void insert(WeightedEdge<T> newEdge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(WeightedEdge<T> edge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Node<T> node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> getNeighbours(Node<T> node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEdge(Node<T> node1, Node<T> node2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getWeight(Node<T> node1, Node<T> node2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
