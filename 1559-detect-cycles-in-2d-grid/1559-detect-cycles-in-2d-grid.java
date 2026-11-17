class Solution {
    public boolean containsCycle(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(!visited[row][col]) {
                    if(dfs(grid, visited, row, col, -1, -1))
                        return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] grid, boolean[][] visited,
                       int row, int col, int prow, int pcol) {

        visited[row][col] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for(int adjacent = 0; adjacent < 4; adjacent++) {

            int curRow = row + dr[adjacent];
            int curCol = col + dc[adjacent];

            if(curRow < 0 || curRow >= grid.length ||
               curCol < 0 || curCol >= grid[0].length)
                continue;

            if(curRow == prow && curCol == pcol)
                continue;

            if(grid[curRow][curCol] != grid[row][col])
                continue;

            if(visited[curRow][curCol])
                return true;

            if(dfs(grid, visited, curRow, curCol, row, col))
                return true;
        }

        return false;
    }
}