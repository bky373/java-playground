import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class AnagramTest {

    /**
     * https://leetcode.com/problems/group-anagrams/
     */
    @Test
    void groupAnagrams() {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        Map<String, List<String>> groups = new HashMap<>();

        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            groups.computeIfAbsent(new String(arr), k -> new ArrayList<>())
                  .add(str);
        }
        List<List<String>> values = new ArrayList<>(groups.values());
        assertThat(values).containsExactlyInAnyOrderElementsOf(List.of(
                List.of("bat"),
                List.of("tan", "nat"),
                List.of("eat", "tea", "ate")
        ));
    }
}
