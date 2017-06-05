package aufgabenblatt09;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DataCollector {
	class Log{
		private String[] spalten;
		private List<List<String>> data;
		public Log(String... spalten){
			data = new LinkedList<List<String>>();
			this.spalten = spalten;
			for(int i = 0; i < spalten.length; i++){
				data.add(i, new LinkedList<String>());
			}
		}
		public void log(String... daten){
			if(!(daten.length == data.size())) return;
			for(int i = 0; i < daten.length; i++){
				data.get(i).add(daten[i]);
			}
		}
		public void print(){
			//Spaltennamen
			for(int i = 0; i < spalten.length; i++){
				System.out.print(spalten[i] + ((i==(spalten.length-1))?"":","));
			}
			System.out.println("");
			
			for(int zeile = 0; zeile < data.get(0).size(); zeile++){
				for(int spalte = 0; spalte < spalten.length; spalte++){
					System.out.print(data.get(spalte).get(zeile) + ((spalte==(spalten.length-1))?"":","));
				}
				System.out.println("");
			}
		}
	}
	private HashMap<String, Log> logs;
	
	public DataCollector(){
		logs = new HashMap<String, Log>();
	}
	
	public void newLog(String logName, String... spalten){
		if(!logs.containsKey(logName)){
			logs.put(logName, new Log(spalten));
		}
	}
	
	public void log(String logName, long... data){
		if(!logs.containsKey(logName)) return;
		
		String[] dataStr = new String[data.length];
		for(int i = 0; i < data.length; i++){
			dataStr[i] = Long.toString(data[i]);
		}
		log(logName, dataStr);
	}	
	
	public void log(String logName, String... data){
		if(!logs.containsKey(logName)) return;

		logs.get(logName).log(data);
	}
	
	public void print(String logName){
		if(!logs.containsKey(logName)) return;
		
		System.out.println(logName);
		logs.get(logName).print();
	}
	
	public static void main(String[] args){
		DataCollector dc = new DataCollector();
		dc.newLog("log1", "Spalte1","spalte2");
		dc.log("log1", 1,2);
		dc.log("log1", 2,4);
		dc.log("log1", 3,5);
		dc.print("log1");
	}
}
