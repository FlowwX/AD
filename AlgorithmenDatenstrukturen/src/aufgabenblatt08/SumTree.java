package aufgabenblatt08;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SumTree {
	private Tree<TreeNode<Integer>> tree;

	public SumTree() {
		//tree = new ArrayTree<TreeNode<Integer>>();
		tree = new NodeTree<TreeNode<Integer>>(null, null);
	}

	public boolean insert(int val) {
		return tree.insert(new TreeNode<Integer>(val));
	}

	public void update() {
		TreeNode<Integer> root = tree.getRoot();
		sumBiggerRec(root, 0);
	}

	private int sumBiggerRec(TreeNode<Integer> n, int inheritedSum) {
		if (n == null)
			return 0;
		TreeNode<Integer> rightChild = tree.getRightChild(n);
		n.sum = inheritedSum;
		int sumRight = 0;
		if (rightChild != null)
			sumRight = sumBiggerRec(rightChild, inheritedSum);
		n.sum += sumRight;
		return n.getValue() + sumRight + sumBiggerRec(tree.getLeftChild(n), n.sum + n.getValue());
	}

	public int sum(int lower, int upper) {
		if (lower > upper)
			return 0;

		List<TreeNode<Integer>> list = tree.inOrder();
		int clower = Integer.MAX_VALUE;
		int cupper = Integer.MIN_VALUE;
		int indLower = -1;
		int indUpper = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getValue() < clower && list.get(i).getValue() >= lower) {
				indLower = i;
				clower = list.get(i).getValue();
			}
			if (list.get(i).getValue() > cupper && list.get(i).getValue() <= upper) {
				indUpper = i;
				cupper = list.get(i).getValue();
			}
		}
		if (indLower == -1 || indUpper == -1)
			return 0;

		return list.get(indLower).getValue() + list.get(indLower).sum - list.get(indUpper).sum;
	}

	public List<TreeNode<Integer>> inOrder() {
		return tree.inOrder();
	}

	@Override
	public String toString() {
		return tree.toString();
	}

	public static void main(String[] args) {
		SumTree st = new SumTree();
		Random rnd = new Random();
		int rounds = 1000;
		for (int len : Arrays.asList(10, 100, 1000, 10000, 100000, 1000000)) {
			for (int i = 0; i < len; i++) {
				st.insert(rnd.nextInt(len*2));
			}
			long start = System.currentTimeMillis();
			for (int i = 0; i < rounds; i++) {
				st.update();
			}
			long end = System.currentTimeMillis();
			System.out.println(Integer.toString(len) + ": " + ((double) end - (double)start) / rounds);
		}
	}
}
