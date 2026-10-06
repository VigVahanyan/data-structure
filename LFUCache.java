import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LeetCode 460 — LFU-кэш (вытеснение наименее часто используемых).
 * Два словаря: cache (key -> Node) для поиска за O(1) и freqBuckets
 * (частота -> упорядоченное по вставке множество узлов с этой частотой)
 * для вытеснения наименее часто используемого элемента за O(1).
 * LinkedHashSet внутри «корзины» разрешает равенство частот по давности
 * (первым вытесняется тот, кто раньше попал в эту частоту) — это
 * соответствует стандартному правилу LeetCode. minFreq хранит наименьшую
 * непустую «корзину», поэтому вытеснение никогда не требует поиска.
 * Память: O(capacity).
 */
public class LFUCache {
    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> cache;
    private final Map<Integer, LinkedHashSet<Node>> freqBuckets;

    private static class Node {
        final int key;
        int value;
        int freq = 1;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Time: O(1). Space: O(1) beyond the fields themselves.
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freqBuckets = new HashMap<>();
    }

    /**
     * Looks up key and, if present, bumps its frequency by one.
     * Time: O(1) amortized. Space: O(1).
     * @return the value, or -1 if key is absent.
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        touch(node);
        return node.value;
    }

    /**
     * Inserts or updates key's value and bumps its frequency by one.
     * If the cache is at capacity and key is new, evicts one node from the
     * minFreq bucket (the least-frequently-used, oldest-among-ties entry).
     * Time: O(1) amortized. Space: O(1).
     */
    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            touch(node);
            return;
        }
        if (cache.size() == capacity) {
            LinkedHashSet<Node> minBucket = freqBuckets.get(minFreq);
            Node evict = minBucket.iterator().next();
            minBucket.remove(evict);
            cache.remove(evict.key);
        }
        Node created = new Node(key, value);
        cache.put(key, created);
        freqBuckets.computeIfAbsent(1, f -> new LinkedHashSet<>()).add(created);
        minFreq = 1;
    }

    /**
     * Moves node from its current frequency bucket to freq+1, updating
     * minFreq if the old bucket became empty and was the minimum.
     * Time: O(1) amortized. Space: O(1).
     */
    private void touch(Node node) {
        int freq = node.freq;
        LinkedHashSet<Node> bucket = freqBuckets.get(freq);
        bucket.remove(node);
        if (bucket.isEmpty()) {
            freqBuckets.remove(freq);
            if (minFreq == freq) {
                minFreq++;
            }
        }
        node.freq++;
        freqBuckets.computeIfAbsent(node.freq, f -> new LinkedHashSet<>()).add(node);
    }
}
