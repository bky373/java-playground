import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class HashingTest {

    @Test
    void productExceptSelf() {
        int[] nums = {1, 2, 3, 4};
//        int[] nums = {-1, 1, 0, -3, 3};
//        int[] nums = {4, 3, 2, 1, 2};
        int len = nums.length;
        int[] res = new int[len];
        int[] left = new int[len];
        int[] right = new int[len];

        left[0] = nums[0];
        for (int i = 1; i < len; i++) {
            left[i] = nums[i] * left[i - 1];
        }
        right[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            right[i] = nums[i] * right[i + 1];
        }
        for (int i = 1; i < len - 1; i++) {
            res[i] = left[i - 1] * right[i + 1];
        }
        res[0] = right[1];
        res[len - 1] = left[len - 2];
        assertThat(res).isEqualTo(new int[]{24, 12, 8, 6});
//        assertThat(res).isEqualTo(new int[]{0, 0, 9, 0, 0});
//        assertThat(res).isEqualTo(new int[]{12, 16, 24, 48, 24});
    }
}
