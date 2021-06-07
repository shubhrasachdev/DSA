// April 11, 2021

import java.util.*;
public class Hashmap {
    private class Node{
        int key = 0;
        int value = 0;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return key + " = " + value;

        }
    }
    private LinkedList<Node>[] buckets;
    private int noOfElements = 0;
    private int maxSizeOfBucket = 0;

    private void initialize(int size){
        buckets = new LinkedList[size];
        for(int i = 0; i < size; i++) buckets[i] = new LinkedList<>();
        this.maxSizeOfBucket = size;
    }

    @Override
    public String toString(){ // for display
        StringBuilder sb = new StringBuilder();
        Integer sizeOfMap = this.noOfElements;
        sb.append("[");
        for(int i = 0; i < this.maxSizeOfBucket; i++){
            LinkedList<Node> group = this.buckets[i];
            int size = group.size();
            while(size-- > 0){
                sb.append(group.getFirst());
                if(sizeOfMap-- > 1) sb.append(", ");
                group.addLast(group.removeFirst());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Hashmap(){
        initialize(10);
    }

    public Integer size(){
        return this.noOfElements;
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }

    public void put(Integer key, Integer value) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if(res) group.getFirst().value = value;
        else {
            group.addLast(new Node(key, value));
            this.noOfElements++;
        }
    }

    // if found return value, else return null
    public Integer get(Integer key){
        boolean res = containsKey(key);        
        LinkedList<Node> group = group(key);
        if(res) return group.getFirst().value;
        return null;      
    }

    public Integer remove(Integer key){
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if(res){
            noOfElements--;
            return group.removeFirst().value;
        }
        return null;
    }

    public boolean containsKey(Integer key){
        LinkedList<Node> group = group(key);
        int size = group.size();
        while(size-- > 0){
            if(group.getFirst().key == key) return true;
            group.addLast(group.removeFirst());
        }
        return false;
    }

    private void allKeysOfGroup(LinkedList<Node> group, ArrayList<Integer> ans){
        int size = group.size();
        while(size-- > 0){
            ans.add(group.getFirst().key);
            group.addLast(group.removeFirst());
        }
    }

    public ArrayList<Integer> keySet(){
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < this.maxSizeOfBucket; i++){
            allKeysOfGroup(this.buckets[i], ans);
        }
        return ans;
    }

    public Integer getOrDefault(Integer key, Integer defaultValue){
        Integer getVal = get(key);
        return getVal == null ? defaultValue : getVal;
    }


    public void putIfAbsent(Integer key, Integer value){
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if(!res){
            group.addLast(new Node(key, value));
            this.noOfElements++;
        }
    }

    private Integer groupNo(Integer key){
        Integer hc = Math.abs(key.hashCode());
        return hc % maxSizeOfBucket;
    }
    
    private LinkedList<Node> group(int key){
        int groupNo = groupNo(key);
        return this.buckets[groupNo];
    }
}
