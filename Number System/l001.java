public class l001 {
    // decimal to any base
    public static int getValueInBase(int n, int base) {
        int res = 0, multiplier = 1;
        while (n > 0) {
            int rem = n % base;
            n /= base;
            res = res + rem * multiplier;
            multiplier *= 10;
        }
        return res;
    }

    // any base to decimal
    public static int getValueIndecimal(int n, int base) {
        int res = 0, multiplier = 1;
        while (n > 0) {
            int rem = n % 10;
            n /= 10;
            res = res + rem * multiplier;
            multiplier *= base;
        }
        return res;
    }
    
    public static int anyBaseToAnyBase(int n, int srcBase, int destBase){
        int a = getValueIndecimal(n, srcBase);
        return getValueInBase(a, destBase);
    }
}
