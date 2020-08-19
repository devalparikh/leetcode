
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
 }

public class MergeTwoSortedLists21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode ans = new ListNode(-1);
        ListNode c = ans;

        while(c1 != null && c2 != null) {
            if(c1.val > c2.val) {
                c.next = new ListNode(c2.val);
                c = c.next;
                c2 = c2.next;
            } else if(c1.val < c2.val) {
                c.next = new ListNode(c1.val);
                c = c.next;
                c1 = c1.next;

            } else {
                c.next = new ListNode(c1.val);
                c.next.next = new ListNode(c2.val);
                c = c.next.next;
                c1 = c1.next;
                c2 = c2.next;
            }
        }
        if(c1 != null) {
            while(c1 != null) {
                c.next = new ListNode(c1.val);
                c = c.next;
                c1 = c1.next;
            }

        }
        if(c2 != null) {
            while(c2 != null) {
                c.next = new ListNode(c2.val);
                c = c.next;
                c2 = c2.next;
            }
        }
        return ans.next;
    }
}
