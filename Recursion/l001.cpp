#include <iostream>
#include <cmath>
#include <string>
#include <vector>
using namespace std;

// recursion lecture 1 - Jan 8, 2021
// print increasing numbers from a to b
void printIncreasing(int a, int b){
    if (a == b + 1) return;
    cout << a <<" ";
    printIncreasing(a + 1, b);
}

// print numbers from 1 to n
void printIncreasing(int n){
    if(n == 0) return;
    printIncreasing(n-1);
    cout << n << endl;   
}

// print numbers from n to 1
void printDecreasing(int n){
    if (n == 0) return;
    cout << n << endl;
    printDecreasing(n-1);
}

// print numbers from n to 1 and then 1 to n
void printIncDec(int n){
    if (n == 0) return;
    cout << n << endl;
    printIncDec(n-1);
    cout<< n << endl;
}

// calculate factorial of n
int factorial(int n){
    if(n == 0) return 1;
    return n * factorial(n - 1);
    
}

// recursion lecture 2
// calculate x raised to power n in linear time
int power(int x,int n){
    if(n == 0) return 1;
    return x*power(x, n-1);
}

// calculate x raised to power n in logarithmic time
int powerLogarithmic(int x,int n){
    if(n == 0) return 1;
    int partialAns = powerLogarithmic(x, n/2);
    partialAns *= partialAns;
    return n % 2 == 0 ? partialAns : x * partialAns;
}


// find the nth term in a fibonacci series
int fibonacci(int n){
    if(n <= 1) return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
}

// find the nth term in a tribonacci series - t(0) = 0, t(1) = 1, t(2) = 1, t(n) = t(n-1) + t(n-2) + t(n-3)
int tribonacci(int n){
    if(n <= 1) return n;
    if(n == 2) return 1;
    return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
}

// display array elements using recursion
void display(int arr[], int idx, int n){
    if(idx == n) return;
    cout<<arr[idx]<<endl;
    display(arr, idx + 1, n);
}

// display array elements in reverse using recursion
void reverseDisplay(int arr[], int idx, int n){
    if(idx == n) return;
    reverseDisplay(arr, idx + 1, n);
    cout<<arr[idx]<<endl;
}

// find max of arr using recursion
int maxOfArray(vector<int>& arr, int idx, int n){
    if(idx == n) return -1e9;
    return max(arr[idx], maxOfArray(arr, idx + 1, n));   
}

// find first index of an element in an array
int firstIndex(int arr[], int idx, int x, int n){
    if(idx == n) return -1;
    if(arr[idx] == x) return idx;
    return firstIndex(arr, idx + 1, x, n);
}

// find last index of an element in an array
int lastIndex(int arr[], int idx, int x, int n){
    if(idx == n) return -1;
    int prev = lastIndex(arr, idx + 1, x, n);
    if(prev == -1){
        if(arr[idx] == x) return idx;
        return -1;
    }
    return prev;
}

// recursion lecture 3
// pattern 1
// pattern 2

// check if arr is palindrome
bool isPalindrome(vector<int>& arr, int startInd, int endInd){
    if(startInd >= endInd) return true;
    if(arr[startInd] != arr[endInd]) return false;
    return isPalindrome(arr, startInd + 1, endInd - 1);
}

// reverse of an array using recursion
void reverseOfArray(vector<int>& arr, int si, int ei){
    if(si >= ei) return;
    swap(arr[si], arr[ei]);
    reverseOfArray(arr, si + 1, ei - 1);
}

// inverse of an array using recursion
void inverseArray(vector<int>& arr, int i){
    if(i == arr.size()) return;
    int val = arr[i];
    inverseArray(arr, i + 1);
    arr[val] = i;
}

// recursion - homework

/* 16. Sum of Digits in a string 
    Input   : "1234"
    Output  : 10
*/
int sumOfDigits(string num, int idx){
        if(idx == (int)num.length()) return 0;
        int ans = sumOfDigits(num, idx + 1);
        int a = num[idx] - '0';
        return a + ans;
}

/* 17. Convert string to int recursively 
    Input   : "1234"
    Output  : 1234
*/
long convertToInt(string str, int idx, long pow){
    if(idx == -1) return 0;
    long a = str[idx] - '0';
    return a * pow + convertToInt(str, idx - 1, pow * 10);
}

// 18. Check if two strings are the reverse of each other
bool isReverse(string one, int i, string two, int j){
    if(one[i] != two[j]) return false;
    if(i >= j) return true;
    return isReverse(one, i + 1, two, j - 1);
}

// 19. Check if a string is a palindrome ignoring the case of the characters
bool isPalindrome(string str, int start, int end){
    if(start >= end) return true;
    char x = str[start];
    char y = str[end];
    /// X - 'A' = x - 'a'
    if(x >= 'A' && x <= 'Z') x = x - 'A' + 'a';
    if(y >= 'A' && y <= 'Z') y = y - 'A' + 'a';
    if(x != y) return false;
    return isPalindrome(str, start + 1, end - 1);
}

// 20. Separate adjacent repeating characters with a *
// Eg: PEPPEPCODING -> PEP*PEPCODING
// Going down

// Going up

// 21. Remove adjacent duplicate characters from string
// Eg PEPPEP -> PEPEP
// Going down
string removeDuplicates(string str){
    if(str.length() == 1) return str;
    char ch = str[0];
    string ros = str.substr(1);
    string res = removeDuplicates(ros);
    if(ch == res[0]) return res;
    else return ch + res;
    
}
// Going up
void removeDuplicates_wayUp(string str, int idx, string ans){
    if(idx == str.length() - 1){
        cout << ans + str[idx];
        return;
    }
    char ch = str[idx];
    if(ch == str[idx+1]) removeDuplicates_wayUp(str, idx + 1, ans);
    else removeDuplicates_wayUp(str, idx + 1, ans + ch);
}

int main() {
    return 0;
}