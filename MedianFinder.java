import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LeetCode 295 — Медиана потока данных.
 * Две кучи делят поток пополам: lowerHalf — max-куча с меньшей половиной
 * чисел, upperHalf — min-куча с большей половиной. После каждой вставки
 * кучи балансируются (разница размеров не больше 1), поэтому медиана
 * всегда находится на вершинах куч.
 * Память: O(n), где n — количество добавленных на текущий момент чисел.
 */
public class MedianFinder {
    private final PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

    public MedianFinder() {
    }

    /**
     * Adds num to lowerHalf, then rebalances by moving lowerHalf's max into
     * upperHalf and, if that overcorrects, moving upperHalf's min back —
     * net effect: num lands in the correct half and the two heaps stay
     * within one element of each other in size.
     * Time: O(log n). Space: O(1).
     */
    public void addNum(int num) {
        lowerHalf.offer(num);
        upperHalf.offer(lowerHalf.poll());
        if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }

    /**
     * Time: O(1). Space: O(1).
     * @return lowerHalf's max if it has the extra element (odd total count),
     *         otherwise the average of both heaps' roots.
     */
    public double findMedian() {
        if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek();
        }
        return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
    }
}
