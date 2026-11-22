
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int[][] distance = new int[n][m];

        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        distance[0][0] = 0;
        heap.offer(new int[]{0, 0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while (!heap.isEmpty()) {

            int[] curr = heap.poll();

            int r = curr[0];
            int c = curr[1];
            int currEffort = curr[2];

            if (currEffort > distance[r][c]) {
                continue;
            }

            if (r == n - 1 && c == m - 1) {
                return currEffort;
            }
            for (int i = 0; i < 4; i++) {

                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m) {
                    continue;
                }
                int edgeEffort = Math.abs(
                    heights[r][c] - heights[nr][nc]
                );
                int newEffort = Math.max(currEffort, edgeEffort);
                if (newEffort < distance[nr][nc]) {

                    distance[nr][nc] = newEffort;

                    heap.offer(new int[]{nr, nc, newEffort});
                }
            }
        }

        return 0;
    }
}
