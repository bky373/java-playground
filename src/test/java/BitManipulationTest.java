import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BitManipulationTest {

    /**
     * https://leetcode.com/problems/reverse-bits/
     */
    @Test
    void reverseBits() {
        int n = Integer.parseInt("00000010100101000001111010011100", 2);
        System.out.println("n = " + n);
        System.out.println("Integer.toBinaryString(n) = " + Integer.toBinaryString(n));
        int ans = 0;

        // Loop through each bit of the integer (32 bits for an int)
        for (int i = 0; i < 32; i++) {
            // Shift `ans` left by 1 (equivalent to multiplying by 2) and OR with the rightmost bit of `n`
            ans = ans << 1 | n & 1;

            // Shift `n` right by 1 to process the next bit
            n >>= 1;
        }
        System.out.println("ans = " + ans);
        System.out.println("Integer.toBinaryString(ans) = " + Integer.toBinaryString(ans));
        assertThat(ans).isEqualTo(964176192);
    }

    /**
     * https://leetcode.com/problems/missing-number/
     */
    @Test
    void missingNumber() {
        int[] nums = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        int ans = nums.length;
        for (int i=0; i<nums.length; i++) {
            ans ^= i^nums[i];
        }
        System.out.println("ans = " + ans);
        assertThat(ans).isEqualTo(8);
    }
}
