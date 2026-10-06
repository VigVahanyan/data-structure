/**
 * LeetCode 622 — Проектирование циклической очереди.
 * Классический кольцевой буфер: массив фиксированного размера с отслеживанием
 * head/count, семантика FIFO; enQueue/deQueue отклоняют операцию, если
 * очередь полна/пуста, вместо перезаписи (в отличие от политики RingBuffer,
 * которая перезаписывает элементы при переполнении).
 * Память: O(n), где n — ёмкость очереди.
 */
public class MyCircularQueue {
    private final int[] data;
    private final int capacity;
    private int head;
    private int count;

    /**
     * Time: O(k) — array allocation. Space: O(k).
     */
    public MyCircularQueue(int k) {
        data = new int[k];
        capacity = k;
    }

    /**
     * Inserts value at the tail if there's room.
     * Time: O(1). Space: O(1).
     * @return false if the queue is full, true otherwise.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        int tail = (head + count) % capacity;
        data[tail] = value;
        count++;
        return true;
    }

    /**
     * Removes the value at the head if the queue is non-empty.
     * Time: O(1). Space: O(1).
     * @return false if the queue is empty, true otherwise.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        count--;
        return true;
    }

    /**
     * Time: O(1). Space: O(1).
     * @return the front value, or -1 if empty.
     */
    public int Front() {
        return isEmpty() ? -1 : data[head];
    }

    /**
     * Time: O(1). Space: O(1).
     * @return the rear value, or -1 if empty.
     */
    public int Rear() {
        return isEmpty() ? -1 : data[(head + count - 1) % capacity];
    }

    /**
     * Time: O(1). Space: O(1).
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Time: O(1). Space: O(1).
     */
    public boolean isFull() {
        return count == capacity;
    }
}
