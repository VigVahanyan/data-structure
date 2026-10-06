import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 981 — Хранилище ключ-значение с метками времени.
 * Каждому ключу соответствует список записей (timestamp, value),
 * добавляемых в порядке вызовов set(). Поскольку set() гарантированно
 * вызывается со строго возрастающими метками времени для каждого ключа,
 * список уже отсортирован по timestamp, что позволяет get() искать
 * последнюю подходящую запись бинарным поиском вместо линейного перебора.
 * Память: O(n), где n — общее число вызовов set().
 */
public class TimeMap {
    private final Map<String, List<Entry>> store;

    private static class Entry {
        final int timestamp;
        final String value;

        Entry(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    /**
     * Time: O(1). Space: O(1).
     */
    public TimeMap() {
        store = new HashMap<>();
    }

    /**
     * Appends (timestamp, value) to key's history.
     * Time: O(1) amortized. Space: O(1).
     */
    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, k -> new ArrayList<>()).add(new Entry(timestamp, value));
    }

    /**
     * Binary-searches key's history for the value with the largest
     * timestamp <= the given timestamp.
     * Time: O(log m) where m = number of entries stored for key.
     * Space: O(1).
     * @return the matching value, or "" if key is unknown or has no entry
     *         at or before timestamp.
     */
    public String get(String key, int timestamp) {
        List<Entry> entries = store.get(key);
        if (entries == null) {
            return "";
        }
        int lo = 0, hi = entries.size() - 1;
        String result = "";
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (entries.get(mid).timestamp <= timestamp) {
                result = entries.get(mid).value;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return result;
    }
}
