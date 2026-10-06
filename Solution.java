import java.util.*;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> targetMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            Integer j = targetMap.get(diff);
            if (j != null) {
                return new int[]{i, j};
            }
            targetMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution");

    }

    public boolean isAnagram(String s, String t) {
        if (t.length() != s.length()) {
            return false;
        }
        Map<Character, Integer> count = new HashMap<>();

        for (char c : s.toCharArray()) {
            count.merge(c, 1, Integer::sum);
        }

        for (char c : t.toCharArray()) {
            if (count.merge(c, -1, Integer::sum) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> values = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (values.contains(nums[i])) {
                return true;
            }
            values.add(nums[i]);
        }
        return false;
    }

    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return true;
            }
        }
        return false;
    }

    public int maxProfit(int[] prices) {

        int buy = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                profit = Math.max(profit, prices[i] - buy);
            }
        }
        return profit;
    }
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];   // не 0 — подмассив должен быть непустым
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public int[] maxSubArrayIndex(int[] nums) {
        int currentSum = nums[0], maxSum = nums[0];
        int start = 0, bestStart = 0, bestEnd = 0;

        for (int i = 1; i < nums.length; i++) {
            if (currentSum < 0) {
                currentSum = nums[i];
                start = i;              // начали новый подмассив
            } else {
                currentSum += nums[i];
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                bestStart = start;
                bestEnd = i;
            }
        }
        return new int[]{bestStart, bestEnd};
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Проход 1: answer[i] = произведение всего СЛЕВА от i
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
            System.out.println("answer"+i+":" + answer[i]);
        }

        // Проход 2: домножаем на произведение всего СПРАВА от i
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
            System.out.println("suffix" + suffix);
            System.out.println("newanswer"+i+":" + answer[i]);
        }

        return answer;
    }

    public boolean isPalindrome(String s) {
        String result = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        char[] chars = result.toCharArray();
        System.out.println(chars);
        int i=0, j= chars.length-1;
        while (i<j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public int[] twoSum2(int[] numbers, int target) {
        Map<Integer, Integer> nums = new HashMap<>();
        int[] answer = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int diff = target-numbers[i];
            Integer k = nums.get(diff);
            if (k != null) {
                answer[0] = k+1;
                answer[1] = i+1;
                break;
            }
            nums.put(numbers[i], i);
        }
        return answer;
    }

    public int[] twoSum2Sorted(int[] numbers, int target) {
        int i=0, j=numbers.length-1;
        int[] answer = new int[2];
        while (i<j) {
            int diff = numbers[j] + numbers[i];
            if (diff == target) {
                answer[0] = i+1;
                answer[1] = j+1;
                break;
            }
            if (diff > target) {
                j--;
            } else {
                i++;
            }
        }
        return answer;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);                       // O(n log n)
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;              // дальше только положительные — суммы нуля не будет
            if (i > 0 && nums[i] == nums[i - 1]) continue;   // пропуск дубликата первого числа

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(List.of(nums[i], nums[left], nums[right]));

                    // сдвигаем оба и пропускаем дубликаты
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        }
        return result;
    }

    public int maxArea(int[] height) {
        int area=0;
        int left = 0;
        int right=height.length-1;
        while (left<right) {
            int tmpArea = Math.min(height[left], height[right]) * (right-left);
            area = Math.max(area, tmpArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }

        }
        return area;
    }
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, best = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            while (window.contains(c)) {          // пока новый символ конфликтует
                window.remove(s.charAt(left));    // выкидываем слева
                left++;
            }

            window.add(c);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public int characterReplacement(String s, int k) {

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(groups.values());
    }

    public int search(int[] nums, int target) {
        int start= 0, end = nums.length-1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return -1;
    }

    //153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int left =0, right = nums.length-1;
        while (left<right) {
            int mid = left + (right-left)/2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    //33. Search in Rotated Sorted Array
    public int searchTarget(int[] nums, int target) {
        int left =0, right = nums.length-1;
        while (left<=right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {                 // ЛЕВАЯ половина отсортирована
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;                       // target внутри неё
                } else {
                    left = mid + 1;                        // значит, он в правой
                }
            } else {                                       // ПРАВАЯ половина отсортирована
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    //20. Valid Parentheses
    public boolean isValidParentheses(String s) {
        Map<Character, Character> pairs = Map.of(')', '(', ']', '[', '}', '{');
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {                       // закрывающая
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) return false;
            } else {
                stack.push(c);                                // открывающая
            }
        }
        return stack.isEmpty();
    }

    //206. Reverse Linked List
    /*public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;   // 1. запомнили хвост
            curr.next = prev;            // 2. перевернули стрелку
            prev = curr;                 // 3. сдвинули prev
            curr = next;                 // 4. сдвинули curr
        }

        return prev;   // curr == null, prev — новая голова
    }*/

    //21. Merge Two Sorted Lists
    /*public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }*/

    //141. Linked List Cycle
    /*public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }*/

    //19. Remove Nth Node From End of List
    /*public ListNode removeNthFromEnd(ListNode head, int n) {
        LisNode node = null;
        while (head.next != null) {
            LisNode current = head.next;
            ListNode coming = current.next;
            if (current.val == n) {
                node.val = n;
                node.next = coming;
            }
        }
        return head;
    }*/

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();   // min-heap по умолчанию

        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();          // выкидываем наименьший — он точно не в топ-k
            }
        }

        return heap.peek();           // корень min-heap = наименьший из k наибольших
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> elements = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(elements::get));

        for (int i = 0; i < nums.length; i++) {
            elements.merge(nums[i], 1, Integer::sum);

        }
        for (Integer num: elements.keySet()) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();          // выкидываем наименьший — он точно не в топ-k
            }
        }
        List<Integer> result = new ArrayList<>(heap);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

