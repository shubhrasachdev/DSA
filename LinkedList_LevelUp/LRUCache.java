// June 10, 2021

// Leetcode 146
// https://leetcode.com/problems/lru-cache/submissions/
import java.util.HashMap;
public class LRUCache {
    private class Node{
        int key, value;
        Node prev, next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
    private HashMap<Integer, Node> map = new HashMap<>();
    private Node head = null, tail = null;
    private int capacity = 0, size = 0;
    
    public LRUCache(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }
    
    private void removeNode(Node node) {
        if(this.size == 1) this.head = this.tail = null;
        else {
            Node prev = node.prev;
            Node forw = node.next;
            if(prev == null){ // head is to be removed
                this.head = node.next;
                this.head.prev = null;
            } else if(forw == null) { // tail is to be removed
                this.tail = prev;
                this.tail.next = null;
            } else { // some middle node to be removed
                forw.prev = prev;
                prev.next = forw;
            }
            node.next = node.prev = null;
        }
        this.size--;
    }

    private void addLast(Node node) {
        if(this.size == 0) this.head = this.tail = node;
        else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size++;
    }

    private void makeRecent(Node node) {
        if(this.tail == node) return;
        removeNode(node);
        addLast(node);
    }

    public int get(int key){
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        makeRecent(node);
        return node.value;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){ // change value of existing entry
            Node node = map.get(key);
            node.value = value;
            makeRecent(node);
        } else { // new entry
            if(this.size == this.capacity){
                int rKey = this.head.key;
                removeNode(this.head);
                map.remove(rKey);
            }   
            Node node = new Node(key, value);
            addLast(node);
            map.put(key, node);
        }
    }
}
