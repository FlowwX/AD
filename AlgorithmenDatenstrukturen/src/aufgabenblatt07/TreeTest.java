package aufgabenblatt07;

import org.junit.Test;
import static org.junit.Assert.*;

public abstract class TreeTest {
	public abstract Tree<Integer> getTree();
	
	@Test
	public void testOrders(){
		Tree<Integer> tree = getTree();
		Integer[] preOrder = {4,2,1,3,9,6,11};
		Integer[] inOrder = {1,2,3,4,6,8,9,11};
		Integer[] postOrder = {1,3,2,6,11,9,4};
		tree.insert(4);
		tree.insert(2);
		tree.insert(9);
		tree.insert(1);
		tree.insert(3);
		tree.insert(6);
		tree.insert(11);
		assertTrue("preOrder failed", preOrder.equals(tree.preOrder()));
		assertTrue("inOrder failed", inOrder.equals(tree.inOrder()));
		assertTrue("postOrder failed", postOrder.equals(tree.postOrder()));
	}
}
