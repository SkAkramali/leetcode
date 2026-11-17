class Solution {
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] ans = new int[mat.length][mat[0].length];
        for(int row = 0; row < mat.length; row++) {
            for(int col = 0; col < mat[0].length; col++) {
                if(mat[row][col] == 0) {
                    queue.offer(new int[]{row, col});
                    ans[row][col] = 0;
                }
                else {
                    ans[row][col] = -1;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int m = mat.length;
        int n = mat[0].length;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                if(ans[nr][nc] != -1)
                    continue;

                ans[nr][nc] = ans[r][c] + 1;

                queue.offer(new int[]{nr, nc});
            }
        }
        return ans;
    }
}