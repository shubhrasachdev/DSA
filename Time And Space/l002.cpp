#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int binarySearch(vector<int>& arr, int data, int si, int ei){
    while(si <= ei){
        int mid = (si + ei)/2;
        if(arr[mid] == data) return mid;
        else if (arr[mid] < data) si = mid + 1;
        else ei = mid - 1;
    }
    return -1;
}

// to find a number in a sorted array. if not found, return the number closest to it.
int binarySearchNearestNeighbour(vector<int>& arr, int data){
    int si = 0, ei = arr.size() - 1;
    if(arr[ei] < data) return arr[ei];
    if(arr[si] > data) return arr[si];
    int minDiff;
    while(si <= ei){
        int mid = (si + ei)/2;
        if(arr[mid] == data) return mid;
        else if (arr[mid] < data) si = mid + 1;
        else ei = mid - 1;
    }
    return (arr[si] - data > data < arr[ei] ? arr[si] : arr[ei]);
}


// to find all pairs of numbers in an array whose sum is equal to a given target sum
void targetSumPair(vector<int>& arr, int target){
    sort(arr.begin(), arr.end());
    int si = 0, ei = arr.size() - 1;
    while(si < ei){
        int sum = arr[si] + arr[ei];
        if(sum > target) ei--;
        else if(sum < target) si++;
        else{
            cout<<arr[si]<<", "<<arr[ei]<<endl;
            si++;
            ei--;
        }
    }
}