// 3043. Find the Length of the Longest Common Prefix
// https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix
// optim using Trie, Time : O(D), Space : O(D) , D:Number of digits
class Solution {
	class TrieNode{
		TrieNode[] child = new TrieNode[10];
	}
	
	TrieNode root = new TrieNode();
	
	private void insert(int num){
		TrieNode node = root;
		String s = String.valueOf(num);
		for(char c : s.toCharArray()){
			int digit = c - '0';
			if(node.child[digit] == null){
				node.child[digit] = new TrieNode();
			}
			
			node = node.child[digit];
		}
	}
	
	private int search(int num){
		TrieNode node = root;
		int len = 0;
		String s = String.valueOf(num);
		for(char c : s.toCharArray()){
			int digit = c - '0';
			if(node.child[digit] == null)
				break;
			
			len++;
			node = node.child[digit];
		}
		return len;
	}
	
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
		// insert all elements to trie from arr1
		for(int n : arr1){
			insert(n);
		}
		int count = 0;
		// find longest prefix match for arr2
		for(int n : arr2){
			count = Math.max(count, search(n));
		}
		return count;
	}
	
}

// optim, Time : O(D), Space : O(D) , D:Number of digits
class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
		// have 2 separate hashsets and compare the common elements
		Set<Integer> set = new HashSet<>();
		for(int num : arr1){
			while(num > 0) {
                set.add(num);
                num /= 10;
            }
		}
        int count = 0;
		for(int num : arr2){
			while(num > 0) {
                if(set.contains(num)){
                    count = Math.max(count, digits(num));
                }
                num /= 10;
            }
		}
		return count;		
    }
    int digits(int num){
        return (int)Math.log10(num)+1; //get digits of a number
    }
}
//init
class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
		// have 2 separate hashsets and compare the common elements
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		for(int i : arr1){
			set1.add(i);
			String s = i+"";
			if(s.length()>1){
                String num = "";
				for(int j=0;j<s.length();j++){
                    num = num+s.charAt(j);
					int n = Integer.parseInt(num);
					set1.add(n);
				}
			}
		}
		for(int k : arr2){
			set2.add(k);
			String s = k+"";
			if(s.length()>1){
                String num = "";
				for(int j=0;j<s.length();j++){
					num = num+s.charAt(j);
					int n = Integer.parseInt(num);
					set2.add(n);
				}
			}
		}
        int count = 0;
		for(int i : set1){
			if(set2.contains(i)){
				count = Math.max(count, String.valueOf(i).length());
			}
		}
		return count;		
    }
}