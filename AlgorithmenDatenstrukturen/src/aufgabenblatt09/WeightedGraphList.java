package aufgabenblatt09;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeightedGraphList<T> implements IWeightedGraph<T> {
	public long countNodeGet = 0;
	public long countEdgeGet = 0;
	public long countNodeListGet = 0;
	public long countEdgeListGet = 0;

	//Map<Node<T>,List<Node<T>>> nodes = new HashMap<Node<T>,List<Node<T>>>();
	Map<Node<T>,List<Edge<T>>> edges = new HashMap<Node<T>,List<Edge<T>>>();

	public boolean directional = false;
	
	@Override
	public void insert(WeightedEdge<T> newEdge) {
		
		if(!existsEdge(newEdge.origin, newEdge.destination)){
			
//			//check if nodes doesnt exist
//			if( !nodes.containsKey(newEdge.origin) ){
//				List<Node<T>> nodeList = new ArrayList<Node<T>>();
//				nodeList.add(newEdge.destination);
//				nodes.put(newEdge.origin, nodeList);
//			}
//			else{
//				nodes.get(newEdge.origin).add(newEdge.destination);
//			}
//			
//			//check if nodes doesnt exist
//			if(directional == false){
//				if( !nodes.containsKey(newEdge.destination) ){
//					List<Node<T>> nodeList = new ArrayList<Node<T>>();
//					nodeList.add(newEdge.origin);
//					nodes.put(newEdge.destination, nodeList);
//				}
//				else{
//					nodes.get(newEdge.destination).add(newEdge.origin);
//				}
//			}
			
			//maintain edges
			if(!edges.containsKey(newEdge.origin)){
				List<Edge<T>> edgeList = new ArrayList<Edge<T>>();
				edgeList.add(newEdge);
				edges.put(newEdge.origin, edgeList);
			}
			else{
				edges.get(newEdge.origin).add(newEdge);
			}

			// for an non-directional graph, also add the edge with swapped origin and destination
			if(directional == false){
				if(!edges.containsKey(newEdge.destination)){
					List<Edge<T>> edgeList = new ArrayList<Edge<T>>();
					edgeList.add(new WeightedEdge<T>(newEdge.destination, newEdge.origin, newEdge.getWeight()));
					edges.put(newEdge.destination, edgeList);
				}
				else{
					edges.get(newEdge.destination).add(new WeightedEdge<T>(newEdge.destination, newEdge.origin, newEdge.getWeight()));
				}
			}
		}
		
	}

	@Override
	public void delete(WeightedEdge<T> edge) {
		edges.get(edge.origin).remove(edge);
		edges.get(edge.destination).remove(edge);
	}

	@Override
	public List<Node<T>> getNeighbours(Node<T> node) { /****************/countNodeGet++;
		//return nodes.get(node);
		ArrayList<Node<T>> neighbours = new ArrayList<Node<T>>(edges.get(node).size());
		for(Edge<T> e : edges.get(node))
			neighbours.add(e.destination);
		
		return neighbours;
	}

	@Override
	public boolean existsEdge(Node<T> node1, Node<T> node2) {
		if(node1.equals(node2)) return true;

		List<Edge<T>> list  = edges.get(node1); /****************/countEdgeGet++;
		
		if(list!=null){
			for(Edge<T> e : list){ /****************/countEdgeListGet++;
				if(e.destination == node2){
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public int getWeight(Node<T> node1, Node<T> node2) {
		if(node1.equals(node2)) return 0;

		List<Edge<T>> list = edges.get(node1); /****************/countEdgeGet++;
		for(Edge<T> e : list){ /****************/countEdgeListGet++;
			//if( e.destination == node2 || e.origin == node2){
			if( e.destination == node2){
				return ((WeightedEdge<?>) e).getWeight();
			}
		}
			
		return -1;
	}

	@Override
	public void delete(Node<T> node) {
		edges.remove(node);
		/*nodes.remove(node);
		
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
		}*/
			
		
	}

	public Collection<Node<T>> getNodes() {
		//return nodes.keySet();
		ArrayList<Node<T>> nodes = new ArrayList<Node<T>>(edges.keySet().size());
		for(Node<T> e : edges.keySet())
			nodes.add(e);
		
		return nodes;
	}
	
	@Override
	public String toString() {

		String buffer = "";
		/*
		for (Map.Entry<Node<T>, List<Node<T>>> entry : nodes.entrySet()) {
			buffer += "Knoten: " + entry.getKey().uid;
			
			if(!entry.getValue().isEmpty()){
				buffer += " -> Nachbarn -> ";
				for(Node<T> n : entry.getValue()){
					buffer += n.uid + "(" + getWeight(entry.getKey(), n) + ")" + ", ";
				}
			}

			buffer += "\n";
		}
		
		*/
		return buffer;
	}

	@Override
	public Iterator<Node<T>> iterator() {
		//return nodes.keySet().iterator();
		return null;
	}

	@Override
	public void toFile(String fPath){
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fPath))){
			//dos.writeInt(nodes.size());
			dos.writeInt(edges.keySet().size());
			//for(Node<T> node : nodes.keySet()){
			for(Node<T> node : edges.keySet()){
				dos.writeInt(node.uid);
				for(Node<T> neighbour : getNeighbours(node)){
					dos.writeInt(neighbour.uid);
					dos.writeInt(getWeight(node, neighbour));
				}
				dos.writeInt(-1);
			}
			dos.writeInt(-1);
		} catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}

	@Override
	public void fromFile(String fPath){
		// the file describes a directional graph
		boolean oldDirVal = directional;
		directional = true;
		try (DataInputStream dis = new DataInputStream(new FileInputStream(fPath))){
			//nodes.clear();
			edges.clear();
			dis.readInt(); // number of nodes, not needed here

			int read = dis.readInt();
			while(read != -1){
				// nodes loop
				int nodeUid = read;
				read = dis.readInt();
				while(read != -1){
					// neighbour loop
					int neighbourUid = read;
					insert(new WeightedEdge<T>(new Node<T>(null, nodeUid), new Node<T>(null, neighbourUid), dis.readInt()));
					read = dis.readInt();
				}
				read = dis.readInt();
			}
		} catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		directional = oldDirVal;
	}
}
