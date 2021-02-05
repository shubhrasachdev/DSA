import java.util.Scanner;

public class dtb{
    public static Scanner sc = new Scanner(System.in);

    public static long decimalToBinary(long n){
        long res = 0, multiplier = 1;
        while(n > 0){
            long rem = n % 2;
            n /= 2;
            res = res + rem * multiplier;
            multiplier *= 10;
        }
        return res;
    }
    
    
    public static void main(String[] args){
        long n = sc.nextLong();
        System.out.println(decimalToBinary(n));
    }
}