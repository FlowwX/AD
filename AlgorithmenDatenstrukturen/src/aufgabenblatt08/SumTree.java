package aufgabenblatt08;

import java.util.List;

public class SumTree {
	private Tree<TreeNode<Integer>> tree;
	
	public SumTree(){
		tree = new ArrayTree<TreeNode<Integer>>();
	}
	
	public boolean insert(int val){
		return tree.insert(new TreeNode<Integer>(val));
	}
	
	public void update(){
		TreeNode<Integer> root = tree.getRoot();
		sumBiggerRec(root,0);
	}
	private int sumBiggerRec(TreeNode<Integer> n, int sumParent){
		if(n == null) return 0;
		TreeNode<Integer> rightChild = tree.getRightChild(n);
		n.sum = sumParent;
		int sumRight = 0;
		if(rightChild != null)
			sumRight = sumBiggerRec(rightChild, sumParent);
		n.sum += sumRight;
		return n.getValue() + sumRight + sumBiggerRec(tree.getLeftChild(n), n.sum + n.getValue());
	}	
	
	public int sum(int lower, int upper){
		if(lower > upper) return 0;
		
		List<TreeNode<Integer>> list = tree.inOrder();
		int clower = Integer.MAX_VALUE;
		int cupper = Integer.MIN_VALUE;
		int indLower = -1;
		int indUpper = -1;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getValue() < clower && list.get(i).getValue() >= lower){
				indLower = i;				
				clower = list.get(i).getValue();
			}
			if(list.get(i).getValue() > cupper && list.get(i).getValue() <= upper){
				indUpper = i;
				cupper = list.get(i).getValue();
			}
		}
		if(indLower == -1 || indUpper == -1)
			return 0;
		
		return list.get(indLower).getValue() + list.get(indLower).sum - list.get(indUpper).sum;
	}
	
	public List<TreeNode<Integer>> inOrder() {
		return tree.inOrder();
	}
	
	@Override
	public String toString(){
		return tree.toString();
	}
}
