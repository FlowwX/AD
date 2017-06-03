package aufgabenblatt09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeightedAdjacencyList implements WeightedAdjacency {

	Map<Node<?>,List<Node<?>>> nodes = new HashMap<Node<?>,List<Node<?>>>();
	Map<Node<?>,List<Edge<?>>> edges = new HashMap<Node<?>,List<Edge<?>>>();

	
	@Override
	public void insert(WeightedEdge<?> newEdge) {
		
		if(!existsEdge(newEdge.origin, newEdge.destination)){
			
			//check if nodes doesnt exist
			if( !nodes.containsKey(newEdge.origin) ){
				List<Node<?>> nodeList = new ArrayList<Node<?>>();
				nodeList.add(newEdge.destination);
				nodes.put(newEdge.origin, nodeList);
				
			}
			else{
				nodes.get(newEdge.origin).add(newEdge.destination);
			}
			
			//check if nodes doesnt exist
			if( !nodes.containsKey(newEdge.destination) ){
				List<Node<?>> nodeList = new ArrayList<Node<?>>();
				nodeList.add(newEdge.origin);
				nodes.put(newEdge.destination, nodeList);	
			}
			else{
				nodes.get(newEdge.destination).add(newEdge.origin);
			}
			
			//maintain edges
			if(!edges.containsKey(newEdge.origin)){
				List<Edge<?>> edgeList = new ArrayList<Edge<?>>();
				edgeList.add(newEdge);
				edges.put(newEdge.origin, edgeList);
			}
			else{
				edges.get(newEdge.origin).add(newEdge);
			}
			
			//maintain edges
			if(!edges.containsKey(newEdge.destination)){
				List<Edge<?>> edgeList = new ArrayList<Edge<?>>();
				edgeList.add(newEdge);
				edges.put(newEdge.destination, edgeList);
			}
			else{
				edges.get(newEdge.destination).add(newEdge);
			}
		}
		
	}

	@Override
	public void delete(WeightedEdge<?> edge) {
		edges.get(edge.origin).remove(edge);
		edges.get(edge.destination).remove(edge);
	}

	@Override
	public List<Node<?>> getNeighbours(Node<?> node) {
		return nodes.get(node);
	}

	@Override
	public boolean existsEdge(Node<?> node1, Node<?> node2) {
		List<Edge<?>> list  = edges.get(node1);
		List<Edge<?>> list2 = edges.get(node2);
		
		if(list!=null){
			for(Edge<?> e : list){
				if(e.destination == node2){
					return true;
				}
			}
		}

		if(list2!=null){
			for(Edge<?> e : list2){
				if(e.destination == node1){
					return true;
				}
			}	
		}

		return false;
	}

	@Override
	public int getWeight(Node<?> node1, Node<?> node2) {
			
		if( existsEdge(node1, node2) ){
			List<Edge<?>> list = edges.get(node1);
			for(Edge<?> e : list){
				if( e.destination == node2 || e.origin == node2){
					return ((WeightedEdge<?>) e).getWeight();
				}
			}
		}
			
		return 0;
	}

	@Override
	public void delete(Node<?> node) {
		edges.remove(node);
		nodes.remove(node);
		
		//run through all remaining nodes and check if there is any connection to node
		for (Map.Entry<Node<?>, List<Node<?>>> entry : nodes.entrySet()) {
			int indexOf = entry.getValue().indexOf(node);
			if( indexOf >= 0 ){
				//connection found, so remove
				
				//remove from nodes
				entry.getValue().remove(indexOf);
				
				//delete edges to node of current key
				List<Edge<?>> edgesList = edges.get(entry.getKey());
				for(Edge<?> e : edgesList){
					if(node == e.destination){
						edgesList.remove(e);
					}
				}
			}
		}
			
		
	}

	@Override
	public Collection<Node<?>> getNodes() {
		return nodes.keySet();
	}
	
	@Override
	public String toString() {

		String buffer = "";
		
		for (Map.Entry<Node<?>, List<Node<?>>> entry : nodes.entrySet()) {
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
		
		WeightedAdjacencyList l = new WeightedAdjacencyList();
		
		l.insert(new WeightedEdge<String>(new Node<String>(), new Node<String>(), 1));
		
		Set<?> k = l.edges.keySet();
		
		System.out.println(l);
		
	}
}
