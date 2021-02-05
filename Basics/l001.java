import java.util.*;

public class l001 {
    
    private static Scanner sc;
    public static void printTableOfN(int n){
        for(int i = 1;i <= 10;i++){
            System.out.println(n + " X " +  i + " = " + n * i);
        }
    } 
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0) return false;
        return true;
    }

    public static void primesInRange(int low, int high){
        for(int i = low; i <= high; i++){
            if(isPrime(i)) System.out.println(i);
        }
    }

    public static int countDigits(int num){
        int count = 0;
        while(num > 0){
            count ++;
            num /= 10;
        }
        return count;
    }
    
    public static void printDigits(int num){
        int n = countDigits(num) - 1;
        n = (int)Math.pow(10, n);
        while(n >= 1){
            System.out.println((num/n) % 10);
            n /= 10;
        }
    }
    
    public static void printFibo(int n){
        if(n == 0) return;
        int a = 0, b = 1, c = 0;
        for(int i = 0; i < n; i++){
            System.out.println(c);
            a = b;
            b = c;
            c = a + b;
        }
    }

    public static void reverse(int num){
        while(num > 0){
            System.out.println(num % 10);
            num /= 10;
        }
    }

    public static int invert(int num){
        int placeVal = 1;
        int res = 0;
        while(num > 0){
            int digit = num % 10;
            int b = digit - 1;
            res += placeVal * Math.pow(10, b);
            num /= 10;
            placeVal++;
        }
        return res;
    }

    public static int rotate(int n,int r){
        int len = countDigits(n);
        // r = r % len;
        // if(r < 0) r += len;

        r = (r % len + len) % len;

        int mul = 1;
        int div = 1;
        
        for(int i=1;i<=len;i++){
            if(i <= r) div *= 10;
            else mul *= 10;
        }
        
        int a = n % div;
        int b = n / div;
        
        return a * mul + b;
    }

    public static int gcd(int a, int b){
        int min = a < b ? a : b;
        int gcd = 1;
        for(int i = 2; i <= min; i++){
            if((a % i == 0) && (b % i == 0)) gcd = i;
        }
        return gcd;
    }
    
    public static int lcm(int a, int b){
        return (a * b) / gcd(a, b);
    }
    
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int n = sc.nextInt();
        printFibo(n);
    }
}