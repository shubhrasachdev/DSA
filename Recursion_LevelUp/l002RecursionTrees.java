// May 15, 2021
public class l002RecursionTrees{
    
    public static int permutationWithInfi(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += permutationWithInfi(arr, tar - ele, ans + ele);
            }
        }
        return count;
    }

    public static int combinationWithInfi(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithInfi(arr, tar - arr[i], i, ans + arr[i]);
        }
        return count;
    }

    public static int combinationWithSingle(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithSingle(arr, tar - arr[i], i + 1, ans + arr[i]);
        }

        return count;
    }

    public static int permutationWithSingleCoin(int[] arr, int tar, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 0 && tar - arr[i] >= 0){
                int val = arr[i];
                arr[i] = -arr[i];
                count += permutationWithSingleCoin(arr, tar - val, ans + val);
                arr[i] = -arr[i];
            }
        }
        return count;
    }


    // SUBSEQUENCE METHODS - ALTERNATIVES TO ABOVE

    public static int combinationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += combinationWithInfi_subSeq(arr, tar, idx + 1, ans);
        if(tar - arr[idx] >= 0) count += combinationWithInfi_subSeq(arr, tar - arr[idx], idx, ans + arr[idx]);
        return count;
    }

    
    public static int combinationWithSingle_subSeq(int[] arr, int tar, int idx, String ans) {
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += combinationWithSingle_subSeq(arr, tar, idx + 1, ans);
        if(tar - arr[idx] >= 0) count += combinationWithSingle_subSeq(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        return count;
    }

    public static int permutationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += permutationWithInfi_subSeq(arr, tar, idx + 1, ans);
        if(tar - arr[idx] >= 0) count += permutationWithInfi_subSeq(arr, tar - arr[idx], 0, ans + arr[idx]);
        return count;
       
    }

    public static int permutationWithSingleCoin_subSeq(int[] arr, int tar, int idx, String ans){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if(arr[idx] > 0 && tar - arr[idx] >= 0){
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationWithSingleCoin_subSeq(arr, tar - val, 0, ans + val);
            arr[idx] = -arr[idx];
        }
        count += permutationWithSingleCoin_subSeq(arr, tar, idx + 1, ans);
        return count;
    }


    // 1D Queen Set

    // tbox - total boxes
    // tqn - total queens
    // qpsf - queens places so far
    // bn - box number
    public static int queenCombination(int tbox, int tqn, int qpsf, int bn, String ans){
        if(qpsf == tqn){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = bn; i < tbox; i++){
            count += queenCombination(tbox, tqn, qpsf + 1, i + 1, ans + "b" + i + "q" + qpsf + " ");
        }
        return count;
    }


    // no priority
    public static int queenPermutation(boolean[] tboxe, int tqn, int qpsf, int bn, String ans) {
        if (qpsf == tqn) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxe.length; i++) {
            if (!tboxe[i]) {
                tboxe[i] = true;
                count += queenPermutation(tboxe, tqn, qpsf + 1, 0, ans + "b" + i + "q" + qpsf + " ");
                tboxe[i] = false;
            }
        }
        return count;
    }

    // May 16, 2021
    // 2D Queen Set
    public static int queenCombination2D(boolean[][] boxes, int tqn, int bn, String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }
        int n = boxes.length, m = boxes[0].length, count = 0;
        for(int i = bn; i < n*m; i++){
            int r = i / m;
            int c = i % m;
            count += queenCombination2D(boxes, tqn - 1, i + 1, ans + "(" + r + ", " + c + ") ");
        }
        return count;
    }

    public static int queenPermutation2D(boolean[][] boxes, int tqn, int bn, String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }
        int n = boxes.length, m = boxes[0].length, count = 0;
        for(int i = bn; i < n*m; i++){
            int r = i / m;
            int c = i % m;
            if(!boxes[r][c]){
                boxes[r][c] = true;
                count += queenPermutation2D(boxes, tqn - 1, 0, ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
            
        }
        return count;
    }

    public static void queenSet(){
        // System.out.println(queenCombination(6, 4, 0, 0, ""));
        boolean[][] boxes = new boolean[4][4];
        //System.out.println(queenCombination2D(boxes, 4, 0, ""));
        System.out.println(queenPermutation2D(boxes, 4, 0, ""));
    }


    // May 20, 2021

    // N Queen Series

    public static boolean isSafeToPlaceQueen(boolean[][] boxes, int r, int c){
        // int[][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}}; // for combination
        int[][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}; // for subsequence
        int n = boxes.length, m = boxes[0].length;
        for(int d = 0; d < dir.length; d++){
            for(int rad = 1; rad < n; rad++){
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];
                if(x >= 0 && y >= 0 && x < n && y < m){
                    if(boxes[x][y]) return false;
                }
                else break;
            }
        }
        return true;
    }

    // combination method - verified
    public static int nqueen_Combination01(boolean[][] boxes, int tqn, int idx, String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }
        int n = boxes.length, m = boxes[0].length, count = 0;
        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(isSafeToPlaceQueen(boxes, r, c)){
                boxes[r][c] = true;
                count += nqueen_Combination01(boxes, tqn - 1, i + 1, ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }

    // subsequence method - verified
    public static int nqueen_Combination02(boolean[][] boxes, int tqn, int idx, String ans){
        int n = boxes.length, m = boxes[0].length, count = 0;
        if(tqn == 0 || idx >= n * m){
            if(tqn == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int r = idx / m;
        int c = idx % m;
        if(isSafeToPlaceQueen(boxes, r, c)){
            boxes[r][c] = true;
            count += nqueen_Combination02(boxes, tqn - 1, idx + 1, ans + "(" + r + ", " + c + ") ");
            boxes[r][c] = false;
        }
        count += nqueen_Combination02(boxes, tqn, idx + 1, ans);
        return count;
    }

    
    // Permutation - verified
    public static int nqueen_Permutation01(boolean[][] boxes, int tqn, String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }
        int n = boxes.length, m = boxes[0].length, count = 0;
        for(int i = 0; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c)){
                boxes[r][c] = true;
                count += nqueen_Permutation01(boxes, tqn - 1, ans + "(" + r + ", " + c + ") ");
                boxes[r][c] = false;
            }
        }
        return count;
    }


    // Optimized isSafe() 
    static boolean[] row;
    static boolean[] col;
    static boolean[] diag;
    static boolean[] adiag;

    static int calls = 0;

    public static int nqueen_Combination03(int n, int m, int tqn, int idx, String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }
        calls++;
        int count = 0;
        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + (m - 1)]){
                row[r] = col[c] = diag[r + c] = adiag[r - c + (m - 1)] = true;
                count += nqueen_Combination03(n , m, tqn - 1, i + 1, ans + "(" + r + ", " + c + ") ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + (m - 1)] = false;
            }
        }
        return count;
    }

    public static int nqueen_Permutation03(int n, int m, int tqn, String ans){
        if(tqn == 0){
            System.out.println(ans);
            return 1;
        }
        calls++;
        int count = 0;
        for(int i = 0; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + (m - 1)]){
                row[r] = col[c] = diag[r + c] = adiag[r - c + (m - 1)] = true;
                count += nqueen_Permutation03(n , m, tqn - 1, ans + "(" + r + ", " + c + ") ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + (m - 1)] = false;
            }
        }
        return count;
    }

    // May 22, 2021

    // N- Queen Optimized
    public static int nqueen_Combination04(int floor, int m, int tnq, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }
        calls++;
        int count = 0;
        for(int room = 0; room < m; room++){
            int r = floor, c = room;
            if(!col[c] && !diag[r + c] && !adiag[r - c + m - 1]){
                col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nqueen_Combination04(floor + 1, m, tnq - 1, ans + "(" + r + ", " + c + ")");
                col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    public static int nqueen_Permutation04(int floor, int m, int tnq, String ans){
        if(tnq == 0 || floor >= m){
            if(tnq == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        calls++;
        int count = 0;
        for(int room = 0; room < m; room++){
            int r = floor, c = room;
            if(!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]){
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nqueen_Permutation04(0, m, tnq - 1, ans + "(" + r + ", " + c + ")");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        count += nqueen_Permutation04(floor + 1, m, tnq, ans);
        return count;
    }


    public static void nqueenSet(){
        int n = 4, m = 4, q = 4;
        // boolean[][] boxes = new boolean[n][m];
        row = new boolean[n];
        col = new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[n + m - 1];
        // System.out.println(nqueen_Permutation03(n, m, q, ""));
        // System.out.println(nqueen_Combination03(n, m, q, 0, ""));
        // System.out.println(calls);
        System.out.println(nqueen_Permutation04(0, m, q, ""));
        System.out.println(calls);
    }


    public static void main(String[] args) {
        nqueenSet();

    }
}