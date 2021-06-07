// June 3, 2021

import java.util.*;
public class quickSortLL {
    static Random rand = new Random();
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static int length(ListNode node) {
        if(node == null) return 0;
        ListNode curr = node;
        int len = 0;
        while(curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }

    public static ListNode[] segregate(ListNode head, int pivotIdx) {
        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);      
        ListNode pivotNode = head, curr = head, sp = small, lp = large;
        while(pivotIdx-- > 0) pivotNode = pivotNode.next;
        while(curr != null) {
            if(curr != pivotNode) {
                if(curr.val <= pivotNode.val){
                    sp.next = curr;
                    sp = sp.next;
                } else {
                    lp.next = curr;
                    lp = lp.next;
                }
            }
            curr = curr.next;
        }
        pivotNode.next = sp.next = lp.next = null;
        return new ListNode[]{small.next, pivotNode, large.next};
    }

    public static ListNode[] mergeElements(ListNode[] left, ListNode pivotNode, ListNode[] right) {
        ListNode head = null, tail = null;
        if(left[0] != null && right[0] != null) {
            head = left[0];
            tail = right[1];
            left[1].next = pivotNode;
            pivotNode.next = right[0];
        } else if(left[0] != null) {
            head = left[0];
            tail = pivotNode;
            left[1].next = pivotNode;
        } else if(right[0] != null) {
            head = pivotNode;
            tail = right[1];
            pivotNode.next = right[0];
        }
        return new ListNode[]{head, tail};
    }

    public static ListNode[] quickSort(ListNode head) {
        if(head == null || head == head.next) return new ListNode[] {head, head};
        int len = length(head);
        int pivotIdx = rand.nextInt(len);
        ListNode[] segregatedElements = segregate(head, pivotIdx);
        ListNode[] left = quickSort(segregatedElements[0]);
        ListNode[] right = quickSort(segregatedElements[2]);         
        return mergeElements(left, segregatedElements[1], right);
    }

    public static ListNode sortLL(ListNode head) {
        return quickSort(head)[0];
    }
    public static void main(String[] args) {

    }
}