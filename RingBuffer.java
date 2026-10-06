import java.util.ArrayList;
import java.util.List;

/**
 * Кольцевой буфер (циклический кэш) фиксированной ёмкости на основе одного массива.
 * Когда буфер заполнен, {@link #add} молча перезаписывает самый старый элемент
 * вместо отказа в записи (семантика вытеснения, в отличие от очереди,
 * которая заблокировала бы запись или вернула ошибку).
 * Память: O(n), где n — ёмкость буфера.
 */
public class RingBuffer<T> {
    private final Object[] data;
    private final int capacity;
    private int head;
    private int size;

    /**
     * Allocates the backing array.
     * Time: O(n) — array allocation. Space: O(n).
     */
    public RingBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.data = new Object[capacity];
    }

    /**
     * Inserts an item at the logical tail. If the buffer is full, the oldest
     * element (at head) is evicted and head advances by one slot.
     * Time: O(1). Space: O(1).
     */
    public void add(T item) {
        int tail = (head + size) % capacity;
        data[tail] = item;
        if (size == capacity) {
            head = (head + 1) % capacity;
        } else {
            size++;
        }
    }

    /**
     * Returns the element at the given logical position, where 0 is the
     * oldest element currently held and size()-1 is the newest.
     * Time: O(1). Space: O(1).
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) data[(head + index) % capacity];
    }

    /**
     * Number of elements currently held (<= capacity).
     * Time: O(1). Space: O(1).
     */
    public int size() {
        return size;
    }

    /**
     * Maximum number of elements the buffer can hold before it starts evicting.
     * Time: O(1). Space: O(1).
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Time: O(1). Space: O(1).
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Time: O(1). Space: O(1).
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Snapshot of current contents, ordered oldest-to-newest.
     * Time: O(n) where n = size(). Space: O(n) for the returned list.
     */
    @SuppressWarnings("unchecked")
    public List<T> toList() {
        List<T> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add((T) data[(head + i) % capacity]);
        }
        return result;
    }
}
