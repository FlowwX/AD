package aufgabenblatt09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				nodes.put(newEdge.destination, new ArrayList<Node<?>>());
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
		}
		
	}

	@Override
	public void delete(WeightedEdge<?> edge) {
		edges.get(edge.origin).remove(edge);
	}

	@Override
	public List<Node<?>> getNeighbours(Node<?> node) {
		return nodes.get(node);
	}

	@Override
	public boolean existsEdge(Node<?> node1, Node<?> node2) {
		List<Edge<?>> list = edges.get(node1);
		return (list==null)?false:list.contains(node2);
	}

	@Override
	public int getWeight(Node<?> node1, Node<?> node2) {
			List<Edge<?>> edgeList = edges.get(node1);
			int idx = edgeList.indexOf(node2);
			if(idx>=0){
				return ((WeightedEdge<?>)edgeList.get(idx)).getWeight();
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

}
