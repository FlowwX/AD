/**
 * 
 */
package Aufgabenblatt01.Test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import Aufgabenblatt01.Liste;
import Aufgabenblatt01.Schluessel;
import Aufgabenblatt01.A.ListeA;
import Aufgabenblatt01.B.ListeB;
import Aufgabenblatt01.C.ListeC;

/**
 * @author Florian Heuer
 *
 */
public class ListenTest {
	
	Liste<Integer> l = 
	//		new ListeA<Integer>();
	//      new ListeB<Integer>();
	      new ListeC<Integer>();
	
	// ------------------------------------------------- INSERT
	@Test
	public void testInsert() {
		Assert.assertEquals(0, l.getSize());
		l.insert(1, 3);
		l.insert(2, 4);
		l.insert(3, 6);
		l.insert(4, 7);
		// Normalfall
		Assert.assertEquals(4, l.getSize());
		
		Assert.assertEquals((Integer)3, l.retrieve(1));
		
		l.insert(1,10);
		Assert.assertEquals(5, l.getSize());
		Assert.assertEquals((Integer)10, l.retrieve(1));
		Assert.assertEquals((Integer)3, l.retrieve(2));
		
		l.insert(3,20);
		Assert.assertEquals(6, l.getSize());
		Assert.assertEquals((Integer)20, l.retrieve(3));
		Assert.assertEquals((Integer)3, l.retrieve(2));
		Assert.assertEquals((Integer)4, l.retrieve(4));
	}
	
	// Sonderfaelle
	@Test(expected=IndexOutOfBoundsException.class) 
	public void testInsertIOOBEhigh() {
		Assert.assertEquals(0, l.getSize());
		try {
			l.insert(2, 3);
		} catch (IndexOutOfBoundsException e){
			throw e;
		}
	}
	
	@Test(expected=IndexOutOfBoundsException.class) 
	public void testInsertIOOBElow() {
		Assert.assertEquals(0, l.getSize());
		try {
			l.insert(0, 3);
		} catch (IndexOutOfBoundsException e){
			throw e;
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInsertIAE() {
		Assert.assertEquals(0, l.getSize());
		try {
			l.insert(1, null);
		} catch (IllegalArgumentException e){
			throw e;
		}
	}
	
	// ---------------------------------------------- DELETE POS
	@Test
	public void testDeletePos() {
		Assert.assertEquals(0, l.getSize());
		l.insert(1, 3);
		l.insert(2, 4);
		l.insert(3, 6);
		l.insert(4, 7);
		// Normalfall
		Assert.assertEquals(4, l.getSize());
		l.delete(2);
		Assert.assertEquals(3, l.getSize());
		Assert.assertEquals((Integer)3, l.retrieve(1));
		Assert.assertEquals((Integer)6, l.retrieve(2));
		Assert.assertEquals((Integer)7, l.retrieve(3));
		
		// Normalfall
		
				// Sonderfaelle
	}
	
	
	// ------------------------------------------------- DELETE KEY
	@Test
	public void testDeleteSchluessel() {
		// Normalfall
		
				// Sonderfaelle
	}
	
	
	// -------------------------------------------- FIND
	@Test
	public void testFind() {
		// Normalfall
		// wir wissen schluessel fangen bei 1000 an
		// da dummys einen nicht vom statischen zaehler
		// berechneten Schlüssel haben
		Schluessel.reset();
		l.insert(1, 3);
		l.insert(2, 4);
		l.insert(3, 6);
		l.insert(4, 7);
		Schluessel key1=new Schluessel(1001);
		Schluessel key2=new Schluessel(1002);
		Assert.assertEquals(1, l.find(key1));
		Assert.assertEquals(2, l.find(key2));
		Assert.assertEquals((Integer)3, l.retrieve(l.find(key1)));
		
		
		
		
		
				// Sonderfaelle
	}
	
	@Test
	public void testRetrieve() {
		// Normalfall
		
				// Sonderfaelle
	}
	
	@Test
	public void testConcat() {
		// Normalfall
		
				// Sonderfaelle
	}

}
