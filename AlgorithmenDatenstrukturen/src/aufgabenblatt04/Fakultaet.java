/**
 * 
 */
package aufgabenblatt04;

/**
 * @author flori
 *
 */
public class Fakultaet {

	public static int berechne( int n ) {
		int fakul = 1;
        for(int i = 2; i <= n; i++){
        	fakul *= i;               
        }
        return fakul;
	}
}
