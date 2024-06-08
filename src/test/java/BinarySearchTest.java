import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BinarySearchTest {

    @Test
    void testBinarySearchTemplates() {
        int bs1 = binarySearchTemplate1();
        int bs2 = binarySearchTemplate2();
        assertThat(bs1).isEqualTo(1);
        assertThat(bs2).isEqualTo(5);
    }

    /**
     * 이진 검색은 일반적으로 3가지 주요 섹션으로 구성됩니다:
     * - 전처리 - 컬렉션이 정렬되지 않은 경우 정렬합니다.
     * - 이진 검색 - 루프 또는 재귀를 사용하여 각 비교 후 검색 공간을 반으로 나눕니다.
     * - 후처리 - 남은 공간에서 실행 가능한 후보를 결정합니다.
     * https://leetcode.com/explore/learn
     */
    /**
     * 이진 검색의 가장 기본적이고 원초적인 형태
     * - 요소의 이웃 요소와 비교하지 않고(또는 주변의 특정 요소 사용) 검색 조건을 결정할 수 있습니다.
     * - 각 단계에서 요소가 발견되었는지 확인하기 때문에 후처리가 필요하지 않습니다. 마지막에 도달하면 요소를 찾지 못했음을 알 수 있습니다.
     */
    int binarySearchTemplate1() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 0;
        int left = 0;
        int right = nums.length - 1;
//        -1[l], {0},    3[m], 5, 9, 12[r]
//        -1[l], {0}[r], 3[ ], 5, 9, 12[ ]
//        -1[l], {0}[r], 3[ ], 5, 9, 12[ ]
//        -1[l][ ], {0}[r][m], 3[ ], 5, 9, 12[ ]
        while (left <= right) {
            int mid = (left + right) / 2;
//            System.out.printf("mid(i,v) = mid(i=%d,v=%d)\n", mid, nums[mid]);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 이진 검색을 구현하는 고급 방법입니다.
     * - 요소의 오른쪽 이웃을 사용하여 조건이 충족되는지 확인하고 왼쪽 또는 오른쪽으로 이동할지 결정합니다.
     * - 각 단계에서 검색 공간이 최소 2 크기 이상 보장됨
     * - 후처리가 필요합니다. 요소가 1개 남으면 루프/재귀가 종료됩니다. 남은 요소가 조건을 충족하는지 평가해야 합니다.
     */
    int binarySearchTemplate2() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 12;
        int left = 0;
        int right = nums.length - 1;
//        -1[l], 0, 3[m], 5, 9, {12}[r]
//        -1[ ], 0, 3[ ], 5[l], 9, {12}[r]
//        -1[ ], 0, 3[ ], 5[l], 9[m], {12}[r]
//        -1[ ], 0, 3[ ], 5[ ], 9[ ], {12}[l][m][r]
        while (left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            System.out.printf("mid(i,v) = mid(i=%d,v=%d)\n", mid, nums[mid]);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    @Test
    void findMin() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int ans = -1;
//      ## case 1. target = 0
//      4[l], 5, 6, 7[m], {0}, 1, 2[r]
//      4[l], 5, 6, 7[ ], {0}[l], 1, 2[r]
//      4[l], 5, 6, 7[ ], {0}[l], 1[m], 2[r]
//      4[l], 5, 6, 7[ ], {0}[l], 1[r], 2[ ]
//      4[l], 5, 6, 7[ ], {0}[l][m], 1[r], 2[ ]
//      ## case 2. target = 0
//      0[l], 1, 2, 4[m], 5, 6, 7[r]
        if (nums[0] < nums[nums.length - 1]) {
            ans = nums[0];
        } else {
            int left = 0;
            int right = nums.length - 1;
            while (nums[left] > nums[right]) {
                int mid = (left + right) / 2;
                System.out.printf("mid(i,v) = mid(i=%d,v=%d)\n", mid, nums[mid]);
                if (nums[mid] < nums[right]) { // mid ~ right 은 오름차순, 최솟값을 찾으려면 right 을 mid 로 이동해야 함
                    right = mid;
                } else {
                    left = mid + 1; // mid ~ right 은 내림차순, 최솟값을 찾으려면 mid 다음으로 이동해야 함
                }
            }
            ans = nums[left]; // left ~ right 가 오름차순이 되어버리면, 여기 범위에서는 left 가 최솟값
        }
        assertThat(ans).isEqualTo(0);
    }
}
