// march 2, 2020

import java.util.*;
public class l001{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        Node(int data){this.data = data;}
    }

    public static void preOrder(Node root, ArrayList<Integer> arr){
        if(root == null) return;
        arr.add(root.data);
        preOrder(root.left, arr);
        preOrder(root.right, arr);
    }

    public static void inOrder(Node root, ArrayList<Integer> arr){
        if(root == null) return;
        inOrder(root.left, arr);
        arr.add(root.data);
        inOrder(root.right, arr);
    }

    public static void postOrder(Node root, ArrayList<Integer> arr){
        if(root == null) return;
        postOrder(root.left, arr);
        postOrder(root.right, arr);
        arr.add(root.data);
    }

    // Basic functions
    public static int size(Node root){
        if(root == null) return 0;
        int left = size(root.left);
        int right = size(root.right);
        return left + right + 1;
    }

    public static int height(Node root){
        if(root == null){
            return -1; // for edge wise height
            // return 0; // for node wise height
        }
        int leftHt = height(root.left);
        int rightHt = height(root.right);
        return Math.max(leftHt, rightHt) + 1;
    }

    public static int maximum(Node root){
        if(root == null) return -(int) 1e8;
        int lmax = maximum(root.left);
        int rmax = maximum(root.right);
        return Math.max(Math.max(lmax, rmax), root.data);
    }

    public static int minimum(Node root){
        if(root == null) return -(int) 1e8;
        int lmax = minimum(root.left);
        int rmax = minimum(root.right);
        return Math.min(Math.min(lmax, rmax), root.data);
    }

    public static int sum(Node root){
        if(root == null) return 0;
        int lsum = sum(root.left);
        int rsum = sum(root.right);
        return lsum + rsum + root.data;
    }

    public static boolean findData(Node root, int data){
        if(root == null) return false;
        if(root.data == data) return true;
        return findData(root.left, data) || findData(root.right, data); 
    }
}