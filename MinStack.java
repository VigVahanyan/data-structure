import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> mins  = new ArrayDeque<>();

    public MinStack() {

    }

    public void push(int value) {
        stack.push(value);
        mins.push(mins.isEmpty() ? value : Math.min(mins.peek(), value));
    }

    public void pop() {
        stack.pop();
        mins.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return mins.peek();
    }
}
