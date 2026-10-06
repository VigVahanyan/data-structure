# data-structure

Java implementations of classic data structures and algorithm problems (many from LeetCode), written for practice.

## Contents

### Data structure designs

| File | Description |
|------|-------------|
| `LRUCache.java` | Least-recently-used cache |
| `LFUCache.java` | Least-frequently-used cache |
| `MinStack.java` | Stack with constant-time minimum lookup |
| `MyCircularQueue.java` | Fixed-size circular queue |
| `MyCircularDeque.java` | Fixed-size circular double-ended queue |
| `RingBuffer.java` | Fixed-capacity ring buffer that overwrites the oldest element when full |
| `MovingAverage.java` | Moving average over a sliding window of a data stream |
| `MedianFinder.java` | Running median of a data stream |
| `HitCounter.java` | Hit counter over a 5-minute sliding window |
| `TimeMap.java` | Time-based key-value store with binary-search lookups |
| `SlidingWindowMaximum.java` | Maximum of each sliding window |

### Algorithm problems

`Solution.java` collects solutions to common problems: two sum, anagrams, duplicates, best time to buy and sell stock, maximum subarray, product of array except self, palindromes, 3Sum, container with most water, longest substring without repeating characters, group anagrams, rotated-array search, valid parentheses, Kth largest element, top K frequent elements, and more.

`SolutionTest.java` contains tests for these solutions.

## Requirements

- JDK 11 or newer

## Build

```sh
javac -d out *.java
```

Compiling `SolutionTest.java` requires JUnit 5 (Jupiter) on the classpath. If you don't have it, compile the other files individually.

## Notes

Source comments are written in Russian.
