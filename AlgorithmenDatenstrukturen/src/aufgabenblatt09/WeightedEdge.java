package aufgabenblatt09;

/**
 * Edge between to nodes in a weighted graph.
 */
public class WeightedEdge<T> extends Edge<T> {
	private int weight;
	
	public void setWeight(int weight){
		if(weight < 0) throw new IllegalArgumentException("weight must be non-negative");
		
		this.weight = weight;
	}
	
	public int getWeight(){
		return weight;
	}
}
