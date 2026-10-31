class Solution {
    int mod = 1_000_000_007;
    int[][][] dp;
    public int uniquePaths(int[][] grid) {
        dp = new int[grid.length][grid[0].length][2];
        for(int[][] layer : dp) {
            for(int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }

        return solve(grid, 0, 0, 0);
    }

    public int solve(int[][] grid, int row, int col, int dir) {
        if(row >= grid.length || col >= grid[0].length) {
            return 0;
        } // base case

        if(row == grid.length - 1 && col == grid[0].length - 1) {
            return 1;
        } // dest

        if(dp[row][col][dir] != -1) {
            return dp[row][col][dir];
        } // retrun dp value 

        int ans = 0;

        if(grid[row][col] == 1) {
            if(dir == 1) {
                ans = solve(grid, row, col + 1, 0);
            } else {
                ans = solve(grid, row + 1, col, 1);
            }
        } else {
            ans = (solve(grid, row, col + 1, 0) + solve(grid, row + 1, col, 1)) % mod;
        }

        return dp[row][col][dir] = ans;
    }
}