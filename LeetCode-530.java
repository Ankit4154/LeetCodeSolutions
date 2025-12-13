// 530. Minimum Absolute Difference in BST
// https://leetcode.com/problems/minimum-absolute-difference-in-bst
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
    int prev = -1;
    int diff = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return diff;
    }
    public void inOrder(TreeNode root){
        if(root == null)
            return;
        inOrder(root.left);
        if(prev != -1){
                diff = Math.min(diff, Math.abs(root.val-prev));
        }
        prev = root.val;
        inOrder(root.right);
    }
}