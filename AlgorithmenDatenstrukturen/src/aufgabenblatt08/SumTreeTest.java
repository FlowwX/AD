package aufgabenblatt08;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

public class SumTreeTest {
	int maxVal = 10000;
	int rounds = 1000;
	int treeSize = 1000;
	
	private SumTree getRandTree(int size){
		SumTree st = new SumTree();
		Random rnd = new Random();
		for(int i = 0; i < size; i++){
			st.insert(rnd.nextInt(maxVal));
		}
		st.update();
		return st;
	}
	
	@Test
	public void testSum(){
		SumTree st = new SumTree();
		st.insert(20);
		st.insert(10);
		st.insert(9);
		st.insert(8);
		st.insert(15);
		st.insert(11);
		st.insert(17);
		st.insert(30);
		st.insert(22);
		st.insert(27);
		st.insert(40);
		st.insert(35);
		st.insert(45);
		
		st.update();
		assertEquals(289, st.sum(0, 50));
		assertEquals(38, st.sum(0, 11));
		assertEquals(131, st.sum(15, 30));
		assertEquals(99, st.sum(20, 30));
		assertEquals(95, st.sum(10, 22));
		assertEquals(27, st.sum(8, 10));
		assertEquals(105, st.sum(30, 40));
		assertEquals(20, st.sum(20, 20));
		assertEquals(20, st.sum(19, 21));
	}
	
	@Test
	public void testSumRandom(){
		SumTree st = getRandTree(treeSize);
		Random rnd = new Random();
		for(int i = 0; i < rounds; i++){
			int upper = rnd.nextInt(maxVal);
			int lower = rnd.nextInt(maxVal);
			int treeSum = st.sum(lower, upper);
			List<TreeNode<Integer>> l = st.inOrder();
			int listSum = 0;
			for(TreeNode<Integer> tn : l){
				if(tn.getValue() <= upper && tn.getValue() >= lower)
					listSum += tn.getValue();
			}
			assertEquals(listSum, treeSum);
		}
	}
}
