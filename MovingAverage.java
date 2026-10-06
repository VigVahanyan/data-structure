/**
 * LeetCode 346 — Скользящее среднее потока данных.
 * Кольцевой буфер последних `size` значений плюс текущая сумма окна,
 * поэтому каждый вызов next() не пересчитывает сумму окна заново.
 * Память: O(n), где n — размер окна.
 */
public class MovingAverage {
    private final int[] window;
    private int index;
    private int count;
    private double sum;

    /**
     * Time: O(n) — array allocation. Space: O(n).
     */
    public MovingAverage(int size) {
        window = new int[size];
    }

    /**
     * Pushes val into the window, evicting the oldest value once the window
     * is full, and returns the average of the current window.
     * Time: O(1). Space: O(1).
     */
    public double next(int val) {
        sum -= window[index];
        window[index] = val;
        sum += val;
        index = (index + 1) % window.length;
        count = Math.min(count + 1, window.length);
        return sum / count;
    }
}
