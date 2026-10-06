/**
 * LeetCode 641 — Проектирование циклического дека.
 * Та же структура кольцевого буфера, что и в MyCircularQueue, но вставка и
 * удаление поддерживаются с обоих концов. insertFront сдвигает head назад
 * (по модулю capacity, со смещением +capacity, чтобы избежать
 * отрицательного остатка); остальная логика аналогична очереди.
 * Память: O(n), где n — ёмкость дека.
 */
public class MyCircularDeque {
    private final int[] data;
    private final int capacity;
    private int head;
    private int count;

    /**
     * Time: O(k) — array allocation. Space: O(k).
     */
    public MyCircularDeque(int k) {
        data = new int[k];
        capacity = k;
    }

    /**
     * Inserts value at the front if there's room.
     * Time: O(1). Space: O(1).
     * @return false if the deque is full, true otherwise.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        head = (head - 1 + capacity) % capacity;
        data[head] = value;
        count++;
        return true;
    }

    /**
     * Inserts value at the back if there's room.
     * Time: O(1). Space: O(1).
     * @return false if the deque is full, true otherwise.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        data[(head + count) % capacity] = value;
        count++;
        return true;
    }

    /**
     * Removes the front element if the deque is non-empty.
     * Time: O(1). Space: O(1).
     * @return false if the deque is empty, true otherwise.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        count--;
        return true;
    }

    /**
     * Removes the back element if the deque is non-empty.
     * Time: O(1). Space: O(1).
     * @return false if the deque is empty, true otherwise.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        count--;
        return true;
    }

    /**
     * Time: O(1). Space: O(1).
     * @return the front value, or -1 if empty.
     */
    public int getFront() {
        return isEmpty() ? -1 : data[head];
    }

    /**
     * Time: O(1). Space: O(1).
     * @return the rear value, or -1 if empty.
     */
    public int getRear() {
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
