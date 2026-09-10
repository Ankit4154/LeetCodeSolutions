// 2265. Count Nodes Equal to Average of Subtree
// https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree
// optim
class Solution {
	int globalCount = 0;
    public int averageOfSubtree(TreeNode root) {
        solve(root);
		return globalCount;
    }
    int[] solve(TreeNode node){
		if(node == null)
			return new int[]{0, 0};

		int[] left = solve(node.left);
		int[] right = solve(node.right);

		int sum = node.val + left[0] + right[0];
		int count = 1 + left[1] + right[1];

		int avg = sum / count;

		if(avg == node.val)
			globalCount++;

		return new int[]{sum, count};
    }
}

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
class Solution {
	int globalCount = 0;
    public int averageOfSubtree(TreeNode root) {
        solve(root, 1);
		return globalCount;
    }
    int[] solve(TreeNode node, int count){
		// if last node then avg == node.val (node.val/1 = node.val )
		// so count this node and return existing count + 1(for this node); 
        if(node.right == null && node.left == null){
			globalCount++;
            return new int[]{node.val, count+1};
        }
		// for current node, starting sum will be node.val
		// total would store the count till previous node
		int sum = node.val;
		int total = count;
		// if both left and right children are available
        if(node.right != null && node.left != null){
			// retrieve left sum and left count of nodes
			// for counting of subtree, reset the count to 1
			// as this node will be root node for the subtree
            int[] left = solve(node.left, 1);
			// retrieve right sum and right count of nodes
			int[] right = solve(node.right, 1);
			sum += left[0] + right[0];
			// add the count of nodes, 
			// since the current node gets counted twice in both of the children
			// subtract it once(1) from total count.
			total = left[1] + right[1] - 1;		
        }else if(node.right == null){  // if right child is empty
			// only retrive left sum and left count of nodes
			int[] left = solve(node.left, 1);
			sum += left[0];
			total = left[1];
		}else if(node.left == null){ // if left child is empty
			// only retrive right sum and right count of nodes
			int[] right = solve(node.right, 1);
			sum += right[0];
			total = right[1];
		}
		// calculate average
		int avg = sum / total;
		// identify if it is valid for solution
		if(avg == node.val)
			globalCount++;
		return new int[]{sum, count+total};
    }
}