import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackTrackingTest {

    /*
     * https://leetcode.com/problems/combination-sum/
     */
    @Test
    void combinationSum() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> combination = new LinkedList<>();
        backtrack(candidates, target, combination, 0, result);
        System.out.println("result = " + result);
    }

    void backtrack(int[] candidates, int remain, LinkedList<Integer> combination, int start, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(combination));
        } else if (remain < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            combination.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], combination, i, result);
            combination.removeLast();
        }
    }
}
