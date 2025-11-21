// 146. LRU Cache
// https://leetcode.com/problems/lru-cache

// Maintain a doubly linked list to insert and remove the nodes
// efficiently from head/left(LRU) and tail/right(MRU)
class ListNode{
	int key = -1;
	int val = -1;
	ListNode prev;
	ListNode next;
	ListNode(int key, int val){
		this.key = key;
		this.val = val;
	}
	ListNode(int val){
		this.val = val;
	}
}

class LRUCache {
	
	// -1 <-> -1
	// lru    mru
	// initially most recently used element will be at mru.left or mru.prev
	// least recently used element will be at lru.right or lru.next
	// for a new element to be added, example : put(1,1)
	// since it(1,1) now becomes recently used element 
	// we will insert it at mru position i.e. at mru.prev
	// -1 <-> 1 <-> -1
	// lru    		mru
	// adding another new element (2,2), will make it most recently used
	// since we are adding 2 to the right of the existing element 1
	// the lru value remains unchanged, no need to update
	// -1 <-> 1 <-> 2 <-> -1
	// lru    			  mru
	// For get operation : if we get(1), then 1 should become the most recently used.
	// to achieve this, we can perform 2 operations
	// 1st : remove 1 from the doubly linked list
	// This operation makes our lru now to 2
	// -1 <-> 2 <-> -1
	// lru    		mru
	// 2nd : re-insert the 1 as a new element in the list
	// -1 <-> 2 <-> 1 <-> -1
	// lru    			  mru
	// This operation makes our mru now to 1
	// For put operation : 1) if we insert a new element (3,3) within capacity(3)
	// a) and the element doesn't exists, we simply insert it at the mru
	// -1 <-> 2 <-> 1 <-> 3 <-> -1
	// lru    			  		mru
	// b) if the element already exists (1,1), delete it from the list
	// -1 <-> 2 <-> 3 <-> -1   // 1 deleted
	// lru    			  mru
	// re-insert as a new element at mru and also override in map
	// -1 <-> 2 <-> 3 <-> 1 <-> -1   // 1 inserted
	// lru    			  		mru
	// 2) if we insert a new element (4,4) outside the capacity(3)
	// remove element at lru from the list and the map and re-insert new element
	// -1 <-> 3 <-> 1 <-> 4 <-> -1   // 2 removed and 4 inserted
	// lru    			  		mru
	
	
	Map<Integer, ListNode> map;
	ListNode lru;
	ListNode mru;
	int capacity;
	
    public LRUCache(int capacity) {
        this.capacity = capacity;
		map = new HashMap<Integer, ListNode>(capacity);
		lru = new ListNode(-1, -1);
		mru = new ListNode(-1, -1);
		lru.next = mru;
		mru.prev = lru;
		// -1 <-> -1
		// lru    mru
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
			return -1;
		ListNode node = map.get(key);
		remove(node);
		insert(node);
		return node.val;
    }
	
	public void remove(ListNode node){
		ListNode nxt = node.next;
		ListNode prv = node.prev;
		prv.next = nxt;
		nxt.prev = prv;
	}
	
	public void insert(ListNode node){
		ListNode prv = mru.prev;
		prv.next = node;
		node.prev = prv;
		node.next = mru;
		mru.prev = node;
	}
    
    public void put(int key, int value) {
		if(map.containsKey(key)){
			remove(map.get(key));
		}
		ListNode node = new ListNode(key, value);
		insert(node);
		map.put(key, node);
		if(map.size() > capacity){
            ListNode lruTemp = lru.next;
			remove(lruTemp);
			map.remove(lruTemp.key);
		}
    }
}
