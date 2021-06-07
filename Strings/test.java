import java.util.*;
public class test{
    public static int gcd(int a, int b){
        int ans = 1;
        for (int i = 1; i <= a && i <= b; i++){
            if (a % i == 0 && b % i == 0) ans = i;
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int tasks = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = scn.nextInt();
        int res = 0;
        for(int i = 0; i < tasks; i++){
            int left = scn.nextInt();
            int right = scn.nextInt();
            res += gcd(arr[left], arr[right]);
        }
        System.out.println(res);
    }
}