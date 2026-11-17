
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        if(n == 1) return 1;

        int ans = 0;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
        };

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            ans++;

            while(size-- > 0) {
                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                if(row == n-1 && col == n-1) return ans;

                for(int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if(newRow >= 0 && newRow < n &&
                       newCol >= 0 && newCol < n &&
                       !visited[newRow][newCol] &&
                       grid[newRow][newCol] == 0) {

                        visited[newRow][newCol] = true;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return -1;
    }
}
