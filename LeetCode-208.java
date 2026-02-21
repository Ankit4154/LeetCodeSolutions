// 208. Implement Trie (Prefix Tree)
// https://leetcode.com/problems/implement-trie-prefix-tree/
class Node{
    Node[] links = new Node[26];
    boolean flag = false;
    public Node(){}
    public boolean containsKey(char c){
        return links[c - 'a'] != null;
    }
    public Node get(char c){
        return links[c - 'a'];
    }
    public void put(char c, Node n){
        links[c - 'a'] = n;
    }
	public void setEnd(){
		this.flag = true;
	}
	public boolean getEnd(){
		return this.flag;
	}
}

class Trie {

	Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node node = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				node.put(c, new Node());
			}
			node = node.get(c);
		}
		node.setEnd();
    }
    
    public boolean search(String word) {
        Node node = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				return false;
			}
			node = node.get(c);
		}
		return node.getEnd();
    }
    
    public boolean startsWith(String prefix) {
        Node node = root;
		for(int i=0;i<prefix.length();i++){
			char c = prefix.charAt(i);
			if(!node.containsKey(c)){
				return false;
			}
			node = node.get(c);
		}
		return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
 
 
// custom trie implementation
import java.util.*;
class Main {
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple");
        t.insert("apps");
        t.insert("apxl");
        t.insert("bac");
        t.insert("bac");
        System.out.println(t.search("apple"));
        System.out.println(t.search("apd"));
        System.out.println(t.startsWith("ba"));
        System.out.println(t.startsWith("tc"));
        System.out.println();
        System.out.println(t.countStartsWith("ap"));
        System.out.println(t.countEndsWith("bac"));
        
        
    }
}
class Node{
	Node links[] = new Node[26];
	boolean flag = false;
	int endsWith = 0;
	int countPrefix = 0;
	
	public Node(){
		
	}
	public boolean containsKey(char c){
		return links[c - 'a'] != null;
	}
	
	public void put(char c, Node node){
		links[c - 'a'] = node;
	}
	
	public Node get(char c){
		return links[c - 'a'];
	}
	
	public void setEnd(){
		this.flag = true;
	}
	
	public boolean isEnd(){
		return this.flag;
	}
	public void incrementPrefix(){
		countPrefix++;
	}
	public void incrementEnd(){
		endsWith++;
	}
	public void decrementPrefix(){
		countPrefix--;
	}
	public void decrementEnd(){
		endsWith--;
	}
	
}

class Trie{

	Node root;
	
	public Trie(){
		root = new Node();
	}

	void insert(String word){
		Node node = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				node.put(c, new Node());
			}
			node = node.get(c);
			node.incrementPrefix();
		}
		node.setEnd();
		node.incrementEnd();
	}
	
	int countStartsWith(String prefix){
		Node node = root;
		for(int i=0;i<prefix.length();i++){
			char c = prefix.charAt(i);
			if(!node.containsKey(c)){
				return 0;
			}
			node = node.get(c);
		}
		return node.countPrefix;
	}
	
	int countEndsWith(String suffix){
		Node node = root;
		for(int i=0;i<suffix.length();i++){
			char c = suffix.charAt(i);
			if(!node.containsKey(c)){
				return 0;
			}
			node = node.get(c);
		}
		return node.endsWith;
	}
	
	void remove(String word){
		Node node = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				return;
			}
			node = node.get(c);
			node.decrementPrefix();
		}
		node.decrementEnd();
	}
	
	boolean search(String word){
		Node node = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if(!node.containsKey(c)){
				return false;
			}
			node = node.get(c);
		}
		return node.isEnd();
	}
	
	boolean startsWith(String prefix){
		Node node = root;
		for(int i=0;i<prefix.length();i++){
			char c = prefix.charAt(i);
			if(!node.containsKey(c)){
				return false;
			}
			node = node.get(c);
		}
		return true;
	}
}