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
	
	public static void main(String[] args){
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
		System.out.println(st.tree);
		//st.insert(5);
		//st.update();
		System.out.println(st.tree);

		System.out.println("Sum: " + st.sum(0, 50) + " (Soll: 289)"); // 289
		System.out.println("Sum: " + st.sum(0, 11) + " (Soll: 38)"); // 38
  		System.out.println("Sum: " + st.sum(15, 30) + " (Soll: 131)"); // 131
		
		System.out.println("Sum: " + st.sum(20, 30) + " (Soll: 99)"); // 99
		System.out.println("Sum: " + st.sum(10, 22) + " (Soll: 95)"); // 95
		System.out.println("Sum: " + st.sum(8, 10) + " (Soll: 27)"); // 27
		System.out.println("Sum: " + st.sum(30, 40) + " (Soll: 105)"); // 105
		System.out.println("Sum: " + st.sum(20, 20) + " (Soll: 20)"); // 20
		System.out.println("Sum: " + st.sum(19, 21) + " (Soll: 20)"); // 20
	}
	
	public List<TreeNode<Integer>> inOrder() {
		return tree.inOrder();
	}
}
