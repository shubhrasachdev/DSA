
public class questions {

    // May 29, 2021

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // middle of a LL - first mid
    public static ListNode midNode(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // middle of a LL - second mid
    public static ListNode midNode2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // reverse a linked list - Pointer Interchange
    public static ListNode reversePI(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode prev = null, curr = head;
        while(curr != null) {
            ListNode forward = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forward;
        }        
        return prev;
    }

    // Check if LL is palindrome
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode mid = midNode(head);
        ListNode nh = mid.next;
        mid.next = null;
        nh = reversePI(nh);
        ListNode c1 = head, c2 = nh;
        boolean res = true;
        while(c2 != null) {
            if(c1.val != c2.val){
                res = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        nh = reversePI(nh);
        mid.next = nh;
        return res;
    }

    // Fold a linked list such that
    // Input: 1->2->3->4->5->6->7 becomes
    // Output: 1->7->2->6->3->5->4
    public static void fold(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode mid = midNode(head);
        ListNode nh = mid.next;
        mid.next = null;
        nh = reversePI(nh);
        ListNode c1 = head, c2 = nh;
        while(c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next;
            // links
            c1.next = c2;
            c2.next = f1;
            // move
            c1 = f1;
            c2 = f2;
        }
    }

    // Unfold a LL such that
    // Input: 1->7->2->6->3->5->4 becomes
    // Output: 1->2->3->4->5->6->7
    public static void unfold(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode c1 = head;
        ListNode c2 = head.next;
        ListNode p1 = l1, p2 = l2;
        while(c1 != null && c2 != null) {
            p1.next = c1;
            p2.next = c2;
            p1 = p1.next;
            p2 = p2.next;
            if(c2 != null) c1 = c2.next;
            if(c1 != null) c2 = c1.next;
        }
        p1.next = null;
        l2.next = reversePI(l2.next);
        p1.next = l2.next;
    }

    // Merge Two sorted LL
    // Portal + Leetcode 21: https://leetcode.com/problems/merge-two-sorted-lists/
    public static ListNode mergeTwoLists(ListNode one, ListNode two) {
        if(one == null || two == null) {
            if(one == null) return two;
            return one;
        }
        ListNode curr = null, head = null;
        while(one != null && two != null) {
            ListNode node = null;
            if(one.val < two.val){
                node = new ListNode(one.val);
                one = one.next;
            } else {
                node = new ListNode(two.val);
                two = two.next;
            }
            if(head == null) {
                curr = node;
                head = node;
            } else {
                curr.next = node;
                curr = node;
            }
        }
        while(one != null) {
            ListNode node = new ListNode(one.val);
            curr.next = node;
            curr = node;
            one = one.next;
        }

        while(two != null) {
            ListNode node = new ListNode(two.val);
            curr.next = node;
            curr = node;
            two = two.next;
        }
        return head;
    }

    // Merge K Sorted Lists
    //Portal + leetcode 23 - https://leetcode.com/problems/merge-k-sorted-lists/
    
    // Brute force - O(nk) or O(^k2) (lambda k-square) solution
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        ListNode res = null;
        for(int i = 0; i < lists.length; i++) res = mergeTwoLists(res, lists[i]);
        return res;
    }

    // More efficient soln - O(NlogK)
    public static ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if(si == ei) return lists[si];
        int mid = (si + ei) / 2;
        ListNode leftList = mergeKLists(lists, si, mid);
        ListNode rightList = mergeKLists(lists, mid + 1, ei);
        return mergeTwoLists(leftList, rightList);
    }
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    // May 30, 2021

    // merge sort LL
    public static ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode mid = midNode(head);
        ListNode nh = mid.next;
        mid.next = null;
        return mergeTwoLists(mergeSort(head), mergeSort(nh));
    }


    // Remove nth node of LL from the end
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n <= 0) return head;
        ListNode slow = head, fast = head;
        while(n-- > 0){
            fast = fast.next;
            if(fast == null && n > 0) return head;
        } 
        if(fast == null) {
            ListNode removeNode = slow;
            head = removeNode.next;
            removeNode.next = null;
            return head;
        }
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode removeNode = slow.next;
        slow.next = removeNode.next;
        removeNode.next = null;
        return head;
    }

    // Segregate even and odd elements in LL - even appear before odd
    public static ListNode segregateEvenOdd(ListNode head) {
        if(head == null ||  head.next == null) return head;
        ListNode o = new ListNode(-1);
        ListNode e = new ListNode(-1);
        ListNode ep = e, op = o;
        ListNode curr = head;
        while(curr != null) {
            if(curr.val % 2 == 0) {
              ep.next = curr;
              ep = ep.next;  
            } else {
                op.next = curr;
                op = op.next;
            }
            curr = curr.next;
        }
        op.next = null;
        ep.next = o.next;
        head = e.next;
        return head;
    }

    // Segregate 0 and 1
    public static ListNode segregate01(ListNode head) {
        if(head == null ||  head.next == null) return head;
        ListNode zero = new ListNode(-1);
        ListNode one = new ListNode(-1);
        ListNode p0 = zero, p1 = one;
        ListNode curr = head;
        while(curr != null) {
            if(curr.val == 0) {
              p0.next = curr;
              p0 = p0.next;  
            } else {
                p1.next = curr;
                p1 = p1.next;
            }
            curr = curr.next;
        }
        p1.next = null;
        p0.next = one.next;
        head = zero.next;
        return head;
    }

    // Segregate 0, 1 and 2
    public static ListNode segregate012(ListNode head) {
        if(head == null ||  head.next == null) return head;
        ListNode zero = new ListNode(-1);
        ListNode one = new ListNode(-1);
        ListNode two = new ListNode(-1);
        ListNode p0 = zero, p1 = one, p2 = two;
        ListNode curr = head;
        while(curr != null) {
            if(curr.val == 0) {
              p0.next = curr;
              p0 = p0.next;  
            } else if(curr.val == 1){
                p1.next = curr;
                p1 = p1.next;
            } else {
                p2.next = curr;
                p2 = p2.next;
            }
            curr = curr.next;
        }
        p2.next = null;
        p1.next = two.next;
        p0.next = one.next;
        head = zero.next;
        return head;
    }

    // Segregate around last index
    public static ListNode segregateOnLastIndex(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;
        while(curr.next != null) curr = curr.next;
        int val = curr.val;
        curr = head;
        while(curr.next != null) {
            if(curr.val <= val) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }
        lp.next = null;
        sp.next = null;
        curr.next = large.next;
        return curr;
    }

    // Class sol
    // public static ListNode segregateOnLastIndex(ListNode head) {
    //     if (head == null || head.next == null)
    //         return head;

    //     ListNode small = new ListNode(-1);
    //     ListNode large = new ListNode(-1);
    //     ListNode sp = small, lp = large, curr = head;

    //     ListNode pivotNode = head;
    //     while (pivotNode.next != null)
    //         pivotNode = pivotNode.next;

    //     while (curr != null) {
    //         if (curr.val <= pivotNode.val) {
    //             sp.next = curr;
    //             sp = sp.next;
    //         } else {
    //             lp.next = curr;
    //             lp = lp.next;
    //         }
    //         curr = curr.next;
    //     }

    //     sp.next = large.next;
    //     lp.next = null;

    //     return sp;
    // }

    // Segregate around given pivot index
    public static ListNode segregate(ListNode head, int pivotIdx) {
        if(head == null || head.next == null) return head;
        ListNode pivotNode = head;
        while(pivotIdx-- > 0) pivotNode = pivotNode.next;        
        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;
        while(curr != null) {
            if(curr != pivotNode){
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
        sp.next = pivotNode;
        pivotNode.next = large.next;
        lp.next = null;
        head = small.next;
        return head;
    }

    // June 3, 2021 

    // Add two LL
    // Eg: 
    //     1->2->3->4->5->6->7->null
    //          +      7->8->9->null
    // -----------------------------
    //     1->2->3->5->3->5->6->null
    // -----------------------------

    public static ListNode reverseLL(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode prev = null, curr = head;
        while(curr != null) {
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);
        ListNode c1 = l1, c2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        int carry = 0;
        while(c1 != null || c2 != null || carry != 0){
            int sum = carry + (c1 == null ? 0 : c1.val) + (c2 == null ? 0 : c2.val);
            carry = sum / 10;
            sum %= 10;
            p.next = new ListNode(sum);
            p = p.next;
            if(c1 != null) c1 = c1.next;
            if(c2 != null) c2 = c2.next;
        }
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);
        return reverseLL(dummy.next);
    }

    // June 5, 2021

    // Subtract two LL
    // 1->2->3->4->5->6->7->null
    // 7->8->9->null
    // 1->2->3->3->7->7->8->null
    
    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);
        ListNode c1 = l1, c2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        int borrow = 0;
        while(c1 != null || c2 != null){ 
            int diff = borrow + (c1 == null ? 0 : c1.val) - (c2 == null ? 0 : c2.val);
            if(diff < 0) {
                diff += 10;
                borrow = -1;
            } else borrow = 0;
            p.next = new ListNode(diff);
            p = p.next;
            if(c1 != null) c1 = c1.next;
            if(c2 != null) c2 = c2.next;
        }
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);
        ListNode subHead = reverseLL(dummy.next);
        while(subHead != null && subHead.val == 0) subHead = subHead.next; // remove leading zeroess
        return subHead;
    }

    // Is cycle present in LL
    public static boolean isCyclePresentInLL(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }

    // Find starting node of loop/cycle
    public static ListNode CycleNode(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        if(fast != slow) return null;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        } 
        return slow;
    }

    public static ListNode CycleNode2(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        if(fast != slow) return null;
        ListNode meetingNode = fast;
        int a = 1, b = 0, c = 0, bc = 0, nDash = 0, n = 0; // bc is b + c
        slow = head;
        int count = 0;
        boolean isLoopRun = false;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
            if(nDash == 0 && fast == meetingNode) bc = count;
            if(fast == meetingNode) nDash++;
            a++;
            count++;
            isLoopRun = true;
        } 
        if(!isLoopRun){
            fast = fast.next;
            bc = 1;
            while(fast != slow){
                slow = slow.next;
                fast = fast.next;
                bc++;
            }
            a = 0;
            b = bc;
            c = 0;
            n = nDash = 0;
        } else {
            n = nDash + 1;
            c = a - bc * nDash;
            b = bc - c;
        }
        System.out.println("Length of tail is : " + a);
        System.out.println("Length of b is : " + b);
        System.out.println("Length of c is : " + c);
        System.out.println("No. of rotation by fast before meeting pt n: " + n);
        System.out.println("No. of rotation by fast after meeting pt nDash: " + nDash);
        return slow;
    }

    // Passes on Leetcode 142 - https://leetcode.com/problems/linked-list-cycle-ii/
    public static ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        if(fast != slow) return null;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        } 
        return slow;
    }

    // Intersection of 2 LL using Floyad cycle method on Portal and Leetcode 160
    // https://leetcode.com/problems/intersection-of-two-linked-lists/
    public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode tail = headA;
        while(tail.next != null) tail = tail.next;
        tail.next = headB;
        ListNode res = detectCycle(headA);
        tail.next = null;
        return res;
    }

}