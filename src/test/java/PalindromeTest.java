import org.junit.jupiter.api.Test;

// Palindrome: 회문 (예 - racecar)
public class PalindromeTest {

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /*
     * https://leetcode.com/problems/longest-palindromic-substring
     * Approach 1.
     * time: O(n^3)
     * space: O(1)
     */
    @Test
    void longestPalindrome1() {
        String s = "babad";
//        String s = "a";
        String output = "bab";
        String longest = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String ss = s.substring(i, j + 1);
                System.out.println("ss = " + ss);
                if (isPalindrome(ss)) {
                    if (ss.length() > longest.length()) {
                        longest = ss;
                    }
                }
            }
        }
        System.out.println("longest = " + longest);
    }

    /*
     * Approach 2.
     * time: 시간 복잡도는 평균적으로 O(n)이고, palindrome 의 길이가 n 에 가까워지면 시간 복잡도 역시 O(n^2) 에 가까워 진다.
     * space: O(1)
     */
    int maxStart, maxEnd;

    @Test
    void longestPalindrome2() {
        String s = "bababd";
        String output = "bab";
        String longest = "";

        int maxStart = 0;
        int maxEnd = 0;

        for (int i = 0; i < s.length(); i++) {
            int start = i;
            int end = i;
            while (0 <= start && end < s.length() && s.charAt(start) == s.charAt(end)) {
                if (maxEnd - maxStart < end - start) {
                    maxStart = start;
                    maxEnd = end;
                }
                start--;
                end++;
            }

            start = i;
            end = i + 1;
            while (0 <= start && end < s.length() && s.charAt(start) == s.charAt(end)) {
                if (maxEnd - maxStart < end - start) {
                    maxStart = start;
                    maxEnd = end;
                }
                start--;
                end++;
            }
        }

        System.out.println("s.substring(maxStart, maxEnd + 1) = " + s.substring(maxStart, maxEnd + 1));
    }

    @Test
    void longestPalindrome3() {
        String s = "bababd";
        String output = "bab";
        String longest = "";

        for (int i = 0; i < s.length(); i++) {
            calculateMaxLength(i, i, s);
            calculateMaxLength(i, i+1, s);
        }

        System.out.println("s.substring(maxStart, maxEnd + 1) = " + s.substring(maxStart, maxEnd + 1));
    }

    public void calculateMaxLength(int start, int end, String s){
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            if (this.maxEnd - this.maxStart < end - start) {
                this.maxStart = start;
                this.maxEnd = end;
            }
            start--;
            end++;
        }
    }
}
