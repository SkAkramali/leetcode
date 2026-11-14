class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int ans = -1;
        for(int left = 0; left < nums. size(); left++) {
            int sum = 0;
            for(int right = left; right < nums.size(); right++) {
                sum += nums.get(right);
                int size = right -left+ 1;
                if(sum > 0 && size <= r && size >= l && (ans == -1 || ans > sum))  {
                    ans = sum;
                }
            }
        }
        return ans;
    }
}