// April 3, 2021
import java.util.*;
public class l002{
    public static void MinPQ(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < 10; i++) pq.add(i);
        while(!pq.isEmpty()){
            int num = pq.remove();
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void MaxPQ(){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->{
            // return a - b; // this - other, default behaviour
            return b - a; // other - this, reverse
        });
        for(int i = 0; i < 10; i++) pq.add(i);
        while(!pq.isEmpty()){
            int num = pq.remove();
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Print k largest elements in an array
    // Eg: int[] arr = {9,84,2,1,0,-4,7,60}, k = 4
    // O/P: 7,9,60,84
    // Time O(nlogk), Space O(k)
    public static void kLargest(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele: arr){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }
        while(!pq.isEmpty()) System.out.println(pq.remove());
    }
    
    // Print k smallest elements in an array
    // Eg: int[] arr = {9,84,2,1,0,-4,7,60}, k = 4
    // O/P: 2,1,0,-4
    // Time O(nlogk), Space O(k)
    public static void kSmallest(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->{
            return b - a;
        });
        for(int ele: arr){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }
        while(!pq.isEmpty()) System.out.println(pq.remove());
    }

    // Leetcode 347
    // return an array of the k most frequently occurring elements in a given array
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> m = new HashMap<>();
        for(int ele: nums) m.put(ele, m.getOrDefault(ele, 0) + 1);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        for(int ele: m.keySet()){
            int freq = m.get(ele);
            int[] arr = new int[]{ele, freq};
            pq.add(arr);
            if(pq.size() > k) pq.remove();
        }
        int[] res = new int[k];
        int i = 0;
        while(!pq.isEmpty()) res[i++] = pq.remove()[0]; // storing val in result
        return res;       
    }

    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> m = new HashMap<>();
        for(int ele: nums) m.put(ele, m.getOrDefault(ele, 0) + 1);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return m.get(a) - m.get(b);
        });
        
        for(int ele: m.keySet()){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }
        
        int[] res = new int[k];
        int i = 0;
        while(!pq.isEmpty()) res[i++] = pq.remove();
        return res;
    }

    // Leetcode 692
    // return a list of most frequently occuring k words in an array 
    // in an decreasing order of frequency, and if freq is same
    // then the lexicographically smaller word is given preference
    public List<String> topKFrequentWords(String[] words, int k) {
        HashMap<String, Integer> m = new HashMap<>();
        for(String word: words) m.put(word, m.getOrDefault(word, 0) + 1);
        
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            int fa = m.get(a), fb = m.get(b);
            if(fa == fb) return b.compareTo(a);
            return fa - fb;
        });
        
        for(String word: m.keySet()){
            pq.add(word);
            if(pq.size() > k) pq.remove();
        }
        
        List<String> res = new ArrayList<String>();
        while(!pq.isEmpty()) res.add(pq.remove());
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){
        // MaxPQ();
        // MinPQ();
        int[] arr = {9,84,2,1,0,-4,7,60};
        kSmallest(arr, 4);
    }
}