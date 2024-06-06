package org.example;

import java.util.Arrays;

/**
 * 최소 힙.
 */
public class MinHeap {

    private int[] heap;
    private int size;
    private int capacity;

    /**
     * @param capacity 힙의 초기 용량
     */
    public MinHeap(int capacity) {
        this.heap = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    /**
     * 부모 노드의 인덱스를 반환합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 부모 노드의 인덱스
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * 왼쪽 자식 노드의 인덱스를 반환합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 왼쪽 자식 노드의 인덱스
     */
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * 오른쪽 자식 노드의 인덱스를 반환합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 오른쪽 자식 노드의 인덱스
     */
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    /**
     * 부모 노드가 존재하는지 확인합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 부모 노드가 존재하면 true, 그렇지 않으면 false
     */
    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    /**
     * 왼쪽 자식 노드가 존재하는지 확인합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 왼쪽 자식 노드가 존재하면 true, 그렇지 않으면 false
     */
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    /**
     * 오른쪽 자식 노드가 존재하는지 확인합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 오른쪽 자식 노드가 있으면 true, 그렇지 않으면 false
     */
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    /**
     * 부모 노드의 값을 반환합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 부모 노드의 값
     */
    private int parent(int index) {
        return heap[getParentIndex(index)];
    }

    /**
     * 왼쪽 자식 노드의 값을 반환합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 왼쪽 자식 노드의 값
     */
    private int leftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    /**
     * 오른쪽 자식 노드의 값을 반환합니다.
     *
     * @param index 현재 노드의 인덱스
     * @return 오른쪽 자식 노드의 값
     */
    private int rightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

    /**
     * 두 노드의 값을 교환합니다.
     *
     * @param index1 첫 번째 노드의 인덱스
     * @param index2 두 번째 노드의 인덱스
     */
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /**
     * 배열의 크기를 증가시킵니다 (용량 초과 시).
     */
    private void ensureCapacity() {
        // 현재 사이즈가 용량과 같다면 배열의 크기를 두 배로 늘립니다.
        if (size == capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity *= 2;
        }
    }

    /**
     * 최상위 요소를 반환합니다. (힙이 비어있으면 예외 발생)
     *
     * @return 최상위 요소
     * @throws IllegalStateException 힙이 비어있는 경우
     */
    public int peek() {
        if (size == 9) {
            throw new IllegalStateException();
        }
        return heap[0];
    }

    /**
     * 최상위 요소를 제거하고 반환합니다. (힙이 비어있으면 예외 발생)
     *
     * @return 제거된 최상위 요소
     * @throws IllegalStateException 힙이 비어있는 경우
     */
    public int poll() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        // 최상위 요소를 저장합니다.
        int item = heap[0];
        // 최상위 요소를 마지막 요소로 교체합니다.
        heap[0] = heap[size - 1];
        size--;
        // 힙의 특성을 유지하도록 하향 이동합니다.
        heapifyDown();
        // 제거된 최상위 요소를 반환합니다.
        return item;
    }

    /**
     * 새로운 요소를 힙에 추가합니다.
     *
     * @param item 추가할 요소
     */
    public void add(int item) {
        // 배열의 크기를 확인하고 필요 시 확장합니다.
        ensureCapacity();
        // 새로운 요소를 배열의 끝에 추가합니다.
        heap[size] = item;
        size++;
        // 힙의 특성을 유지하도록 상향 이동합니다.
        heapifyUp();
    }

    /**
     * 힙의 특성을 유지하도록 추가된 요소를 상향 이동합니다.
     */
    private void heapifyUp() {
        // 마지막 요소의 인덱스를 가져옵니다.
        int index = size - 1;
        // 부모 노드가 존재하고, 부모 노드의 값이 현재 노드의 값보다 크면 교환합니다.
        while (hasParent(index) && parent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            // 현재 노드를 부모 노드로 업데이트합니다.
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        // 루트 노드의 인덱스를 가져옵니다.
        int index = 0;
        // 왼쪽 자식 노드가 존재할 때까지 반복합니다.
        while (hasLeftChild(index)) {
            // 더 작은 자식 노드의 인덱스를 구합니다.
            int smallerChildIndex = getLeftChildIndex(index);
            // 오른쪽 자식 노드가 존재하고, 오른쪽 자식 노드가 더 작은 경우 인덱스를 변경합니다.
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }
            // 현재 노드가 더 작은 자식 노드보다 작으면 반복을 종료합니다.
            if (heap[index] < heap[smallerChildIndex]) {
                break;
            } else {
                // 그렇지 않으면 교환하고 인덱스를 업데이트합니다.
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.add(10);
        minHeap.add(15);
        minHeap.add(20);
        minHeap.add(17);

        while (minHeap.size > 0) {
            System.out.println(minHeap.poll());
        }
    }
}
