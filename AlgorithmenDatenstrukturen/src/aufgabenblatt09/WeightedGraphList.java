package aufgabenblatt09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeightedGraphList<T> implements IWeightedGraph<T> {

	Map<Node<T>,List<Node<T>>> nodes = new HashMap<Node<T>,List<Node<T>>>();
	Map<Node<T>,List<Edge<T>>> edges = new HashMap<Node<T>,List<Edge<T>>>();

	
	@Override
	public void insert(WeightedEdge<T> newEdge) {
		
		if(!existsEdge(newEdge.origin, newEdge.destination)){
			
			//check if nodes doesnt exist
			if( !nodes.containsKey(newEdge.origin) ){
				List<Node<T>> nodeList = new ArrayList<Node<T>>();
				nodeList.add(newEdge.origin);
				nodeList.add(newEdge.destination);
				nodes.put(newEdge.origin, nodeList);
			}
			else{
				nodes.get(newEdge.origin).add(newEdge.destination);
			}
			
			//check if nodes doesnt exist
			if( !nodes.containsKey(newEdge.destination) ){
				List<Node<T>> nodeList = new ArrayList<Node<T>>();
				nodeList.add(newEdge.origin);
				nodeList.add(newEdge.destination);
				nodes.put(newEdge.destination, nodeList);
			}
			else{
				nodes.get(newEdge.destination).add(newEdge.origin);
			}
			
			//maintain edges
			if(!edges.containsKey(newEdge.origin)){
				List<Edge<T>> edgeList = new ArrayList<Edge<T>>();
				edgeList.add(newEdge);
				edgeList.add(new WeightedEdge<T>(newEdge.origin, newEdge.origin,0));
				edges.put(newEdge.origin, edgeList);
			}
			else{
				edges.get(newEdge.origin).add(newEdge);
			}
			
			// for an nin-directional graph, also add the edge with swapped origin and destiny
			if(!edges.containsKey(newEdge.destination)){
				List<Edge<T>> edgeList = new ArrayList<Edge<T>>();
				edgeList.add(new WeightedEdge<T>(newEdge.destination, newEdge.origin, newEdge.getWeight()));
				edgeList.add(new WeightedEdge<T>(newEdge.destination, newEdge.destination,0));
				edges.put(newEdge.destination, edgeList);
			}
			else{
				edges.get(newEdge.destination).add(new WeightedEdge<T>(newEdge.destination, newEdge.origin, newEdge.getWeight()));
			}
		}
		
	}

	@Override
	public void delete(WeightedEdge<T> edge) {
		edges.get(edge.origin).remove(edge);
		edges.get(edge.destination).remove(edge);
	}

	@Override
	public List<Node<T>> getNeighbours(Node<T> node) {
		return nodes.get(node);
	}

	@Override
	public boolean existsEdge(Node<T> node1, Node<T> node2) {
		List<Edge<T>> list  = edges.get(node1);
		List<Edge<T>> list2 = edges.get(node2);
		
		if(list!=null){
			for(Edge<T> e : list){
				if(e.destination == node2){
					return true;
				}
			}
		}

		if(list2!=null){
			for(Edge<T> e : list2){
				if(e.destination == node1){
					return true;
				}
			}	
		}

		return false;
	}

	@Override
	public int getWeight(Node<T> node1, Node<T> node2) {
			
		if( existsEdge(node1, node2) ){
			List<Edge<T>> list = edges.get(node1);
			for(Edge<T> e : list){
				//if( e.destination == node2 || e.origin == node2){
				if( e.destination == node2){
					return ((WeightedEdge<?>) e).getWeight();
				}
			}
		}
			
		return 0;
	}

	@Override
	public void delete(Node<T> node) {
		edges.remove(node);
		nodes.remove(node);
		
		//run through all remaining nodes and check if there is any connection to node
		for (Map.Entry<Node<T>, List<Node<T>>> entry : nodes.entrySet()) {
			int indexOf = entry.getValue().indexOf(node);
			if( indexOf >= 0 ){
				//connection found, so remove
				
				//remove from nodes
				entry.getValue().remove(indexOf);
				
				//delete edges to node of current key
				List<Edge<T>> edgesList = edges.get(entry.getKey());
				for(Edge<T> e : edgesList){
					if(node == e.destination){
						edgesList.remove(e);
					}
				}
			}
		}
			
		
	}

	public Collection<Node<T>> getNodes() {
		return nodes.keySet();
	}
	
	@Override
	public String toString() {

		String buffer = "";
		
		for (Map.Entry<Node<T>, List<Node<T>>> entry : nodes.entrySet()) {
			buffer += "Knoten: " + entry.getKey().uid;
			
			if(!entry.getValue().isEmpty()){
				buffer += " -> Nachbarn -> ";
				for(Node<?> n : entry.getValue()){
					buffer += n.uid + ", ";
				}
			}

			buffer += "\n";
		}
		
		
		return buffer;
	}

	public static void main(String[] args) {
		/*WeightedGraph<String> g = new WeightedGraph<String>(
				new WeightedAdjacencyList()
		);*/
		
		WeightedGraphList l = new WeightedGraphList();
		
		l.insert(new WeightedEdge<String>(new Node<String>(), new Node<String>(), 1));
		
		Set<?> k = l.edges.keySet();
		
		System.out.println(l);
		
	}

	@Override
	public Iterator<Node<T>> iterator() {
		return nodes.keySet().iterator();
	}
}
