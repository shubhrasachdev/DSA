public class client {

    public static void solve1(int[] arr){  // returns elements in decreasing order
        Heap pq = new Heap();
        for(int ele: arr) pq.add(ele);
        while(pq.size() != 0) System.out.print(pq.remove() + " "); // Takes O(nlogn) time
        System.out.println();
    }

    public static void solve2(int[] arr){ // returns elements in decreasing order
        Heap pq = new Heap(arr, false); // Takes O(n) time
        while(pq.size() != 0) System.out.print(pq.remove() + " "); // Takes O(nlogn) time
        System.out.println();
    }

    public static void main(String[] args){
        int[] arr = {10,20,30,-2,-3,-4,5,6,7,8,9,22,11,13};
        // Heap myHeap = new Heap(arr);
        // myHeap.printHeap();
        // myHeap.add(16);
        // System.out.println("Adding element");
        // myHeap.printHeap();
        // System.out.println("Removing element: " + myHeap.remove());
        // System.out.println("Peek: " + myHeap.peek());
        // myHeap.printHeap();
        solve1(arr);
        solve2(arr);
    }   
}
