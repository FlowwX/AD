package aufgabenblatt09;

import java.util.Random;

public class DijkstraRunner {
	public static void main(String[] args) {
		String fPath = "graph.bin";
		DataCollector dc = new DataCollector();
		dc.newLog("Matrix", "N", "counters");
		dc.newLog("List", "N", "counters");
		
		for(int numNodes = 10; numNodes <= 10000; numNodes *= 10){
			IWeightedGraph<Object> graph = WeightedGraphBuilder.getEmptyNodes(numNodes, AdjacencyImplementation.MATRIX);
			graph.toFile(fPath);
			@SuppressWarnings("unchecked")
			Node<Object> destination = (Node<Object>) graph.getNodes().toArray()[new Random().nextInt(numNodes)];
			graph = null;
			
			WeightedGraphMatrix<Object> graphMatrix = new WeightedGraphMatrix<Object>(0);
			graphMatrix.fromFile(fPath);
			clearCounters(graphMatrix);
			Dijkstra.calculate(graphMatrix, destination);
			System.out.println("Finished Matrix for " + numNodes);
			dc.log("Matrix", numNodes, graphMatrix.countMapKeysGet+graphMatrix.countMatAccess+graphMatrix.countNodeIndMapGet);
			graphMatrix = null;
			
			WeightedGraphList<Object> graphList = new WeightedGraphList<Object>();
			graphList.fromFile(fPath);
			clearCounters(graphList);
			Dijkstra.calculate(graphList, destination);
			System.out.println("Finished List for " + numNodes);
			dc.log("List", numNodes, graphList.countEdgeGet+graphList.countEdgeListGet+graphList.countNodeGet+graphList.countNodeListGet);
			graphList = null;
		}
		
		dc.print("Matrix");
		dc.print("List");
	}
	
	public static void clearCounters(WeightedGraphMatrix<?> graph){
		graph.countMapKeysGet = 0;
		graph.countMatAccess = 0;
		graph.countNodeIndMapGet = 0;
	}
	
	public static void clearCounters(WeightedGraphList<?> graph){
		graph.countEdgeGet = 0;
		graph.countEdgeListGet = 0;
		graph.countNodeGet = 0;
		graph.countNodeListGet = 0;
	}
}
