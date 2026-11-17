class Solution {
    public boolean containsCycle(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(!visited[row][col]) {
                    if(bfs(grid, visited, row, col)) return true;
                } 
            }
        }
        
        return false;
    }

    public boolean bfs(char[][] grid, boolean[][] visited, int row, int col) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{-1, -1, row, col});
        visited[row][col] = true;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while(queue.size() != 0) {
            int size = queue.size();
            for(int index = 0; index < size; index++) {
                int[] cur = queue.removeFirst();
                for(int adjecent = 0; adjecent < 4; adjecent++) {
                    int curRow = cur[2] + dr[adjecent];
                    int curCol = cur[3] + dc[adjecent];
                    if(curRow == cur[0] && curCol == cur[1]) continue;
                    if(curRow >= grid.length || curRow < 0) continue;
                    if(curCol >= grid[0].length || curCol < 0) continue;
                    if(grid[curRow][curCol] != grid[cur[2]][cur[3]]) continue;
                    if(visited[curRow][curCol]) return true;
                    visited[curRow][curCol] = true;
                    queue.addLast(new int[]{cur[2], cur[3], curRow, curCol});
                }
            }
        } 
        return false;
    }
}