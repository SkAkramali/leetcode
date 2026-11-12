class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int ans = 1;
        int order = 2;
        int cur = 0;
        int prev = Integer.MIN_VALUE;
        for(int index = 0; index < nums.length; index++) {
            if(prev > nums[index]) {
                if(order == 2) {
                    order = 1;
                } else if(order != 1) {
                    cur = 1;
                    order = 1;
                }
                cur++;
            } else if(prev < nums[index]) {
                if(order == 2) {
                    order = 0;
                } else if(order != 0) {
                    cur = 1;
                    order = 0;
                }
                cur++;
            } else {
                cur = 1;
                order = 2;
            }
            prev = nums[index];
            ans = Math.max(ans, cur);
        }

        return ans;
    }
}