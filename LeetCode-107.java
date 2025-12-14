// 107. Binary Tree Level Order Traversal II
// https://leetcode.com/problems/binary-tree-level-order-traversal-ii
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// optimized 
// use same list and insert at first index 0, to reverse the order
// add node val to the inner list only once during traversal
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> out = new ArrayList<>();
		if(root == null)
			return out;
		Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		while(!q.isEmpty()){
			int size = q.size();
			List<Integer> list = new ArrayList<>();
			for(int i=0;i<size;i++){
				TreeNode n = q.poll();
                list.add(n.val);
				if(n.left != null){
					q.add(n.left);
				}
                if(n.right != null){
					q.add(n.right);
				}
			}
			if(!list.isEmpty())
				out.add(0,list);
		}
		return out;
    }
}
 
// non optimized
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> out = new ArrayList<>();
		if(root == null)
			return out;
		Queue<TreeNode> q = new ArrayDeque<>();
		List<Integer> list = new ArrayList<>();
		q.add(root);
		list.add(root.val);
		out.add(list);
		while(!q.isEmpty()){
			int size = q.size();
			list = new ArrayList<>();
			for(int i=0;i<size;i++){
				TreeNode n = q.poll();
				if(n.left != null && n.right != null){
					q.add(n.left);
					q.add(n.right);
					list.add(n.left.val);
					list.add(n.right.val);
				}else if(n.left != null){
					q.add(n.left);
					list.add(n.left.val);
				}else if(n.right != null){
					q.add(n.right);
					list.add(n.right.val);
				}
			}
			if(!list.isEmpty())
				out.add(list);
		}
		List<List<Integer>> out2 = new ArrayList<>();
		for(int i=out.size()-1;i>=0;i--){
			out2.add(out.get(i));
		}
		return out2;
    }
}