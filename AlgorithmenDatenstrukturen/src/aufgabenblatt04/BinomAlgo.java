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
			
			//zeile[k] = (int) (fakultaet( n ) / ( fakultaet( k ) * fakultaet( n - k ) ));
			zeile[k] = binomialkoeffizient2(n, k);
						
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
	
	private int binomialkoeffizient(int n, int k){
		int bin=1;
		

		for(int i = 1; i <= k; i++){
			 bin = bin * (n - i + 1) / i;
			 zaehler++;
		}
               

		
		return bin;
	}
	
	private int binomialkoeffizient2(int n, int k){
		
		zaehler++;
		
		if( k == 0 )return 1;
		
		if( 2*k > n ) return binomialkoeffizient(n, n-k);
		
		return (n+1-k)/k*binomialkoeffizient(n,k-1);
	}
	
	
	public long getLaufzeit(){
		return time_end - time_start;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i = 0; i <= 16; i++) {
			int exp = (int) Math.pow(2, i);
	
			// System.out.print("N =\t2^" +i + "\t");
			BinomAlgo a = new BinomAlgo();
			
			int[] zeile = a.getZeile(exp);
			System.out.println("\nZeile: " + exp + "\nAufwand: " + a.zaehler);
			System.out.println("Laufzeit: " + a.getLaufzeit());
		}
		
	} 



}
