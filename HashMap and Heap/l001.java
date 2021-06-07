// April 1, 2021
import java.util.*;
public class l001{
    public static void basic_01() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Nepal", 233);
        map.put("UK", 45);
        map.put("Germany", 35);
        map.put("USA", 20);
        map.put("Russia", 18);
        map.put("India", 10);
        map.put("USA", 19);

        // System.out.println(map);
        for (String keys : map.keySet()) {
            System.out.println(keys + " -> " + map.get(keys));
        }

        String key = "USA";
        if (map.containsKey(key))
            System.out.println(map.get(key));
        else
            System.out.println("not Found");

    }

    public static void printFrequency(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for(Character ch: map.keySet()){
            System.out.println(ch + "->" + map.get(ch));
        }
    }

    // to find character with highest frequency in given string
    public static Character highestFreqChar(String str){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i); 
            map.put(c, map.getOrDefault(c, 0) + 1); 
        }
        int max = Integer.MIN_VALUE;
        char res = ' ';
        for(char key: map.keySet()){
            if(map.get(key) > max) {
                max = map.get(key);
                res = key;
            }
        }
        return res;
    }

    // to find index positions of a character in a given string
    // I/P: abaabc
    // O/P:
    // a -> [0,2,3]
    // b -> [1,4]
    // c -> [5]
    public static void positionOfAllChar(String str){
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            map.putIfAbsent(ch, new ArrayList<Integer>());
            map.get(ch).add(i);
        }
        for(Character ch: map.keySet()){
            System.out.println(ch + "->" + map.get(ch));
        }
    }

    // Get distinct common elements in two arrays
    // if array1 -> 1 1 2 2 2 3 5
    // and array2 -> 1 1 1 2 2 4 5
    // Output is -> 1 2 5
    public static void gce(int[] arr, int n1, int[] brr, int n2){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n1; i++) map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        for(int i = 0; i < n2; i++){
            if(map.containsKey(brr[i])){
                System.out.println(brr[i]);
                map.remove(brr[i]);
            }
        }   
    }

    // Get common elements or intersection of 2 arrays
    // if array1 -> 1 1 2 2 2 3 5
    // and array2 -> 1 1 1 2 2 4 5
    // Output is -> 1 1 2 2 5
    public static void gceTwo(int[] arr, int n1, int[] brr, int n2){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n1; i++) map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        for(int i = 0; i < n2; i++){
            if(map.containsKey(brr[i])){
                System.out.println(brr[i]);
                int newVal = map.get(brr[i]) - 1;
                if(newVal == 0) map.remove(brr[i]);
                else map.put(brr[i], newVal);
            }
        }   
    }

    // public static int longestConsecutiveSeq(int[] arr){
    //     HashSet<Integer> set = new HashSet<>();
    //     for(int num: arr) set.add(num);
        
    // }

    public static void main (String[] args){

    }
}