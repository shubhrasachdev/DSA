import java.util.*;
public class Heap{
    private ArrayList<Integer> arr;
    private int size;
    private boolean isMax; // max priority queue: should be true. for min pq should be false
    private void initialize(){
        this.arr = new ArrayList<>();
        this.size = 0;
        this.isMax = true;
    }
    public Heap(){
        initialize();
    }
    
    public Heap(int[] arr, boolean isMax){
        initialize();
        this.isMax = isMax;
        for(int ele: arr) this.arr.add(ele);
        for(int i = this.arr.size() - 1; i >= 0; i--) downHeapify(i);
        this.size = arr.length;
    }

    public int size(){return this.size;}
    
    public int peek(){
        return this.arr.get(0);
    }

    private void swap(int i, int j){
        int ei = this.arr.get(i);
        int ej = this.arr.get(j);
        this.arr.set(i, ej);
        this.arr.set(j, ei);
    }

    public void add(int data){ //O(log n)
        this.arr.add(data);
        this.size++;
        upHeapify(this.size - 1);

    }

    public int remove(){ //O(log n)
        int n = this.arr.size();
        int rv = this.arr.get(0);
        swap(0, n - 1);
        this.arr.remove(n - 1);
        this.size--;
        downHeapify(0);
        return rv;
    }

    public void downHeapify(int pi){
        int maxIdx = pi;
        int lci = 2*pi + 1;
        int rci = 2*pi + 2;
        // if(lci < arr.size() && arr.get(maxIdx) < arr.get(lci)) maxIdx = lci;
        // if(rci < arr.size() && arr.get(maxIdx) < arr.get(rci)) maxIdx = rci;
        if(lci < arr.size() && compareTo(lci, maxIdx) > 0) maxIdx = lci;
        if(rci < arr.size() && compareTo(rci, maxIdx) > 0) maxIdx = rci;
        if(maxIdx != pi){
            swap(maxIdx, pi);
            downHeapify(maxIdx);
        }
    }

    public void upHeapify(int ci){
        int pi = (ci - 1)/2;
        if(pi >= 0 && arr.get(pi) < arr.get(ci)){
            swap(pi, ci);
            upHeapify(pi);
        }
    }
    
    public void printHeap(){
        for(int i = 0; i < this.size; i++) System.out.print(this.arr.get(i) + " ");
        System.out.println();
    }

    private int compareTo(int a, int b){
        if(isMax) return this.arr.get(a) - this.arr.get(b);
        return this.arr.get(b) - this.arr.get(a);
    }
}