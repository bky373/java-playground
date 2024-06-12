package org.example;

public class ListNode {

    public int value;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode[" + value + "]";
    }

    public String toDetailString() {
        return "ListNode[v=" + value + ", next=" + next + "]";
    }
}
