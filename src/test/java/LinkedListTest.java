import static support.ListNodeSupport.NODE_1;
import static support.ListNodeSupport.NODE_2;
import static support.ListNodeSupport.NODE_3;
import static support.ListNodeSupport.NODE_4;

import java.util.ArrayList;
import java.util.List;
import org.example.ListNode;
import org.junit.jupiter.api.Test;
import support.ListNodeSupport;

public class LinkedListTest {

    /**
     * https://leetcode.com/problems/reorder-list/
     */
    @Test
    void reorderList() {
        ListNode head = ListNodeSupport.linkNext(NODE_1, NODE_2, NODE_3, NODE_4);
        System.out.println("head = " + head.toDetailString());

//        0,1,2,3
//        0,3   - left=0, right=3
//        0,3,1 - left=1, right=3
//        0,3,1,2 - left=1, right=2
//        0,3,1,2 [end] - left=2, right=2

//        0,1,2,3,4
//        0,5 - left=0, right=4
//        0,5,1 - left=1, right=4
//        0,5,1,4 - left=1, right=3
//        0,5,1,4,2 - left=2, right=3
//        0,5,1,4,2,3 - left=2, right=2
//        0,5,1,4,2,3 [end] - left=3, right=3

        if (head.next == null) {
            return;
        }
        List<ListNode> nodes = new ArrayList<>();
        ListNode reorderHead = head;
        ListNode reorderHead2 = head;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            nodes.add(head);
            head = next;
        }

        int left = 0;
        int right = nodes.size() - 1;
        while (left < right) {
            reorderHead.next = nodes.get(right);
            right--;
            left++;
            if (left <= right && left < nodes.size()) {
                reorderHead = reorderHead.next;
                reorderHead.next = nodes.get(left);
                reorderHead = reorderHead.next;
            }
        }
        System.out.println("reorderHead = " + reorderHead2);
    }

    /**
     *  https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     */
    @Test
    void removeNthFromEnd() {
        ListNode head = ListNodeSupport.linkNext(NODE_1, NODE_2);
//        ListNode head = ListNodeSupport.linkNext(NODE_1, NODE_2, NODE_3, NODE_4, NODE_5);
//        ListNode head = ListNodeSupport.linkNext(NODE_1);
        int n = 2;

        ListNode resultHead = head;
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }
        System.out.println("nodes = " + nodes);

        int i = nodes.size() - n;
        if (i <= 0) {
            resultHead = resultHead.next;
            return;
        }
        ListNode node = nodes.get(i - 1);
        if (node.next != null) {
            node.next = node.next.next;
        } else {
            node.next = null;
        }
        System.out.println("head = " + resultHead);
    }
}
