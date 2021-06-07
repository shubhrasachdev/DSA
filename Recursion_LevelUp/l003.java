import java.util.*;
public class l003 {
    // May 22, 2021
    
    //Portal - Cryptiarithmetic SEND + MORE = MONEY
    static String s1 = "send";
    static String s2 = "more";
    static String s3 = "money";


    static boolean[] usedNumbers = new boolean[10];
    static int[] mapping = new int[128];
   
   
    private static Scanner scn = new Scanner(System.in);

    public static int convertStringToNumber(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 10 + mapping[str.charAt(i)];
        }

        return res;
    }

    public static int crypto(String str, int idx) {
        if (idx == str.length()) {
            if (mapping[s1.charAt(0)] == 0 || mapping[s2.charAt(0)] == 0 || mapping[s3.charAt(0)] == 0)
                return 0;

            int x = convertStringToNumber(s1);
            int y = convertStringToNumber(s2);
            int z = convertStringToNumber(s3);

            if (x + y == z) {
                StringBuilder sb = new StringBuilder();
                for (int i = 'a'; i <= 'z'; i++) {
                    if (mapping[i] >= 0) {
                        sb.append((char) i + "-" + mapping[i] + " ");
                    }
                }

                System.out.println(sb.toString());
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch = str.charAt(idx);
        for (int i = 0; i <= 9; i++) {
            if (!usedNumbers[i]) {
                usedNumbers[i] = true;
                mapping[ch] = i;

                count += crypto(str, idx + 1);
                usedNumbers[i] = false;
                mapping[ch] = -1;
            }
        }

        return count;
    }

    public static void crypto() {
        String str = s1 + s2 + s3;
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        str = "";
        for (int i = 0; i < 26; i++)
            if (freq[i] > 0)
                str += (char) (i + 'a');

        Arrays.fill(mapping, -1);

        System.out.println(crypto(str, 0));
    }

    // Portal - Word Break
    public static int wordBreak(String str, int idx, String ans, HashSet<String> dict){
	    if(idx >= str.length()){
	        System.out.println(ans);
	        return 1;
	    }
	    int count = 0;
	    for(int i = idx; i  <= str.length(); i++){
	        String word = str.substring(idx, i);
	        if(dict.contains(word)){
	            count += wordBreak(str, i, ans + word + " ", dict);
	        }
	    }
	    return count;
	}

    public static void wordBreakMain(String[] args) {
		int n = scn.nextInt();
		HashSet<String> dict = new HashSet<>();
		for(int i = 0  ; i  < n; i++){
			dict.add(scn.next());
		}
		String sentence = scn.next();
		wordBreak(sentence, 0, "", dict);
	}

    // May 27, 2021
    
    // Sudoku solver
    // Leetcode 37: https://leetcode.com/problems/sudoku-solver/
    public static boolean isSafeToPlaceNum(char[][] board, int row, int col, int num){
        int n = board.length, m = board[0].length;
        // row
        for(int i = 0; i < n; i++) 
            if(board[i][col] - '0' == num) return false;
        // col
        for(int j = 0; j < m; j++) 
            if(board[row][j] - '0' == num) return false;
        // mat
        row = (row/3) * 3;
        col = (col/3) * 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++)
                if(board[row + i][col + j] - '0' == num) return false;
        }
        return true;
    }

    public static boolean sudokuSolver(char[][] board, int idx){
        if(idx == 81){
            return true;
        }
        int row = idx / 9, col = idx % 9;
        if(board[row][col] != '.'){
            if(sudokuSolver(board, idx + 1)) return true;
        } else {
            for(int num = 1; num <= 9; num++) {
                if(isSafeToPlaceNum(board, row, col, num)){
                    board[row][col] = (char)(num + '0');
                    if(sudokuSolver(board, idx + 1)) return true;
                    board[row][col] = '.';
                }
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        sudokuSolver(board, 0);
    }

    public static boolean sudokuSolver2(char[][] board, ArrayList<Integer> IDX, int idx){
        if(idx == IDX.size()){
            return true;
        }
        int r = IDX.get(idx) / 9;
        int c = IDX.get(idx) % 9;
        for(int num = 1; num <= 9; num++){
            if(isSafeToPlaceNum(board, r, c, num)){
                board[r][c] = (char) (num + '0');
                if(sudokuSolver2(board, IDX, idx + 1)) return true;
                board[r][c] = '.';
            
            }
        }
        return false;
    }

    public static void solveSudoku2(char[][] board){
        ArrayList<Integer> IDX = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.'){
                    IDX.add(i * 9 + j);
                }
            }
        }
        sudokuSolver2(board, IDX, 0);
    }
    public static void main(String[] args){
        
    }
}
