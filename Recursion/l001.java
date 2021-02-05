import java.util.*;

class l001 {    
    // recursion lecture 1
    // print increasing numbers from a to b
    void printIncreasing(int a, int b){
        if(a == b + 1)
            return;
        System.out.print(a + " ");
        printIncreasing(a+1, b);

    }
    
    // print numbers from 1 to n
    void printIncreasing(int n){
        if(n == 0) return;
        printIncreasing(n-1);
        System.out.println(n);     
    }

    // print numbers from n to 1
    void printDecreasing(int n){
        if (n == 0) return;
        System.out.print(n + " ");
        printDecreasing(n-1);
    }
    
    // print numbers from n to 1 and then 1 to n
    void printIncDec(int n){
        if (n == 0) return;
        System.out.println(n);
        printIncDec(n-1);
        System.out.println(n);
    }

    // calculate factorial of n
    int factorial(int n){
        if(n == 0) return 1;
        return n * factorial(n - 1);
    }

    // recursion lecture 2    
    // calculate x raised to power n in linear time
    int power(int x,int n){
        if(n == 0) return 1;
        return x*power(x, n-1);
    }

    // calculate x raised to power n in logarithmic time
    int powerLogarithmic(int x,int n){
        if(n == 0) return 1;
        int partialAns = powerLogarithmic(x, n/2);
        partialAns *= partialAns;
        return n % 2 == 0 ? partialAns : x * partialAns;
    }

    // find the nth term in a fibonacci series
    int fibonacci(int n){
        if(n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // find the nth term in a tribonacci series - t(0) = 0, t(1) = 1, t(2) = 1, t(n) = t(n-1) + t(n-2) + t(n-3)
    int tribonacci(int n){
        if(n <= 1) return n;
        if(n == 2) return 1;
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    // display array elements using recursion
    void display(int arr[], int idx, int n){
        if(idx == n) return;
        System.out.println(idx);
        display(arr, idx + 1, n);
    }

    // display array elements in reverse using recursion
    void reverseDisplay(int arr[], int idx, int n){
        if(idx == n) return;
        reverseDisplay(arr, idx + 1, n);
        System.out.println(arr[idx]);
    }

    // max of array using recursion
    int maxOfArray(int[] arr, int idx) {
        if(idx == arr.length) return Integer.MIN_VALUE; 
        int res = maxOfArray(arr, idx+1);
        return arr[idx] > res ? arr[idx] : res;
    }
  
    // find first index of an element in an array
    int firstIndex(int arr[], int idx, int x, int n){
        if(idx == n) return -1;
        if(arr[idx] == x) return idx;
        return firstIndex(arr, idx + 1, x, n);
    }

    // find last index of an element in an array
    int lastIndex(int arr[], int idx, int x, int n){
        if(idx == n) return -1;
        int prev = lastIndex(arr, idx + 1, x, n);
        if(prev == -1){
            if(arr[idx] == x) return idx;
            return -1;
        }
        return prev;
    }

    // recursion lecture 3
    // pattern 1
    // pattern 2
    // check if arr is palindrome
    boolean isPalindrome(int[] arr, int startInd, int endInd){
        if(startInd >= endInd) return true;
        if(arr[startInd] != arr[endInd]) return false;
        return isPalindrome(arr, startInd + 1, endInd - 1);
    }
    
    // reverse of an array using recursion
    void reverseOfArray(int[] arr, int si, int ei){
        if(si >= ei) return;
        int temp = arr[si];
        arr[si] = arr[ei];
        arr[ei] = temp;
        reverseOfArray(arr, si + 1, ei - 1);
    }

    // inverse of an array using recursion
    void inverseArray(int[] arr, int i){
        if(i == arr.length) return;
        int val = arr[i];
        inverseArray(arr, i + 1);
        arr[val] = i;
    }

    // recursion - homework
    /* 16. Sum of Digits in a string 
        Input   : "1234"
        Output  : 10
    */
    int sumOfDigits(String num, int idx){
        if(idx == num.length()) return 0;
        int ans = sumOfDigits(num, idx + 1);
        int a = num.charAt(idx) - '0';
        return a + ans;
    }

    /* 17. Convert string to int recursively 
    Input   : "1234"
    Output  : 1234
    */
    long convertToInt(String str, int idx, long pow){
        if(idx == -1) return 0;
        long a = str.charAt(idx) - '0';
        return a * pow + convertToInt(str, idx - 1, pow * 10);
   }

    // 18. Check if two strings are the reverse of each other
    boolean isReverse(String one, int i, String two, int j){
        if(one.charAt(i) != two.charAt(j)) return false;
        if(i >= j) return true;
        return isReverse(one, i + 1, two, j - 1);
    }

    // 19. Check if a string is a palindrome ignoring the case of the characters
    boolean isPalindrome(String str, int start, int end){
        if(start >= end) return true;
        char x = str.charAt(start);
        char y = str.charAt(end);
        // X - 'A' = x - 'a'
        if(x >= 'A' && x <= 'Z') x = (char) (x - 'A' + 'a');
        if(y >= 'A' && y <= 'Z') y = (char) (y - 'A' + 'a');
        if(x != y) return false;
        return isPalindrome(str, start + 1, end - 1);
    }

    // 20. Separate adjacent repeating characters with a *
    // Eg: PEPPEPCODING -> PEP*PEPCODING
    
    // Create ans going down
    String separateDuplicates(String str){
        if(str.length() == 0) return "";
        if(str.length() == 1) return str;
        char ch = str.charAt(0);
        String res = str.substring(1);
        String recRes = separateDuplicates(res);
        if(ch != recRes.charAt(0)) return ch + recRes;
        return ch + "*" + recRes;
    }
    // Create ans going up
    void separateDuplicatesWayUp(String str, int idx,  String ans){
        if(idx == str.length() - 1){
          System.out.println(ans + str.charAt(idx));
          return;
        } 
        char ch = str.charAt(idx);
        if(ch == str.charAt(idx + 1)) separateDuplicatesWayUp(str, idx + 1, ans + ch + "*");
        else separateDuplicatesWayUp(str, idx + 1, ans + ch);
    }

    // 21. Remove adjacent duplicate characters from string
    // Eg PEPPEP -> PEPEP


    // printing subsequences for a given string - method 1
    // I/P: yvTA 
    // O/P: yvTA yvT yvA yv yTA yT yA y vTA vT vA v TA T A
    public static void printSS(String str, int idx, String ans) {
        if(idx == str.length()){
            System.out.println(ans);
            return;
        }
        printSS(str, idx + 1, ans + str.charAt(idx));
        printSS(str, idx + 1, ans);
        
    }

    // printing subsequences for a given string - method 2 - using StringBuilder
    // I/P: yvTA 
    // O/P: yvTA yvT yvA yv yTA yT yA y vTA vT vA v TA T A 
    public static void printSS(String str, int idx, StringBuilder ans){
        if(idx == str.length()){
            System.out.println(ans);
            return;
        }
        ans.append(str.charAt(idx));
        printSS(str, idx + 1, ans);
        ans.deleteCharAt(ans.length() - 1);
        printSS(str, idx + 1, ans);
    }

    // returning an arraylist of subsequences
    public static ArrayList<String> gss(String str) {
        if (str.length() == 0) {
            ArrayList < String > tmp = new ArrayList < > ();
            tmp.add("");
            return tmp;
        }
        char ch = str.charAt(0);
        String ros = str.substring(1);
        ArrayList < String > temp = gss(ros);
        ArrayList < String > res = new ArrayList<> (temp);
        for (String var: temp) {
            res.add(ch + var);
        }
        return res;
    }
    public static void main(String[] args){
        l001 obj = new l001();
        obj.printIncreasing(2, 5);
        return;
    }
}