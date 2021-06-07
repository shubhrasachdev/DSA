// April 10, 2021
public class heapSort {

    private static boolean compareTo(int a, int b, boolean isMax){
        if(isMax) return a > b;
        return a < b;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void heapify(int pi, int[] arr, int last, boolean isMax){
        int maxIdx = pi;
        int li = 2*pi + 1;
        int ri = 2*pi + 2;
        if(li <= last && compareTo(arr[li], arr[maxIdx], isMax)) 
            maxIdx = li;
        if(ri <= last && compareTo(arr[ri], arr[maxIdx], isMax)) 
            maxIdx = ri;
        if(maxIdx != pi){
            swap(arr, maxIdx, pi);
            heapify(maxIdx, arr, last, isMax);
        }  
    }
    public static void sort(int[] arr, boolean isMax){
        int n = arr.length;
        for(int i = n - 1; i >= 0; i--) heapify(i, arr, n - 1, isMax);
        for(int i = n - 1; i >= 0; i--){
            heapify(0, arr, i, isMax);
            swap(arr, 0, i);
        }
    }
    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    
    }
    public static void main(String[] args){
        int[] arr = {10,20,30,-2,-3,-4,5,6,7,8,9,22,11,13};
        sort(arr, true); // for ascending
        print(arr);
        sort(arr, false); // for descending
        print(arr);

    }    
}
