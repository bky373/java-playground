import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class TwoPointerTest {

    @Test
    void twoSum() {
        int[] nums = new int[]{-1, 0, -3, -3, 1, -4};
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                res.add(nums[left]);
                res.add(nums[right]);
                left++;
            }
        }
        assertThat(res).containsExactlyInAnyOrderElementsOf(List.of(-1, 1));
    }

    @Test
    void threeSum() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        Arrays.sort(nums);
        System.out.println("nums = " + Arrays.toString(nums));
        int i = 0, j, k;
        int ni = 0, nj, nk;
        Set<List<Integer>> res = new HashSet<>();
        while (i < nums.length && ni <= 0) {
            ni = nums[i];
            j = i + 1;
            k = nums.length - 1;
            while (j < k) {
                nj = nums[j];
                nk = nums[k];
                int sum = ni + nj + nk;
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    res.add(List.of(ni, nj, nk));
                    j++;
                }
            }
            i++;
        }
        assertThat(res).containsExactlyInAnyOrderElementsOf(
                List.of(List.of(-1, -1, 2),
                        List.of(-1, 0, 1)));
    }
}
