// 707. Design Linked List
// https://leetcode.com/problems/design-linked-list/
class Node {
    int data;
    Node next;
}

class MyLinkedList {

	Node head;
    int size;
	
    public MyLinkedList() {
        
    }
    
    public int get(int index) {
        if(head == null)
            return -1;
        if(index == 0)
            return head.data;
        int count = 0;
        Node tmp = head;
        while(tmp.next != null && count != index){
            count++;
            tmp = tmp.next;
        }
        if(count == index)
            return tmp.data;
        return -1;
    }
    
    public void addAtHead(int val) {
        Node n = new Node();
        n.data = val;
        n.next = head;
        head = n;
        size++;
    }
    
    public void addAtTail(int val) {
        Node n = new Node();
        n.data = val;
        if(head == null){
            head = n;
            size++;
            return;
        }
        Node tmp = head;
        while(tmp.next != null)
            tmp = tmp.next;
        tmp.next = n;
        size++;
    }
    
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        Node newNode = new Node();
        newNode.data = val;
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }
    
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
        }
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */