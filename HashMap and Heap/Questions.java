import java.util.*;

public class Questions{

    // LeetCode 242: https://leetcode.com/problems/valid-anagram/
    // check if the given 2 strings are anagrams
    public static boolean isAnagram(String s, String t){
        if(s.length() != t.length()) return false;
        int[] freq = new int[26];
        for(int i = 0; i < s.length(); i++){
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++){
            if(freq[i] != 0) return false;
        }
        return true;
    }

    // LeetCode 49: https://leetcode.com/problems/group-anagrams/
    // Given an array of strings strs, group the anagrams together in any order.
    public static String RLES(String str){ // run length encoded string
        // if string is aabbcee -> RLE will be a2b2c1d0e2
        int[] freq =  new int[26];
        for(int i = 0; i < str.length(); i++) freq[str.charAt(i) - 'a']++;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++){
            sb.append((char)(i + 'a'));
            sb.append(freq[i]);
        }
        return sb.toString();
    }

    public List<List<String>> groupAnagram(String[] strs){
        HashMap<String, ArrayList<String>> m = new HashMap<>();
        for(String str: strs){
            String rles = RLES(str);
            m.putIfAbsent(rles, new ArrayList<>());
            m.get(rles).add(str);
        }
        List<List<String>> ans = new ArrayList<>();
        for(String key: m.keySet()) ans.add(m.get(key));
        return ans;
    }

    // Leetcode 215: https://leetcode.com/problems/kth-largest-element-in-an-array
    // return kth largest element in an array
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void downHeapify(int[] arr, int pi, int last){
        int maxIdx = pi;
        int li = 2*pi + 1, ri = 2*pi + 2;
        if(li <= last && arr[maxIdx] < arr[li]) maxIdx = li;
        if(ri <= last && arr[maxIdx] < arr[ri]) maxIdx = ri;
        if(maxIdx != pi){
            swap(arr, pi, maxIdx);
            downHeapify(arr, maxIdx, last);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        for(int i = n - 1; i >= 0; i--) downHeapify(nums, i, n - 1);
        int last = n - 1;
        while(k-- > 1){
            swap(nums, 0, last--);
            downHeapify(nums, 0, last);
        }
        return nums[0];
    }

    //Leetcode 378: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
    public int kthSmallest(int[][] matrix, int k){
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->{
            return matrix[a / m][a % m] - matrix[b / m][b % m];
        });
        for(int i = 0; i < n; i++) pq.add(i * m + 0);
        int r = 0, c = 0;
        while(--k > 1){
            int idx = pq.remove();
            r = idx / m;
            c = idx % m + 1;
            if(c < m) pq.add(r * m + c);
        }
        
    }

}