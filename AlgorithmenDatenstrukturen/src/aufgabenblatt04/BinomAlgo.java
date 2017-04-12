/**
 * 
 */
package aufgabenblatt04;

/**
 * @author flori
 *
 */
public class BinomAlgo implements Pascal3eck{

	@Override
	public int[] getZeile(int n) {
		
		int[] zeile = new int[n+1];

		for(int k=0; k <= n; k++ ){
			
			zeile[k] = Fakultaet.berechne( n ) / ( Fakultaet.berechne(k) * Fakultaet.berechne( n - k ) );
		}
		
		return zeile;
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BinomAlgo a = new BinomAlgo();
		
		int[] zeile = a.getZeile(6);

		System.out.println(zeile);
	}



}
