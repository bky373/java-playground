package support;

import java.util.Arrays;
import java.util.List;
import org.example.ListNode;

public class ListNodeSupport {

    public static final ListNode NODE_1 = new ListNode(1);
    public static final ListNode NODE_2 = new ListNode(2);
    public static final ListNode NODE_3 = new ListNode(3);
    public static final ListNode NODE_4 = new ListNode(4);
    public static final ListNode NODE_5 = new ListNode(5);
    public static final ListNode NODE_6 = new ListNode(6);
    public static final ListNode NODE_7 = new ListNode(7);
    public static final ListNode NODE_8 = new ListNode(8);
    public static final ListNode NODE_9 = new ListNode(9);
    public static final ListNode NODE_10 = new ListNode(10);

    public static ListNode from(int val) {
        return new ListNode(val);
    }

    public static ListNode linkNext(ListNode... nodes) {
        List<ListNode> list = Arrays.stream(nodes)
                                    .toList();

        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        return list.get(0);
    }
}
