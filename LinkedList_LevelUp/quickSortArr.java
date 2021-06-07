import java.util.*;
public class quickSortArr {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int segregate(int[] arr, int pivotIdx, int sp, int ep) {
        swap(arr, pivotIdx, ep);
        int p = sp - 1, itr = sp;
        while (itr <= ep) {
            if (arr[itr] <= arr[ep])
                swap(arr, ++p, itr);
            itr++;
        }
        return p;
    }
    public static void quickSort(int[] arr, int si, int ei) {
        if (si > ei) return;
        
        // check if already sorted
        boolean flag = true;
        for (int i = si + 1; i <= ei; i++){
            if (arr[i - 1] > arr[i]) {
            flag = false;
            break;
            }
        }
        if(flag) return;
        int pivotIdx = (si + ei)/2;
        pivotIdx = segregate(arr, pivotIdx, si, ei);
        quickSort(arr, si, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, ei);
    }
    public static void print(int[] arr) {
        System.out.print("[");
        int i = 0;
        for(i = 0; i < arr.length - 1; i++) System.out.print(arr[i] + ", ");
        System.out.println(arr[i] + "]");
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = scn.nextInt();
        System.out.println("Original Array");
        print(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted Array");
        print(arr);
    }

}
