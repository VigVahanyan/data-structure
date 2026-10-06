import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146 — LRU-кэш (вытеснение давно неиспользуемых).
 * HashMap<key, Node> обеспечивает поиск за O(1); встроенный двусвязный
 * список (head — самый недавно использованный, tail — самый давно
 * использованный) даёт O(1) для перестановки и вытеснения элементов.
 * Фиктивные head/tail-узлы убирают проверки на null на границах списка.
 * Память: O(capacity).
 */
public class LRUCache {
    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    private static class Node {
        final int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Time: O(1). Space: O(1) beyond the fields themselves.
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Looks up key and, if present, marks it most-recently-used.
     * Time: O(1). Space: O(1).
     * @return the value, or -1 if key is absent.
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToFront(node);
        return node.value;
    }

    /**
     * Inserts or updates key's value and marks it most-recently-used.
     * If the cache is at capacity and key is new, evicts the
     * least-recently-used entry (the node just before the tail sentinel).
     * Time: O(1). Space: O(1).
     */
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToFront(node);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
        Node created = new Node(key, value);
        map.put(key, created);
        addToFront(created);
    }

    /**
     * Time: O(1). Space: O(1).
     */
    private void moveToFront(Node node) {
        remove(node);
        addToFront(node);
    }

    /**
     * Unlinks node from the list. Time: O(1). Space: O(1).
     */
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Links node in right after the head sentinel (most-recently-used slot).
     * Time: O(1). Space: O(1).
     */
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
