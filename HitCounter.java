/**
 * LeetCode 362 — Проектирование счётчика запросов (Hit Counter).
 * Кольцо из 300 фиксированных ячеек — по одной на каждую секунду в
 * скользящем окне 5 минут. Каждая ячейка хранит последнюю попавшую в неё
 * метку времени и счётчик, поэтому ячейка неявно «сбрасывается» при
 * следующем попадании более новой метки времени — заранее очищать
 * устаревшие ячейки не нужно.
 * Память: O(1) — массивы фиксированного размера (300 ячеек) независимо от
 * количества запросов.
 */
public class HitCounter {
    private static final int WINDOW = 300;
    private final int[] timestamps = new int[WINDOW];
    private final int[] counts = new int[WINDOW];

    public HitCounter() {
    }

    /**
     * Records a hit at the given timestamp (assumed monotonically
     * non-decreasing across calls, per problem constraints). If the slot's
     * stored timestamp is stale, it's overwritten (lazy reset); otherwise
     * the slot's count is incremented for hits sharing the same second.
     * Time: O(1). Space: O(1).
     */
    public void hit(int timestamp) {
        int idx = timestamp % WINDOW;
        if (timestamps[idx] != timestamp) {
            timestamps[idx] = timestamp;
            counts[idx] = 1;
        } else {
            counts[idx]++;
        }
    }

    /**
     * Sums the counts of all slots whose stored timestamp falls within the
     * trailing 300-second window ending at timestamp.
     * Time: O(1) — always scans exactly 300 slots. Space: O(1).
     */
    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < WINDOW; i++) {
            if (timestamp - timestamps[i] < WINDOW) {
                total += counts[i];
            }
        }
        return total;
    }
}
