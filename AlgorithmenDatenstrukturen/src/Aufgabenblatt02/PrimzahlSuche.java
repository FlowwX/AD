/**
 * 
 */
package Aufgabenblatt02;

/**
 * @author 	Florian Heuer, 
 * 			Karl-Fabian Witte, 
 * 			Alexander Mendel
 *
 */
public class PrimzahlSuche {

	/**
	 * 
	 */
	public static int K = 2;
	
	/**
	 * 
	 */
	public static final int N = (int) Math.pow(10, K);
	
	/**
	 * 
	 */
	public static boolean[] langsam(){

		boolean[] feld = new boolean[N];
		
		//fülle array
		for(int i=0 ; i < N ; i++){
			feld[i] = true;
		}
		
		for ( int i = 2; i < N; i++){
			for ( int j = 2; j < N; j++){
				if ( (i%j == 0) && (j!=i) ){
					feld[i] = false;
				}
			}
		}
		
		return feld;
	}
	
	/**
	 * 
	 */
	public static boolean[] schnell(){
		
		boolean[] feld = new boolean[N];
		
		//fülle array
		for(int i=0 ; i < N ; i++){
			feld[i] = true;
		}
		
		for ( int i = 2; i < Math.sqrt(N); i++){
			for ( int j = 2; j < i; j++){
				if ( i%j == 0 ){
					feld[i] = false;
				}
			}
		}
		
		return feld;
	}
	
	/**
	 * 
	 */
	public static boolean[] sieb(){
		
		boolean[] feld = new boolean[N];
		
		//fülle array
		for(int i=0 ; i < N ; i++){
			feld[i] = true;
		}
			
		for(int i=2 ; i < Math.sqrt(N) ; i++){
	
				if ( feld[i] == true ){
					for (int j = 2 ; i*j < N ; j++){
						feld[i*j] = false;
					}
				}
		}
		
		return feld;
	}
	
	/**
	 * 
	 */
	public static boolean einzeln(int zahl){
		for (int j = 2 ; j < zahl ; j++){
			if(zahl%j==0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean[] feld = PrimzahlSuche.sieb();
		
		System.out.println(feld[4]);
		
		System.out.println(PrimzahlSuche.einzeln(7));
	}

}
