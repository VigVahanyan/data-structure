import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    void twoSum_findsMatchingPair() {
        int[] result = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        assertArrayEquals(new int[]{1, 0}, result);
    }

    @Test
    void twoSum_throwsWhenNoPairExists() {
        assertThrows(IllegalArgumentException.class,
                () -> solution.twoSum(new int[]{1, 2, 3}, 100));
    }

    @Test
    void isAnagram_trueForAnagrams() {
        assertTrue(solution.isAnagram("anagram", "nagaram"));
    }

    @Test
    void isAnagram_falseForDifferentLengths() {
        assertFalse(solution.isAnagram("rat", "car"));
    }

    @Test
    void isAnagram_falseForSameLengthNonAnagrams() {
        assertFalse(solution.isAnagram("rat", "cat"));
    }

    @Test
    void containsDuplicate_trueWhenDuplicateExists() {
        assertTrue(solution.containsDuplicate(new int[]{1, 2, 3, 1}));
    }

    @Test
    void containsDuplicate_falseWhenAllUnique() {
        assertFalse(solution.containsDuplicate(new int[]{1, 2, 3, 4}));
    }

    @Test
    void containsDuplicate1_trueWhenDuplicateExists() {
        assertTrue(solution.containsDuplicate1(new int[]{1, 2, 3, 1}));
    }

    @Test
    void containsDuplicate1_falseWhenAllUnique() {
        assertFalse(solution.containsDuplicate1(new int[]{1, 2, 3, 4}));
    }

    @Test
    void maxProfit_returnsBestProfit() {
        assertEquals(5, solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    void maxProfit_returnsZeroWhenPricesOnlyDecrease() {
        assertEquals(0, solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    void maxSubArray_returnsMaxSum() {
        assertEquals(6, solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    void maxSubArray_handlesSingleElement() {
        assertEquals(1, solution.maxSubArray(new int[]{1}));
    }

    @Test
    void maxSubArrayIndex_returnsBoundsOfMaxSubarray() {
        int[] result = solution.maxSubArrayIndex(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        assertArrayEquals(new int[]{3, 6}, result);
    }

    @Test
    void productExceptSelf_returnsProductsExcludingSelf() {
        int[] result = solution.productExceptSelf(new int[]{1, 2, 3, 4});
        assertArrayEquals(new int[]{24, 12, 8, 6}, result);
    }

    @Test
    void productExceptSelf_handlesZeroInArray() {
        int[] result = solution.productExceptSelf(new int[]{1, 2, 0, 4});
        assertArrayEquals(new int[]{0, 0, 8, 0}, result);
    }
}
