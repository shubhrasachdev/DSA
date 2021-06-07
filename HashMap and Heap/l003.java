// April 4, 2021
import java.util.*;
public class l003 {
    
    // Sort an array where each element is displaced by at max k positions from its 
    // supposed position in a sorted array
    public static void kSorted(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int idx = 0;
        for(int ele: arr){
            pq.add(ele);
            if(pq.size() > k){
                arr[idx++] = pq.remove();
            }
        }
        while(!pq.isEmpty()) arr[idx++] = pq.remove();
        for(int ele: arr) System.out.println(ele);
    }

    // given a list of lists, where each list is sorted.
    // The function is expected to merge k sorted lists to create one sorted list.
    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->{
            return a[0] - b[0];
        });
        for(int i = 0; i < lists.size(); i++){
            pq.add(new int[]{lists.get(i).get(0), 0, i});
        }
        while(!pq.isEmpty()){
            int[] arr = pq.remove();
            res.add(arr[0]);
            int idx = arr[1];
            int listIdx = arr[2];
            int length = lists.get(listIdx).size();
            if(idx + 1 < length){
                arr[1]++;
                arr[0] = lists.get(listIdx).get(idx + 1);
                pq.add(arr);
            }
        }
        return res;
    }

    public static ArrayList<Integer> mergeTwoLists(ArrayList<Integer> list1, ArrayList<Integer> list2){
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0, n = list1.size();
        int j = 0, m = list2.size();
        while(i < n && j < m){
            if(list1.get(i) < list2.get(j)) ans.add(list1.get(i++));
            else ans.add(list2.get(j++));
        }
        while(i < n) ans.add(list1.get(i++));
        while(j < m) ans.add(list2.get(j++));
        return ans;
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists, int si, int ei){
        if(si == ei) return lists.get(si);
        int mid = (si + ei)/2;
        ArrayList<Integer> list1 = mergeKSortedLists(lists, si, mid);
        ArrayList<Integer> list2 = mergeKSortedLists(lists, mid + 1, ei);
        return mergeTwoLists(list1, list2);
    }



    public static void main(String[] args){
        return;
    }
}
