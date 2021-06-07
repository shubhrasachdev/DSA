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
    public static int getValueInDecimal(int n, int base) {
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
        int a = getValueInDecimal(n, srcBase);
        return getValueInBase(a, destBase);
    }

    // find sum of two numbers n1 and n2 in given base b
    public static int getSum(int b, int n1, int n2){
        int x = getValueInDecimal(n1, b);
        int y = getValueInDecimal(n2, b);
        int res = x + y;
        return getValueInBase(res, b);
    }

    // find diff of two numbers n1 and n2 (n2 - n1) in given base b
    public static int getDifference(int b, int n1, int n2){
        int x = getValueInDecimal(n1, b);
        int y = getValueInDecimal(n2, b);
        int res = y - x;
        return getValueInBase(res, b);
    }
}
