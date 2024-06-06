import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class LongestConsecutiveTest {

    @Test
    void longestConsecutive() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        Set<Integer> numSet = new HashSet<>();
        for (int n : nums) {
            numSet.add(n);
        }
        int longest = 0;
        for (int n : nums) {
            if (numSet.contains(n - 1)) {
                continue;
            }
            int seq = 1;
            while (numSet.contains(n + seq)) {
                seq++;
            }
            longest = Math.max(longest, seq);
        }
        assertThat(longest).isEqualTo(4);
    }
}
