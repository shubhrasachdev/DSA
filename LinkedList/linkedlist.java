// Feb 24, 2020
public class linkedlist {
    private class Node {
        private int data = 0;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }
    private Node head = null;
    private Node tail = null;
    private int sizeOfLL = 0;

    public boolean isEmpty(){
        return sizeOfLL == 0;
    }

    public int size(){
        return sizeOfLL;
    }

    public void display(){
        Node curr = this.head;
        while(curr != null) {
            System.out.println(curr.data +  " --> ");
            curr = curr.next;
        }
    }
    
    // Exceptions ==================================================================
    private void emptyException() throws Exception{
        if(isEmpty()) 
            throw new Exception("LinkedList is empty");
    }
    
    private void checkIndex(int idx) throws Exception{
        if(idx < 0 || idx >= this.sizeOfLL)
            throw new Exception("Linked List Index out of bounds");
    }

    private void checkIndexInclusive(int idx) throws Exception{
        if(idx < 0 || idx > this.sizeOfLL)
            throw new Exception("Linked List Index out of bounds");
    }

    // Add =========================================================================
    private void addFirstNode(Node node){
        if(this.head == null){
            this.head = node;
            this.tail = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
        this.sizeOfLL++;
    }

    public void addFirst(int data){
        Node node = new Node(data);
        addFirstNode(node);
    }

    private void addLastNode(Node node){
        if(this.head == null){
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
        this.sizeOfLL++;
    }

    public void addLast(int data){
        Node node = new Node(data);
        addLastNode(node);
    }

    private void addNodeAt(int idx, Node node){
        if(idx == 0) addFirstNode(node);
        else if(idx == this.sizeOfLL) addLastNode(node);
        else{
            Node prev = getNodeAt(idx - 1);
            Node temp = prev.next;
            prev.next = node;
            node.next = temp;
            this.sizeOfLL++;
        }
    }

    public void addAt(int idx, int data) throws Exception {
        checkIndexInclusive(idx); 
        Node node = new Node(data);
        addNodeAt(idx, node);        
    }

    // Get =========================================================================
    
    public int getFirst() throws Exception{
        emptyException();
        return this.head.data;
    }

    public int getLast() throws Exception{
        emptyException();
        return this.tail.data;
    }

    private Node getNodeAt(int idx){
        Node node = this.head;
        while(idx-- > 0) node = node.next;
        return node;
    }

    public int getAt(int idx) throws Exception{
        checkIndex(idx);
        Node node = getNodeAt(idx); 
        return node.data;
    }

    // Remove =========================================================================
    private Node removeFirstNode(){
        Node removeNode = this.head;
        if(this.sizeOfLL == 1){
            this.head = null;
            this.tail = null;
        }else{
            Node temp = this.head.next;
            removeNode.next = null;
            this.head = temp;
        }
        this.sizeOfLL--;
        return removeNode;
    }
    
    public int removeFirst() throws Exception{
        emptyException();
        Node node = removeFirstNode();
        return node.data;
    }

    private Node removeLastNode(){
        Node removeNode = this.tail;
        if(this.sizeOfLL == 1){
            this.head = null;
            this.tail = null;
        }else{
            Node secondLastNode = getNodeAt(this.sizeOfLL - 2);
            this.tail = secondLastNode;
            this.tail.next = null;
        }
        this.sizeOfLL--;
        return removeNode;

    }

    public int removeLast() throws Exception{
        emptyException();
        Node node = removeLastNode();
        return node.data;
    }

    private Node removeNodeAt(int idx){
        if(idx == 0) return removeFirstNode();
        if(idx == this.sizeOfLL - 1) return removeLastNode();
        Node prev = getNodeAt(idx - 1);
        Node removeNode = prev.next;
        Node temp = removeNode.next;
        prev.next = temp;
        removeNode.next = null;
        this.sizeOfLL--;
        return removeNode;
    }

    public int removeAt(int idx) throws Exception{
        checkIndex(idx);
        Node node = removeNodeAt(idx);
        return node.data;
    }

    // Feb 26, 2020
    
    // to find middle of a linked list
    // this version returns 1st mid for even length LL
    public int middleOne(){
        Node fast = this.head;
        Node slow = this.head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }
    
    // this version returns 2nd mid for even length LL
    public int middleTwo(){
        Node fast = this.head;
        Node slow = this.head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }


    // reverse LL using pointer iteration
    public void reversePI(){
        Node prev = null;
        Node curr = this.head;
        while(curr != null){
            Node forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        this.tail = this.head;
        this.head = prev;
    }

    // reverse data elements of a LL in nodes
    // Time: O(n2)
    public void reverseDI() {
        int i = 0, j = size() - 1;
        while(i < j){
            Node a = getNodeAt(i);
            Node b = getNodeAt(j);
            int temp = a.data;
            a.data = b.data;
            b.data = temp;
            i++;
            j--;
        }
    }

    // display reverse LL recursively
    private void displayReverseHelper(Node node){
        if(node.next != null) displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }

    public void displayReverse(){
        displayReverseHelper(head);
        System.out.println();
    }

    // reverse linked list (pointer - recursive)
    private void reversePRHelper(Node node){
        if(node.next == null){
            Node temp = head;
            head = tail;
            tail = temp;
            return;
        }
        reversePRHelper(node.next);
        Node prev = node.next;
        prev.next = node;
    }

    public void reversePR(){
        reversePRHelper(this.head);
    }

    // find kth last element from the linked lis
    public int kthFromLast(int k){
        Node fast = this.head, slow = this.head;
        while(k-- > 0) fast = fast.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow.data;
    }
    
    // merge two sorted linked lists
    public static linkedlist mergeTwoSortedLists(linkedlist l1, linkedlist l2) {
        linkedlist result = new linkedlist();
        Node one = l1.head, two = l2.head;
        while(one != null && two != null){
            if(one.data < two.data){
                result.addLast(one.data);
                one = one.next;
            }
            else{
                result.addLast(two.data);
                two = two.next;
            }
        }
        while(one != null){
            result.addLast(one.data);
            one = one.next;
        }
        while(two != null){
            result.addLast(two.data);
            two = two.next;
        }
        return result;
    }

    // March 1, 2021


    /* 
    Homework Question
    
    You are required to complete the body of addLinkedLists function. The function is passed two linked lists which represent two numbers - the first element 
    is the most significant digit and the last element is the least significant digit. The function is expected to add the two linked list and return a new linked list.
    The following approaches are not allowed :
    1. Don't reverse the linked lists in order to access them from least significant digit to most significant.
    2. Don't use arrays or explicit extra memory.
    3. Don't convert linked lists to number, add them up and convert the result back to a linked list.
    
    Hint - You are expected to take help of recursion to access digits from least significant to most significant. You have to tackle the challenge of unequal 
    size of lists and manage carry where required. 
    
    */
    
    // Method 1 - Reverse is allowed, iterative solution
    public static linkedlist addTwoLists1(linkedlist one, linkedlist two){
        one.reversePI();
        two.reversePI();
        int carry = 0;
        Node c1 = one.head, c2 = two.head;
        linkedlist ans = new linkedlist();
        while(c1 != null || c2 != null || carry != 0){
            int sum = (c1 != null ? c1.data : 0) + (c2 != null ? c2.data : 0) + carry;
            carry = sum / 10;
            sum %= 10;
            ans.addFirst(sum);
            if(c1 != null) c1 = c1.next;
            if(c2 != null) c2 = c2.next;
            
        }
        one.reversePI();
        two.reversePI();
        return ans;
    }

    // Method 2 - Reverse is not allowed, recursive solution
    private static int addTwoListsHelper(Node one, int p, Node two, int q, linkedlist res){
        int sum = 0;
        if(one == null && two == null) return sum;
        if(p > q){
            int carry = addTwoListsHelper(one.next, p - 1, two, q, res);
            sum = one.data + carry;
        }else if(p < q){
            int carry = addTwoListsHelper(one, p, two.next, q - 1, res);
            sum = two.data + carry;
        }else{
            int carry = addTwoListsHelper(one.next, p - 1, two.next, q - 1, res);
            sum = one.data + two.data + carry;
        }
        int data = sum % 10;
        int carry = sum / 10;
        res.addFirst(data);
        return carry;
    }

    public static linkedlist addTwoLists2(linkedlist one, linkedlist two) {
        linkedlist res = new linkedlist();
        int carry = addTwoListsHelper(one.head, one.size(), two.head, two.size(), res);
        if(carry > 0) res.addFirst(carry);
        return res;
    }

    
    // The function is expected to return a new sorted list. The original list must not change.
    public static Node middle(Node h, Node t){
        Node f = h;
        Node s = h;
        while (f.next != t && f.next.next != t) {
            f = f.next.next;
            s = s.next;
        }
      return s;
    }

    // my method - merge sort LL
    public static linkedlist mergeSort(Node head, Node tail){
      if(head == tail){
          linkedlist base = new linkedlist();
          base.addLast(head.data);
          return base;
      }
      Node mid = middle(head, tail);
      linkedlist first = mergeSort(head, mid);
      linkedlist second = mergeSort(mid.next, tail);
      linkedlist res = mergeTwoSortedLists(first, second);
      return res;
    }

    // sir's method - merge sort LL
    public static linkedlist mergeSort2(Node head, Node tail){
        if(head == tail){
            linkedlist base = new linkedlist();
            base.addLast(head.data);
            return base;
        }
        Node mid = middle(head, tail);
        Node head1 = head;
        Node tail1 = mid;
        Node head2 = mid.next;
        Node tail2 = tail;
        mid.next = null;
        linkedlist first = mergeSort2(head1, tail1);
        linkedlist second = mergeSort2(head2, tail2);
        mid.next = head2;
        linkedlist res = mergeTwoSortedLists(first, second);
        return res;
    }

}