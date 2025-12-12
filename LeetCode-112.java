// 112. Path Sum
// https://leetcode.com/problems/path-sum/
/**
* Definition for a binary tree node.
* public class TreeNode {
* int val;
* TreeNode left;
* TreeNode right;
* TreeNode() {}
* TreeNode(int val) { this.val = val; }
* TreeNode(int val, TreeNode left, TreeNode right) {
* this.val = val;
* this.left = left;
* this.right = right;
* }
* }
*/
class Solution {
public boolean hasPathSum(TreeNode root, int targetSum) {
 if(root == null)
    return false;
 Queue<Pair> q = new ArrayDeque<>();
  Pair p = new Pair(root, root.val);
  q.add(p);
  while(!q.isEmpty()){
       int size = q.size();
       for(int i=0;i<size;i++){
             Pair p1 = q.poll();
             if(p1.sum == targetSum && 
             p1.node.left == null && p1.node.right == null)
                    return true;
             if(p1.node.left!=null){
                   int v = p1.node.left.val;
                   Pair p2 = new Pair(p1.node.left, p1.sum+v);
                   q.add(p2);
             }
             if(p1.node.right!=null){
                  int v = p1.node.right.val;
                  Pair p2 = new Pair(p1.node.right, p1.sum+v);
                  q.add(p2);
              }
       }
  }        
  return false;
}
private class Pair{
TreeNode node;
int sum;
Pair(TreeNode n, int sum){
this.node = n;
this.sum = sum;
}
}
}
