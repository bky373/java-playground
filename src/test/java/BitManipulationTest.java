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
    }
}
