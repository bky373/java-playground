import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BinarySearchTest {

    /**
     * 이진 검색은 일반적으로 3가지 주요 섹션으로 구성됩니다:
     * 전처리 - 컬렉션이 정렬되지 않은 경우 정렬합니다.
     * 이진 검색 - 루프 또는 재귀를 사용하여 각 비교 후 검색 공간을 반으로 나눕니다.
     * 후처리 - 남은 공간에서 실행 가능한 후보를 결정합니다.
     * (Template 1에서는 각 단계에서 요소가 발견되었는지 확인하기 때문에 후처리가 필요하지 않습니다.)
     */
    int binarySearchTemplate1() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    @Test
    void findMin() {
//        int[] nums = {3, 4, 5, 1, 2};
//        int[] nums = {4, 5, 6, 7, 8, 9, 0, 1, 2};
        int[] nums = {2, 1};
        int ans = 0;
        if (nums[0] < nums[nums.length - 1]) {
            ans = nums[0];
        } else {
            int left = 0;
            int right = nums.length - 1;
            while (nums[left] > nums[right]) {
                int mid = (left + right) / 2;
                System.out.printf("mid(i,v) = mid(i=%d,v=%d)\n", mid, nums[mid]);
                if (nums[mid] < nums[right]) {
                    right = mid;
                } else {
                    left = mid + 1; // left 가 mid 보다 크다는 건, left 까지는 최솟값을 찾을 수 없고, mid + 1 이후의 첫 번째 오름차순에서 최솟값을 가야만 한다는 얘기
                }
            }
            ans = nums[left];
        }
//        assertThat(ans).isEqualTo(1);
//        assertThat(ans).isEqualTo(0);
        assertThat(ans).isEqualTo(1);
    }
}
