import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BinarySearchTest {

    @Test
    void findMin() {
//        int[] nums = {3, 4, 5, 1, 2};
//        int[] nums = {4, 5, 6, 7, 8, 9, 0, 1, 2};
        int[] nums = {2, 1};
        int ans = 0;
        if (nums[0] < nums[nums.length - 1]) {
            ans = nums[0];
        }

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
//        assertThat(ans).isEqualTo(1);
//        assertThat(ans).isEqualTo(0);
        assertThat(ans).isEqualTo(1);
    }
}
