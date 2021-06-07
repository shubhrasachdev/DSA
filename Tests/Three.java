import java.util.*;

public class Three{

    static Scanner scn = new Scanner(System.in);

    // Remove adjacent duplicates in a linked list
    public static class Node{
        int data;
        Node next = null;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public static Node createLL(int n){
        Node head = null, curr = null;
        for(int i = 0; i < n; i++) {
            int data = scn.nextInt();
            Node node = new Node(data);
            if(head == null) {
                head = node;
                curr = node;
            }else{
                curr.next = node;
                curr = curr.next;
            }
        }
        return head;
    }

    public static void printLL(Node head){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static Node removeAdjacentDups(Node head){
        if(head.next == null) return head;
        Node curr = removeAdjacentDups(head.next);
        if(curr.data == head.data) return curr;
        head.next = curr;
        return head;
    }

    // Replace hi with pep
    public static String replaceHiPep(String str, int idx){
        if(str.length() <= 1) return str;
        if(idx >= str.length()) return "";
        if(idx == str.length() - 1) return str.charAt(idx) + "";
        String ans;
        if(str.charAt(idx) == 'h' && str.charAt(idx + 1) == 'i')
            ans = "pep" + replaceHiPep(str, idx + 2);
        else ans = str.charAt(idx) + replaceHiPep(str, idx + 1);
        return ans;
    }

    public static void replaceHiPepWayUp(String str, int idx, String ans){
        if(str.length() <= 1){
            System.out.println(str);
            return;
        }
        if(idx >= str.length()){
            System.out.println(ans);
            return;
        }
        if(idx == str.length() - 1){
            System.out.println(ans + str.charAt(idx));
            return;
        }
        if(str.charAt(idx) == 'h' && str.charAt(idx + 1) == 'i') replaceHiPepWayUp(str, idx + 2, ans + "pep");
        else replaceHiPepWayUp(str, idx + 1, ans + str.charAt(idx));
    }

    // Multiplication of matrices
    public static void inputMatrix(int n, int m, int[][] mat){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                mat[i][j] = scn.nextInt();
            }
        }
    }
    
    public static void printMat(int n, int m, int[][] mat){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void multiplyMat(int[][] a, int[][] b, int n, int m){
        int[][] c = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                c[i][j] = 0;
                for(int k = 0; k < m; k++)
                    c[i][j] += a[i][k] * b[k][j]; 
            }
        }
        printMat(n, m, c);
    }

    public static void main(String[] args){
        String str = scn.next();
        int idx = scn.nextInt();
        String res = replaceHiPep(str, 0);
        System.out.println(res.charAt(idx));
        replaceHiPepWayUp(str, 0, "");
        
    }
}