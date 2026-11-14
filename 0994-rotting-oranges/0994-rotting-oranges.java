
class Solution {
    public int orangesRotting(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Deque<int[]> queue = new ArrayDeque<>();
        int fresh = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 2) {
                    queue.addLast(new int[]{row, col});
                } 
                else if (grid[row][col] == 1) {
                    fresh++;
                }
            }
        }

        return bfs(grid, queue, fresh);
    }

    public int bfs(int[][] grid, Deque<int[]> queue, int fresh) {
        int ans = 0;
        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curOrange = queue.removeFirst();
                int curRow = curOrange[0];
                int curCol = curOrange[1];
                if (curRow + 1 < grid.length &&
                    grid[curRow + 1][curCol] == 1) {
                    grid[curRow + 1][curCol] = 2;
                    fresh--;
                    queue.addLast(new int[]{curRow + 1, curCol});
                }
                if (curCol + 1 < grid[0].length &&
                    grid[curRow][curCol + 1] == 1) {
                    grid[curRow][curCol + 1] = 2;
                    fresh--;
                    queue.addLast(new int[]{curRow, curCol + 1});
                }

                if (curCol - 1 >= 0 &&
                    grid[curRow][curCol - 1] == 1) {
                    grid[curRow][curCol - 1] = 2;
                    fresh--;
                    queue.addLast(new int[]{curRow, curCol - 1});
                }
                if (curRow - 1 >= 0 &&
                    grid[curRow - 1][curCol] == 1) {

                    grid[curRow - 1][curCol] = 2;
                    fresh--;
                    queue.addLast(new int[]{curRow - 1, curCol});
                }
            }

            ans++;
        }
        return fresh == 0 ? ans : -1;
    }
}
