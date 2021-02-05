class l001{
    public static void bubble(int arr[], int n) {
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n - 1; i++){
            int minIdx = i;
            for(int j = i + 1; j < n; j++){
                if(isSmaller(arr, j, minIdx)) minIdx = j;
            }
            swap(arr, i, minIdx);
        }
    }

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // return true if ith element is smaller than jth element
    public static boolean isSmaller(int[] arr, int i, int j) {
        System.out.println("Comparing " + arr[i] + " and " + arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }

    public static void insertionSort(int[] arr) {
        int i, j;  
        int n = arr.length;
        for (i = 1; i < n; i++){  
            for(j = i - 1; j >= 0; j--){
                if(isGreater(arr, j, j + 1)) swap(arr, j, j + 1);
                else break;
            }  
        }
    }

    // return true if jth element is greater than ith element
    public static boolean isGreater(int[] arr, int j, int i) {
        System.out.println("Comparing " + arr[i] + " and " + arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] mergeSort(int[] arr, int lo, int hi) {
        if (lo == hi) {
            int[] res = new int[1];
            res[0] = arr[lo];
            return res;
        }

        int mid = (lo + hi) / 2;
        int[] a = mergeSort(arr, lo, mid);
        int[] b = mergeSort(arr, mid + 1, hi);
        int[] res = mergeTwoSortedArrays(a, b);
        return res;
    }

    //used for merging two sorted arrays
    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] ans = new int[a.length + b.length];
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) ans[k++] = a[i++];
            else ans[k++] = b[j++];
        }
        while (i < a.length) {
            ans[k] = a[i];
            k++;
            i++;
        }
        while (j < b.length) {
            ans[k] = b[j];
            k++;
            j++;
        }

        return ans;
    }

    // sort an array with 0 and 1 in linear time
    public static void sort01(int[] arr) {
        int n = arr.length;
        int ptr = 0, itr = 0;
        while (itr < n) {
            if (arr[itr] == 1) itr++;
            else swap(arr, itr++, ptr++);
        }
    }

    // sort an array with 0, 1 and 2 in linear time    
    public static void sort012(int[] arr) {
        int n = arr.length;
        int ptr1 = 0, ptr2 = n-1, itr = 0;
        
        while(itr <= ptr2){
            if(arr[itr] == 0) swap(arr, itr++, ptr1++);
            else if(arr[itr] == 1) itr++;
            else swap(arr, itr, ptr2--);
        }
    }

    public static void countSort(int[] arr, int min, int max) {
        int[] freq = new int[max - min + 1];
        for(int x: arr) freq[x - min]++;
        int idx = 0;
        for(int i = 0; i < freq.length; i++){
            while(freq[i] != 0) {
                arr[idx++] = i + min;
                freq[i]--;
            }
        }
    }


}