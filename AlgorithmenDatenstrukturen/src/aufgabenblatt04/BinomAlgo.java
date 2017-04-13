/**
 * 
 */
package aufgabenblatt04;

/**
 * @author flori
 *
 */
public class BinomAlgo implements Pascal3eck{

	public long zaehler = 0;
	
	private long time_start = 0;
	private long time_end   = 0;
	
	@Override
	public int[] getZeile(int n) {
		
		int[] zeile = new int[n+1];
		
		time_start = System.currentTimeMillis();

		for(int k=0; k <= n; k++ ){
			
			zaehler++;
			
			zeile[k] = (int) (fakultaet( n ) / ( fakultaet( k ) * fakultaet( n - k ) ));
						
		}
		
		time_end = System.currentTimeMillis();
		
		return zeile;
	}
	
	private double fakultaet( int n ) {
		double fakul = 1;
        for(int i = 2; i <= n; i++){
        	zaehler++;
        	fakul *= i;               
        }
        return fakul;
	}
	
	
	public long getLaufzeit(){
		return time_end - time_start;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int n = 10;
		
		BinomAlgo a = new BinomAlgo();
		
		int[] zeile = a.getZeile(n);

		System.out.println("\nZeile: " + n + "\nAufwand: " + a.zaehler);
		System.out.println("Laufzeit: " + a.getLaufzeit());
	} 



}
