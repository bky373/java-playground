import org.junit.jupiter.api.Test;

public class DPTest {
    // https://leetcode.com/problems/house-robber/
    @Test
    void rob() {
        int[] nums = {1, 2, 3, 1};
        if (nums.length == 0) {
            return;
        }

        int maxOfTwoStepsLater = 0;
        int maxOfOneStepAhead = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; --i) {
            int curr = Math.max(maxOfOneStepAhead, maxOfTwoStepsLater + nums[i]);

            maxOfTwoStepsLater = maxOfOneStepAhead;
            maxOfOneStepAhead = curr;
        }


        System.out.println("curr = " + maxOfOneStepAhead);
    }
}
