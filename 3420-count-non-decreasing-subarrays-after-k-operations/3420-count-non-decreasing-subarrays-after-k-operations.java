
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        int right = n - 1;
        Deque<int[]> dq = new ArrayDeque<>();

        for (int left = n - 1; left >= 0; left--) {
            int count = 1;
            int num = nums[left];
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] curr = dq.pollLast();
                cost += (long)(num - curr[0]) * curr[1];
                count += curr[1];
            }

            dq.offerLast(new int[]{num, count});
            while (cost > k) {
                int[] curr = dq.pollFirst();
                cost -= curr[0] - nums[right];
                right--;
                if (curr[1] > 1) {
                    dq.offerFirst(new int[]{curr[0], curr[1] - 1});
                }
            }
            ans += right - left + 1;
        }

        return ans;
    }
}
