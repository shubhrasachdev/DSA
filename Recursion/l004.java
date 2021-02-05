public class l004 {

    // jan 22, 2021

    // divide an array into two sets set 1 and set 2 such that the sum of their elements is equal, they have a null intersection 
    // and the union is the original array
    public static int equiSet(int[] arr, int idx, int sum1, int sum2, String set1, String set2){
        if(idx == arr.length){
            if(sum1 == sum2){
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }
        
        int count = 0;
        count += equiSet(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " ", set2);
        count += equiSet(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");
        return count;
    }

    public static void main(String[] args){
        int[] arr = {10, 30, 40, 50, 20, 60, 80, 70};
        System.out.println(equiSet(arr, 1, arr[0], 0, arr[0] + " ", ""));
    }
    
}
