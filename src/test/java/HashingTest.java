import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
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

    @Test
    void lengthOfLongestSubstring() {
//        String s = "abcabcb";
        String s = "bbb";
//        String s = "pwwkew";
//        String s = "dvdf";
//        String s = " abc";
        int start = 0, k = 0, longest = 0, mapVersion = 0;
        Map<Character, Integer> charMap = new HashMap<>();
//        b[s][k]bbb - curVersion[b]: 0, mapVersion: 0
//        b[s]b[k]bb - curVersion[b]: 1, mapVersion: 0
//                   - [Map Update] mapVersion: 1, longest = 1
//        bb[s][k]bb - curVersion[b]: 1, mapVersion: 1
//        bb[s]b[k]b - curVersion[b]: 2, mapVersion: 1
//                   - [Map Update] mapVersion: 2, longest = 1

//        a[s][k]bcabcbb - curVersion[a]: 0, mapVersion: 0
//        a[s]b[k]cabcbb - curVersion[b]: 0, mapVersion: 0
//        a[s]bc[k]abcbb - curVersion[c]: 0, mapVersion: 0
//        a[s]bca[k]bcbb - curVersion[a]: 1, mapVersion: 0
//                       - [Map Update] mapVersion: 1, longest = 3
//        abca[s][k]bcbb - curVersion[a]: 1, mapVersion: 1
//        abca[s]b[k]cbb - curVersion[b]: 1, mapVersion: 1
//        ...

        while (start + k < s.length()) {
            char curr = s.charAt(start + k);
//            System.out.println("charMap = " + charMap);
//            System.out.printf("s[%c],k[%c]\n\n", s.charAt(start), curr);
            int curVersion = charMap.getOrDefault(curr, 0);
            if (curVersion > mapVersion) {
                longest = Math.max(longest, k);
                start++;
                k = 0;
                mapVersion = curVersion;
            } else {
                charMap.put(curr, mapVersion + 1);
                k++;
            }
        }
        longest = Math.max(longest, k);

        assertThat(longest).isEqualTo(3);
    }
}
