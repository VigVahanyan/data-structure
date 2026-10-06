import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 239 — Максимум в скользящем окне.
 * Монотонный дек индексов, убывающий по значению от начала к концу, поэтому
 * в начале дека всегда лежит индекс максимума текущего окна. Каждый индекс
 * попадает в дек и покидает его не более одного раза, поэтому суммарная
 * работа составляет O(n), несмотря на внешне вложенные циклы while.
 */
public class SlidingWindowMaximum {
    /**
     * For every window of size k in nums, returns the maximum.
     * Time: O(n) where n = nums.length — amortized O(1) per index across
     * the two while loops. Space: O(n) for the output array plus O(k) for
     * the deque.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> indices = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            while (!indices.isEmpty() && indices.peekFirst() <= i - k) {
                indices.pollFirst();
            }
            while (!indices.isEmpty() && nums[indices.peekLast()] < nums[i]) {
                indices.pollLast();
            }
            indices.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[indices.peekFirst()];
            }
        }
        return result;
    }
}
