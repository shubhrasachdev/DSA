import java.util.Scanner;

public class btd{
    public static Scanner sc = new Scanner(System.in);

    public static long binaryToDecimal(long n){
        long res = 0, multiplier = 1;
        while(n > 0){
            long rem = n % 10;
            n /= 10;
            res = res + rem * multiplier;
            multiplier *= 2;
        }
        return res;
    }
  
    public static void main(String[] args){
        long n = sc.nextLong();
        System.out.println(binaryToDecimal(n));
    }
}