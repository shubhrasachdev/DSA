import java.util.ArrayList;
import java.util.Arrays;

public class l004 {

    // May 27, 2021
    static char[][] box = { 
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, 
            { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, 
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, 
            { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, 
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

    static String[] words = { "agra", "norway", "england", "gwalior" };

    public static boolean isSafeToPlaceH(String word, int r, int c) {
        
        for (int i = 0; i < word.length(); i++) {
            if (c + i >= box[0].length)
                return false;
            if (box[r][c + i] != '-' && box[r][c + i] != word.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean[] placeH(String word, int r, int c) {
        boolean[] loc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (box[r][c + i] == '-') {
                box[r][c + i] = word.charAt(i);
                loc[i] = true;
            }
        }
        return loc;
    }

    public static void unPlaceH(String word, int r, int c, boolean[] loc) {
        for (int i = 0; i < word.length(); i++) {
            if (loc[i]) {
                box[r][c + i] = '-';
            }
        }
    }

    public static boolean isSafeToPlaceV(String word, int r, int c) {
        for (int i = 0; i < word.length(); i++) {
            if (r + i == box.length)
                return false;
            if (box[r + i][c] != '-' && box[r + i][c] != word.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean[] placeV(String word, int r, int c) {
        boolean[] loc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (box[r + i][c] == '-') {
                box[r + i][c] = word.charAt(i);
                loc[i] = true;
            }
        }
        return loc;
    }

    public static void unPlaceV(String word, int r, int c, boolean[] loc) {
        for (int i = 0; i < word.length(); i++) {
            if (loc[i]) {
                box[r + i][c] = '-';
            }
        }
    }

    public static int crossWord(int idx) {
        if (idx == words.length) {
            print2D();
            return 1;
        }

        String word = words[idx];
        int n = box.length, m = box[0].length, count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == '-' || box[i][j] == word.charAt(0)) {
                    if (isSafeToPlaceH(word, i, j)) {
                        boolean[] loc = placeH(word, i, j);
                        count += crossWord(idx + 1);
                        unPlaceH(word, i, j, loc);
                    }

                    if (isSafeToPlaceV(word, i, j)) {
                        boolean[] loc = placeV(word, i, j);
                        count += crossWord(idx + 1);
                        unPlaceV(word, i, j, loc);
                    }

                }

            }
        }

        return count;
    }

    public static void print2D() {
        int n = box.length, m = box[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void crossWord() {
        Arrays.sort(words, (a, b) -> {
            return b.length() - a.length();
        });

        System.out.println(crossWord(0));
    }

    // May 28, 2021


    // public static void kSubsetEqualSum(int[] arr, int vidx, int tar, int[] totalSet, int k, ArrayList<ArrayList<Integer>> ans){
    //     +++++++++++++if(vidx == arr.length){
    //         if(tar == 0){
    //             int val = totalSet[0];
    //             for(int i = 1; i < totalSet.length; i++){
    //                 if(val != totalSet[i]) return;
    //             }
    //             System.out.println(ans);            
    //         }
    //         return;
    //     }
    //     for(int i = 0; i < k; i++){
    //         totalSet[i] += arr[vidx];
    //         ans.get(i).add(arr[vidx]);
    //         kSubsetEqualSum(arr, vidx + 1, tar - arr[vidx], totalSet, k, ans);
    //         ans.get(i).remove(ans.get(i).size() - 1);
    //         totalSet[i] -= arr[vidx];
    //     }

    // }

    // public static void kSubsetEqualSum(int[] arr, int k) {

    //     int sum = 0;
    //     for(int ele: arr) sum += ele;
    //     if(sum % k != 0 || arr.length < k) return;
    //     ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    //     for(int i = 0; i < k; i++) ans.add(new ArrayList<>());
    //     int tar = sum / k;
    //     int[] totalSet = new int[k];
    //     kSubsetEqualSum(arr, 0, totalSet, ans);
    // }

    public static boolean isPalindrome(String str) {
        int low = 0, high = str.length() - 1;
        while(low <= high) {
            if(str.charAt(low) != str.charAt(high)) return false;
            low++;
            high--;
        }
        return true;
    }

    public static void allPalindromicPartiions(String str, String asf) {
        for(int i = 0; i < str.length(); i++) {
            String smallStr = str.substring(0, i + 1);
            if(isPalindrome(smallStr)) {
                allPalindromicPartiions(str.substring(i + 1), asf + "(" + smallStr + ") ");
            }
        }
    }

    // Portal - Friends Pairing
    static int counter = 1;
    public static int friendsPairing(int totalFriends, boolean[] vis, String asf) {
        if(totalFriends == 0) {
            System.out.println(counter++ + "." + asf);
            return 1;
        }
        
        int firstUnusedFriend = 0;
        for(int i = 1; i < vis.length; i++) {
            if(!vis[i]) {
                firstUnusedFriend = i;
                break;
            }
        }
        int count = 0;
        vis[firstUnusedFriend] = true;
        count += friendsPairing(totalFriends - 1, vis, asf + "(" + firstUnusedFriend + ") ");
        for(int i = firstUnusedFriend + 1; i < vis.length; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            count += friendsPairing(totalFriends - 2, vis, asf + "(" + firstUnusedFriend + "," + i +") ");
            vis[i] = false;
        }
        vis[firstUnusedFriend] = false;
        return count;
    }

    // Largest Number possible after k swaps
    public static int maxNum = 0;

    public static void swap(StringBuilder sb, int i, int j){
        char ch1 = sb.charAt(i);
        char ch2 = sb.charAt(j);
        sb.setCharAt(i, ch2);
        sb.setCharAt(j, ch1);
    }

    public static void largestNumber(StringBuilder sb, int k) {
        if(k == 0) return;
        boolean flag = false;
        for(int i = 0; i < sb.length(); i++) {
            char maxCh = sb.charAt(i);
            int idx = 0;
            for(int j = i + 1; j < sb.length(); j++) {
                if(sb.charAt(j) > maxCh) {
                    maxCh = sb.charAt(j);
                    idx = j;
                }
            }
            if(i == idx) continue;

            flag = true;
            swap(sb, i, idx);
            int possibleNum = Integer.parseInt(sb.toString());
            if(possibleNum > maxNum) maxNum = possibleNum;
            largestNumber(sb, k - 1);
            swap(sb, i, idx);
        }
        if(!flag) return;
    }
    
    public static void largestNumber() {
        String str = "1234567";
        int k = 4;
        largestNumber(new StringBuilder(str), k);
        System.out.println(maxNum);
    }


    public static void main(String[] args) {
        crossWord();
    }

}